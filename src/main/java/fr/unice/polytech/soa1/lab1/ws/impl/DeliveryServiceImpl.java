package fr.unice.polytech.soa1.lab1.ws.impl;

import fr.unice.polytech.soa1.lab1.business.Customer;
import fr.unice.polytech.soa1.lab1.business.Delivery;
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
        return null;
    }

    public Delivery displayDeliveryMode(int deliveryId) {
        return null;
    }

    public Customer provideCustomerData(int orderId, String firstname, String lastname, String address, String zipcode, String city, String country, String phone, String email) {
        return null;
    }

    public Delivery chooseDeliveryMode(int orderId, int deliveryId) {
        return null;
    }

    public Delivery trackDeliveryStatus(int orderId, String email) {
        return null;
    }
}
