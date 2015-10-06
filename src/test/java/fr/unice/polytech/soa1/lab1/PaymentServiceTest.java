package fr.unice.polytech.soa1.lab1;

import fr.unice.polytech.soa1.lab1.business.*;
import fr.unice.polytech.soa1.lab1.business.Package;
import fr.unice.polytech.soa1.lab1.utils.ContentType;
import fr.unice.polytech.soa1.lab1.utils.OrderStatus;
import fr.unice.polytech.soa1.lab1.utils.exceptions.ContentNotFoundException;
import fr.unice.polytech.soa1.lab1.utils.exceptions.RequestFailException;
import fr.unice.polytech.soa1.lab1.utils.exceptions.RestrictedFonctionalityException;
import fr.unice.polytech.soa1.lab1.ws.PaymentService;
import fr.unice.polytech.soa1.lab1.ws.impl.PaymentServiceImpl;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by Tianhao on 10/6/2015.
 */
public class PaymentServiceTest extends TestCase {
    private PaymentService service = null;
    private int orderId;
    private Order order;

    protected void setUp() throws RestrictedFonctionalityException, RequestFailException, ContentNotFoundException {
        this.service = new PaymentServiceImpl();
        this.order = new Order();
        order.setCustomer((Customer)Storage.read(ContentType.CUSTOMER, 11));
        order.setDelivery((Delivery)Storage.read(ContentType.DELIVERY, 9));
        order.setPackaging((Package)Storage.read(ContentType.PACKAGE, 8));
        order.addItemToCart((Item) Storage.read(ContentType.ITEM, 4), 25);
        order.addItemToCart((Item) Storage.read(ContentType.ITEM, 2), 25);
        Storage.create(ContentType.ORDER, order);
        this.orderId = order.getId();

    }

    @Test
    public void test_calculateAmount() throws ContentNotFoundException {
        assertEquals(service.calculateAmount(this.orderId), 70.7, 0.0000001);
    }

    @Test
    public void test_payment() throws RestrictedFonctionalityException, RequestFailException, ContentNotFoundException {
        assertEquals(this.order.getStatus(), OrderStatus.NOT_VALIDATED);
        service.payOrder(this.orderId,"1234","tom.loeza@gmail.com");
        assertEquals(this.order.getStatus(), OrderStatus.IN_PREPARATION);
        Invoice invoice = service.issueInvoice(this.orderId,"tom.loeza@gmail.com");
        assertTrue(invoice.isPaid());
    }
}
