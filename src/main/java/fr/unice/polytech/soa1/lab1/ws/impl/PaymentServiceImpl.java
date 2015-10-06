package fr.unice.polytech.soa1.lab1.ws.impl;

import fr.unice.polytech.soa1.lab1.Storage;
import fr.unice.polytech.soa1.lab1.business.*;
import fr.unice.polytech.soa1.lab1.utils.*;
import fr.unice.polytech.soa1.lab1.utils.exceptions.ContentNotFoundException;
import fr.unice.polytech.soa1.lab1.utils.exceptions.RequestFailException;
import fr.unice.polytech.soa1.lab1.utils.exceptions.RestrictedFonctionalityException;
import fr.unice.polytech.soa1.lab1.ws.PaymentService;

import javax.jws.WebService;
import java.util.Iterator;

/**
 * Created by camille on 30/09/15.
 */

@WebService(
        targetNamespace   = "http://informatique.polytech.unice.fr/soa1/lab1/ws",
        portName          = "PaymentServicePort",
        serviceName       = "PaymentService",
        endpointInterface = "fr.unice.polytech.soa1.lab1.ws.PaymentService"
)
public class PaymentServiceImpl implements PaymentService {

    /**
     * Service method used to calculate the total amount of your cart.
     * @param orderId
     * @return
     */
    public double calculateAmount(int orderId) throws ContentNotFoundException {

        Order order = (Order)Storage.read(ContentType.ORDER, orderId);
        double amount = 0.0;

        if(order != null) {

            for (Pair<Item, Integer> pair : order.getCart()) {
                amount += pair.getRight() * pair.getLeft().getPrice();
            }
            amount += order.getDelivery().getPrice();
            amount += order.getPackaging().getPrice();

            return amount;
        } else {
            throw new ContentNotFoundException("The specified order does not exist");
        }
    }

    /**
     * Service method used to pay and validate your order.
     * @param orderId
     * @param creditCardNumber
     * @param email
     * @return
     */
    public Invoice payOrder(int orderId, String creditCardNumber, String email)
            throws RequestFailException, ContentNotFoundException, RestrictedFonctionalityException{

        Invoice invoice = null;
        Order order = (Order)Storage.read(ContentType.ORDER, orderId);

        if(order != null) {
            if(order.getStatus().equals(OrderStatus.NOT_VALIDATED)) {
                if (order.getCustomer().getEmail().equals(email) && (Integer.parseInt(creditCardNumber) % 2 == 0)) {
                    invoice = getInvoice(order);
                    if (!invoice.isPaid()) {
                        invoice.setPaid();
                    }
                    invoice.getOrder().setStatus(OrderStatus.IN_PREPARATION);
                    return invoice;
                } else {
                    throw new RequestFailException("Payment failed, user email doesn't match or credit card number is not valid.");
                }
            } else {
                throw new RestrictedFonctionalityException("Your order has already been validated");
            }
        } else {
            throw new ContentNotFoundException("The specified order does not exists");
        }
    }

    /**
     * Service method used to invoice customers.
     * @param orderId
     * @param email
     * @return
     */
    public Invoice issueInvoice(int orderId, String email)
            throws ContentNotFoundException, RequestFailException, RestrictedFonctionalityException {

        Order order = (Order)Storage.read(ContentType.ORDER, orderId);
        if(order != null) {
            if(!order.getStatus().equals(OrderStatus.NOT_VALIDATED)) {
                if (order.getCustomer().getEmail().equals(email)) {
                    return getInvoice(order);
                } else {
                    throw new RequestFailException("Email given does not match");
                }
            } else {
                throw new RestrictedFonctionalityException("You must pay your oder to validate it before");
            }
        } else {
            throw new ContentNotFoundException("The specified order does not exist");
        }
    }


    private static Invoice getInvoice(Order order){
        Invoice invoice;

        for(Iterator it = Storage.findAll(ContentType.INVOICE).iterator(); it.hasNext();){
            invoice = (Invoice)it.next();
            if(invoice.getOrder().getId().equals(order.getId())){
                return invoice;
            }
        }

        invoice = new Invoice(order);
        Storage.create(ContentType.INVOICE, invoice);

        return invoice;
    }

}
