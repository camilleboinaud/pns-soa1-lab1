package fr.unice.polytech.soa1.lab1.ws.impl;

import fr.unice.polytech.soa1.lab1.Storage;
import fr.unice.polytech.soa1.lab1.business.Customer;
import fr.unice.polytech.soa1.lab1.business.Delivery;
import fr.unice.polytech.soa1.lab1.business.Order;
import fr.unice.polytech.soa1.lab1.business.StorableContent;
import fr.unice.polytech.soa1.lab1.utils.ContentType;
import fr.unice.polytech.soa1.lab1.utils.OrderStatus;
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

    /**
     * Provides customer personal data like its address for shipment and invoice
     * @param orderId
     * @param firstname
     * @param lastname
     * @param address
     * @param zipcode
     * @param city
     * @param country
     * @param phone
     * @param email
     * @return
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public Customer provideCustomerShipmentData(int orderId, String firstname, String lastname, String address, String zipcode, String city, String country, String phone, String email)
            throws NullPointerException,IllegalArgumentException{
        if (orderId < 0){
            throw new IllegalArgumentException("orderId cannot be negative");
        }
        if ( firstname == null
                || lastname == null
                || address == null
                || zipcode == null
                || city == null
                || country == null
                || phone == null
                || email == null) {
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

    /**
     * This method must be used to select a delivery mode for shipment
     * @param orderId
     * @param deliveryId
     * @return
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
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


}
