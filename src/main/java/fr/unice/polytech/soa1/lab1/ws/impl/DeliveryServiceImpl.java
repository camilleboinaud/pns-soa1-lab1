package fr.unice.polytech.soa1.lab1.ws.impl;

import fr.unice.polytech.soa1.lab1.Storage;
import fr.unice.polytech.soa1.lab1.business.Customer;
import fr.unice.polytech.soa1.lab1.business.Delivery;
import fr.unice.polytech.soa1.lab1.business.Order;
import fr.unice.polytech.soa1.lab1.business.StorableContent;
import fr.unice.polytech.soa1.lab1.utils.ContentType;
import fr.unice.polytech.soa1.lab1.utils.exceptions.ContentNotFoundException;
import fr.unice.polytech.soa1.lab1.ws.DeliveryService;

import javax.jws.WebService;
import java.util.Collection;

/**
 * Created by camille on 04/10/15.
 */

@WebService(
        targetNamespace   = "http://informatique.polytech.unice.fr/soa1/lab1/ws",
        portName          = "DeliveryServicePort",
        serviceName       = "DeliveryService",
        endpointInterface = "fr.unice.polytech.soa1.lab1.ws.DeliveryService"
)
public class DeliveryServiceImpl implements DeliveryService {

    public Collection<Delivery> listDeliveriesModes() {
        return Storage.findAll(ContentType.DELIVERY);
    }

    public Delivery displayDeliveryMode(int deliveryId)  throws NullPointerException,IllegalArgumentException {
        if (deliveryId < 0) {
            throw new IllegalArgumentException("deliveryId cannot be negative");
        }
        Delivery deliveryFound = (Delivery) Storage.read(ContentType.DELIVERY, deliveryId);
        if (deliveryFound != null) {
            return deliveryFound;
        } else {
            throw new NullPointerException("delivery not found with deliveryId = "+deliveryId);
        }
    }

    public Customer provideCustomerData(int orderId, String firstname, String lastname, String address, String zipcode, String city, String country, String phone, String email)
            throws NullPointerException,IllegalArgumentException{
        if (orderId < 0){
            throw new IllegalArgumentException("orderId cannot be negative");
        }
        if ( firstname == null || lastname == null || address == null || zipcode == null || city == null || country == null || phone == null || email == null) {
            throw new IllegalArgumentException("your argument(s) cannot be empty");
        }
        Order orderFount = (Order) Storage.read(ContentType.ORDER,orderId) ;
        if (orderFount != null){
            // create a new client
            Customer customer = new Customer();
            customer.setFirstname(firstname);
            customer.setLastname(lastname);
            customer.setAddress(address);
            customer.setZipcode(zipcode);
            customer.setCity(city);
            customer.setCountry(country);
            customer.setPhone(phone);
            customer.setEmail(email);
            // update order
            orderFount.setCustomer(customer);
            // persist into database
            Storage.create(ContentType.CUSTOMER,customer);
            return customer;
        } else {
            throw new NullPointerException("order not found with orderId = "+orderId);
        }
    }

    public Delivery chooseDeliveryMode(int orderId, int deliveryId)  throws NullPointerException,IllegalArgumentException{
        if (orderId < 0 || deliveryId < 0) {
            throw new IllegalArgumentException("orderId and deliveryId cannot be negative");
        }
        Order orderFound = (Order) Storage.read(ContentType.ORDER,orderId);
        Delivery deliveryFound = (Delivery) Storage.read(ContentType.DELIVERY,deliveryId);
        if (orderFound != null && deliveryFound != null){
            // update order
            orderFound.setDelivery(deliveryFound);
            return deliveryFound;
        } else {
            String message;
            if (orderFound == null)
                message = "cannot find the order with orderId = " + orderId;
            else
                message = "cannot find the delivery with delivertId = "+ deliveryId;
            throw new NullPointerException(message);
        }
    }

    public Delivery trackDeliveryStatus(int orderId, String email) throws NullPointerException,IllegalArgumentException {
        if (orderId < 0 ) {
            throw new IllegalArgumentException("orderId cannot be negative");
        }
        if (email == null) {
            throw new IllegalArgumentException("email cannot be empty");
        }
        Order orderFound = (Order) Storage.read(ContentType.ORDER,orderId);
        if (orderFound != null) {
            Customer customerInOrder = orderFound.getCustomer();
            if (customerInOrder != null && (email.equals(customerInOrder.getEmail()))) {
                return orderFound.getDelivery();
            }
            throw new NullPointerException("cannot find the customer in the order "+orderId);
        } else {
            throw new NullPointerException("cannot find the order with orderId = "+orderId);
        }
    }
}
