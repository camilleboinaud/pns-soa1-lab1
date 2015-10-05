package fr.unice.polytech.soa1.lab1.ws.impl;

import fr.unice.polytech.soa1.lab1.Storage;
import fr.unice.polytech.soa1.lab1.business.*;
import fr.unice.polytech.soa1.lab1.business.Package;
import fr.unice.polytech.soa1.lab1.utils.ContentType;
import fr.unice.polytech.soa1.lab1.utils.exceptions.ContentNotFoundException;
import fr.unice.polytech.soa1.lab1.ws.BusinessManagementService;

import javax.jws.WebService;
import java.util.Collection;

/**
 * Created by camille on 05/10/15.
 */

@WebService(
        targetNamespace   = "http://informatique.polytech.unice.fr/soa1/lab1/ws",
        portName          = "BusinessManagementServicePort",
        serviceName       = "BusinessManagementService",
        endpointInterface = "fr.unice.polytech.soa1.lab1.ws.BusinessManagementService"
)
public class BusinessManagementServiceImpl implements BusinessManagementService {

    /**
     * Create a new object in "database". Only Items, Deliveries or
     * Packages contents are allowed.
     * @param contentType
     * @param price
     * @param name
     * @param description
     * @return <T>
     * @throws ContentNotFoundException
     */
    public <T extends StorableContent> T createContent(ContentType contentType, Double price, String name, String description)
            throws ContentNotFoundException {

        switch (contentType){
            case ITEM:
                return Storage.create(ContentType.ITEM, new Item(price, name, description));
            case DELIVERY:
                return Storage.create(ContentType.DELIVERY, new Delivery(name, price, description));
            case PACKAGE:
                return Storage.create(ContentType.PACKAGE, new Package(name, price, description));
            default:
                throw new ContentNotFoundException("Specified content type does not allow creation");
        }

    }

    /**
     * List all contents for the specified type (ex : Items, Customers, etc.)
     * @param contentType
     * @return
     */
    public Collection<? extends StorableContent> listContents(ContentType contentType) {
        return Storage.findAll(contentType);
    }

    /**
     * Return content as specified in parameters
     * @param contentType
     * @param contentId
     * @return <T>
     * @throws ContentNotFoundException
     */
    public <T extends StorableContent> T displayContent(ContentType contentType, Integer contentId) throws ContentNotFoundException {
        T content = (T) Storage.read(contentType, contentId);
        if(content != null){
            return content;
        } else {
            throw new ContentNotFoundException("Content specified does not exist");
        }
    }

}
