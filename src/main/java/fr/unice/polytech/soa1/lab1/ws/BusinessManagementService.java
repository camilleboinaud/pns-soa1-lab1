package fr.unice.polytech.soa1.lab1.ws;

import fr.unice.polytech.soa1.lab1.business.*;
import fr.unice.polytech.soa1.lab1.utils.ContentType;
import fr.unice.polytech.soa1.lab1.utils.exceptions.ContentNotFoundException;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.Collection;

/**
 * Created by camille on 05/10/15.
 */

@WebService(name="BusinessManagementService")
public interface BusinessManagementService {

    @WebResult(name = "BusinessManagementService_create_content")
    <T extends StorableContent> T createContent(
            @WebParam(name = "contentType") ContentType contentType,
            @WebParam(name = "price") Double price,
            @WebParam(name = "name") String name,
            @WebParam(name = "description") String description
    ) throws ContentNotFoundException;

    @WebResult(name = "BusinessManagementService_list_contents")
    Collection<? extends StorableContent> listContents (
            @WebParam(name = "contentType") ContentType contentType
    );

    @WebResult(name = "BusinessManagementService_display_content")
    <T extends StorableContent> T displayContent(
            @WebParam(name = "contentType") ContentType contentType,
            @WebParam(name = "contentId") Integer contentId
    ) throws ContentNotFoundException;

}
