package fr.unice.polytech.soa1.lab1;

import fr.unice.polytech.soa1.lab1.business.Customer;
import fr.unice.polytech.soa1.lab1.business.Order;
import fr.unice.polytech.soa1.lab1.utils.ContentType;
import fr.unice.polytech.soa1.lab1.utils.exceptions.ContentNotFoundException;
import fr.unice.polytech.soa1.lab1.utils.exceptions.RequestFailException;
import fr.unice.polytech.soa1.lab1.utils.exceptions.RestrictedFonctionalityException;
import fr.unice.polytech.soa1.lab1.ws.DeliveryService;
import fr.unice.polytech.soa1.lab1.ws.OrderService;
import fr.unice.polytech.soa1.lab1.ws.impl.DeliveryServiceImpl;
import fr.unice.polytech.soa1.lab1.ws.impl.OrderServiceImpl;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by Tianhao on 10/6/2015.
 */
public class DeliveryServiceTest extends TestCase {
    private DeliveryService service = null;
    private int orderId;

    protected void setUp() {
        service = new DeliveryServiceImpl();
        OrderService orderService = new OrderServiceImpl();
        orderId = orderService.startOrder().getId();
    }

    @Test
    public void test_provideCustomerShipmentData()
            throws RestrictedFonctionalityException, RequestFailException,
            ContentNotFoundException {
        Customer customer1 = service.provideCustomerShipmentData(orderId, "tianhao", "shi", "polytech",
                "06560", "valbonne", "Fr", "+33667628870",
                "tianhao.shi@etu.unice.fr");
        Customer customer2 = (Customer) Storage.read(ContentType.CUSTOMER, customer1.getId());
        assertEquals(customer1,customer2);
    }

    @Test
    public void test_chooseDeliveryMode()
            throws RestrictedFonctionalityException, RequestFailException, ContentNotFoundException {
        service.chooseDeliveryMode(orderId,10);
        Order order = (Order) Storage.read(ContentType.ORDER,orderId);
        assertEquals(order.getDelivery().getId().intValue(),10);
    }
}
