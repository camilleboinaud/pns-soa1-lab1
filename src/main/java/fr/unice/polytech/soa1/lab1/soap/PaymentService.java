package fr.unice.polytech.soa1.lab1.soap;

import fr.unice.polytech.soa1.lab1.business.Invoice;

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
    );

    @WebResult(name="PaymentService_pay")
    Invoice payOrder(
            @WebParam(name="orderId") int orderId,
            @WebParam(name="creditCardNumber") String creditCardNumber,
            @WebParam(name="email") String email
    );

    @WebResult(name="PaymentService_invoice")
    Invoice issueInvoice(
            @WebParam(name="orderId") int orderId,
            @WebParam(name="email") String email
    );


}
