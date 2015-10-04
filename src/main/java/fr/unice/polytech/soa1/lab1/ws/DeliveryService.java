package fr.unice.polytech.soa1.lab1.ws;

import fr.unice.polytech.soa1.lab1.business.*;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.Collection;

/**
 * Created by camille on 04/10/15.
 */

@WebService(name = "DeliveryService")
public interface DeliveryService {


    @WebResult(name = "DeliveryService_list_deliveries")
    Collection<Delivery> listDeliveriesModes();

    @WebResult(name = "DeliveryService_display_delivery")
    Delivery displayDeliveryMode(
            @WebParam(name = "deliveryId") int deliveryId
    );

    @WebResult(name = "DeliveryService_provide_customer_data")
    Customer provideCustomerData(
            @WebParam(name = "orderId") int orderId,
            @WebParam(name = "firstname") String firstname,
            @WebParam(name = "lastname") String lastname,
            @WebParam(name = "address") String address,
            @WebParam(name = "zipcode") String zipcode,
            @WebParam(name = "city") String city,
            @WebParam(name = "country") String country,
            @WebParam(name = "phone") String phone,
            @WebParam(name = "email") String email
    );

    @WebResult(name = "DeliveryService_choose_delivery")
    Delivery chooseDeliveryMode(
            @WebParam(name = "orderId") int orderId,
            @WebParam(name = "deliveryId") int deliveryId
    );

    @WebResult(name = "DeliveryService_track_delivery")
    Delivery trackDeliveryStatus(
            @WebParam(name = "orderId") int orderId,
            @WebParam(name = "email") String email
    );


}
