package fr.unice.polytech.soa1.lab1.rest;

import fr.unice.polytech.soa1.lab1.business.Delivery;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by camille on 03/10/15.
 */

@Path("/delivery")
@Produces(MediaType.APPLICATION_JSON)
public class DeliveryService {

    @GET
    @Path("/get/all")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getDeliveries(){
        List<Delivery> deliveries = new ArrayList<Delivery>();

        for(int i = 0 ; i < 10 ; i++){
            deliveries.add(new Delivery("type_"+i, new Double(i), "description_"+i));
        }
        return Response.ok().entity(deliveries).build();
    }

}
