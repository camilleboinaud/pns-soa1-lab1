package fr.unice.polytech.soa1.lab1.ws;

import fr.unice.polytech.soa1.lab1.business.Invoice;
import fr.unice.polytech.soa1.lab1.utils.exceptions.ContentNotFoundException;
import fr.unice.polytech.soa1.lab1.utils.exceptions.RequestFailException;
import fr.unice.polytech.soa1.lab1.utils.exceptions.RestrictedFonctionalityException;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * Created by camille on 30/09/15.
 */

@WebService(name="PaymentService")
public interface PaymentService {

    @WebResult(name="PaymentService_amount")
    double calculateAmount(
            @WebParam(name="orderId") int orderId
    ) throws ContentNotFoundException;

    @WebResult(name="PaymentService_pay")
    Invoice payOrder(
            @WebParam(name="orderId") int orderId,
            @WebParam(name="creditCardNumber") String creditCardNumber,
            @WebParam(name="email") String email
    ) throws RequestFailException, ContentNotFoundException, RestrictedFonctionalityException;

    @WebResult(name="PaymentService_invoice")
    Invoice issueInvoice(
            @WebParam(name="orderId") int orderId,
            @WebParam(name="email") String email
    ) throws ContentNotFoundException, RequestFailException, RestrictedFonctionalityException;


}
