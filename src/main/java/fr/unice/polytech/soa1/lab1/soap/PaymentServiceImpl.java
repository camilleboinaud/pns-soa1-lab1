package fr.unice.polytech.soa1.lab1.soap;

import fr.unice.polytech.soa1.lab1.business.Invoice;

import javax.jws.WebService;

/**
 * Created by camille on 30/09/15.
 */

@WebService(
        targetNamespace   = "http://informatique.polytech.unice.fr/soa1/lab1/soap",
        portName          = "PaymentServicePort",
        serviceName       = "PaymentService",
        endpointInterface = "fr.unice.polytech.soa1.lab1.soap.PaymentService"
)
public class PaymentServiceImpl implements PaymentService {

    /**
     * Service method used to calculate the total amount of your cart.
     * @param orderId
     * @return
     */
    public int calculateAmount(int orderId) {
        return 0;
    }

    /**
     * Service method used to pay and validate your order.
     * @param orderId
     * @param creditCardNumber
     * @param email
     * @return
     */
    public Invoice payOrder(int orderId, String creditCardNumber, String email) {
        return new Invoice();
    }

    /**
     * Service method used to invoice customers.
     * @param orderId
     * @param email
     * @return
     */
    public Invoice issueInvoice(int orderId, String email) {
        return new Invoice();
    }

}
