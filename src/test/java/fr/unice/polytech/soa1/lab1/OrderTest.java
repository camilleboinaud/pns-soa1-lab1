package fr.unice.polytech.soa1.lab1;

import fr.unice.polytech.soa1.lab1.business.Item;
import fr.unice.polytech.soa1.lab1.business.Order;
import fr.unice.polytech.soa1.lab1.utils.ContentType;
import fr.unice.polytech.soa1.lab1.utils.Pair;
import fr.unice.polytech.soa1.lab1.utils.exceptions.ContentNotFoundException;
import fr.unice.polytech.soa1.lab1.utils.exceptions.RequestFailException;
import fr.unice.polytech.soa1.lab1.utils.exceptions.RestrictedFonctionalityException;
import fr.unice.polytech.soa1.lab1.ws.OrderService;
import fr.unice.polytech.soa1.lab1.ws.impl.OrderServiceImpl;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.UUID;

/**
 * Created by Tianhao on 10/6/2015.
 */
public class OrderTest extends TestCase {
    private OrderService service = null;
    private Order initOrder = null;
    private int orderId;
    protected void setUp() {
        this.service = new OrderServiceImpl();
        this.initOrder = this.service.startOrder();
        this.orderId = this.initOrder.getId();
    }

    @Test
    public void test_updateCart() throws ContentNotFoundException, RequestFailException, RestrictedFonctionalityException {
        // init is 0
        assertEquals(this.initOrder.getCart().size(), 0);
        // add 100 item No.0
        this.service.updateItemToCart(this.orderId,0,100);
        this.service.updateItemToCart(this.orderId,1,50);
        assertEquals(this.initOrder.getCart().size(),2);
        // check
        Pair<Item, Integer> pair = this.service.displayCart(this.orderId).iterator().next();
        assertEquals(pair.getLeft().getId().intValue(),0);
        assertEquals(pair.getRight().intValue(),100);
        // remove item No.0
        this.service.removeItemFromCart(this.orderId,0);
        assertEquals(this.initOrder.getCart().size(),1);
        // empty cart
        assertEquals(this.service.emptyCart(this.orderId).getCart().size(), 0);
        assertEquals(this.initOrder.getCart().size(), 0);
    }

    @Test
    public void test_packaging() throws ContentNotFoundException, RestrictedFonctionalityException {
        // update packaging
        this.service.updatePackagingMode(orderId,7);
        // check
        assertEquals(this.initOrder.getPackaging().getId().intValue(),7);
    }

    @Test
    public void test_deleteOrder() throws RestrictedFonctionalityException {
        Order newOrder = service.startOrder();
        assertTrue(Storage.read(ContentType.ORDER,newOrder.getId())!=null);
        assertTrue(this.service.cancelOrder(newOrder.getId()));
        assertTrue(Storage.read(ContentType.ORDER, newOrder.getId()) == null);
    }


    /**
     * Private helpers
     */
/*
    private SimpleTaxRequest buildSimpleTaxRequest(float income){
        SimpleTaxRequest req = new SimpleTaxRequest();
        req.setIdentifier(UUID.randomUUID().toString());
        req.setIncome(income);
        return req;
    }*/


}
