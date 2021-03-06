package fr.unice.polytech.soa1.lab1.ws.impl;

import fr.unice.polytech.soa1.lab1.Storage;
import fr.unice.polytech.soa1.lab1.business.*;
import fr.unice.polytech.soa1.lab1.business.Package;
import fr.unice.polytech.soa1.lab1.utils.ContentType;
import fr.unice.polytech.soa1.lab1.utils.OrderStatus;
import fr.unice.polytech.soa1.lab1.utils.Pair;
import fr.unice.polytech.soa1.lab1.utils.exceptions.ContentNotFoundException;
import fr.unice.polytech.soa1.lab1.utils.exceptions.RequestFailException;
import fr.unice.polytech.soa1.lab1.utils.exceptions.RestrictedFonctionalityException;
import fr.unice.polytech.soa1.lab1.ws.OrderService;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by camille on 04/10/15.
 */

@WebService(
        targetNamespace   = "http://informatique.polytech.unice.fr/soa1/lab1/ws",
        portName          = "OrderServicePort",
        serviceName       = "OrderService",
        endpointInterface = "fr.unice.polytech.soa1.lab1.ws.OrderService"
)
public class OrderServiceImpl implements OrderService {

    /**
     * Mehtod used to start a new order (initialises all process)
     * @return
     */
    public Order startOrder() {
        Order order = new Order();
        Storage.create(ContentType.ORDER, order);
        return order;
    }

    /**
     * Add an item to cart, quantity must be positive otherwise function returns a null object.
     * If the item has already been added into cart, its quantity will be replaced by the one
     * specified in parameters.
     * @param orderId
     * @param itemId
     * @param quantity
     * @return
     */
    public Order updateItemToCart(Integer orderId, Integer itemId, Integer quantity)
            throws ContentNotFoundException, RequestFailException, RestrictedFonctionalityException {
        Order order = (Order) Storage.read(ContentType.ORDER, orderId);
        if(order != null) {
            if(order.getStatus().equals(OrderStatus.NOT_VALIDATED)){
                if (order.addItemToCart((Item) Storage.read(ContentType.ITEM, itemId), quantity)) {
                    return Storage.merge(ContentType.ORDER, order);
                } else {
                    throw new RequestFailException("Resquest failed : abort. Please check parameters validity.");
                }
            } else {
                throw new RestrictedFonctionalityException("Your order has already been validated");
            }

        } else {
            throw new ContentNotFoundException("The specified order does not exist");
        }
    }

    /**
     * This method removes from cart the specified item.
     * @param orderId
     * @param itemId
     * @return
     */
    public Order removeItemFromCart(Integer orderId, Integer itemId)
            throws ContentNotFoundException, RequestFailException, RestrictedFonctionalityException {

        Order order = (Order) Storage.read(ContentType.ORDER, orderId);

        if(order != null) {
            if(order.getStatus().equals(OrderStatus.NOT_VALIDATED)){
                if (order.removeItemFromCart((Item) Storage.read(ContentType.ITEM, itemId))) {
                    return order;
                } else {
                    throw new RequestFailException("Resquest failed : abort. Please check parameters validity.");
                }
            } else {
                throw new RestrictedFonctionalityException("Your order has already been validated");
            }

        } else {
            throw new ContentNotFoundException("The specified order does not exist");
        }
    }

    /**
     * Select a packaging mode (if not specified during the order process, one will be selected
     * by default)
     * @param orderId
     * @param packageId
     */
    public Order updatePackagingMode(Integer orderId, Integer packageId)
            throws ContentNotFoundException, RestrictedFonctionalityException {

        Order order = (Order) Storage.read(ContentType.ORDER, orderId);

        if(order != null) {
            if(order.getStatus().equals(OrderStatus.NOT_VALIDATED)){
                order.setPackaging((Package) Storage.read(ContentType.PACKAGE, packageId));
                return Storage.merge(ContentType.ORDER, order);
            } else {
                throw new RestrictedFonctionalityException("Your order has already been validated");
            }

        } else  {
            throw new ContentNotFoundException("The specified order does not exist");
        }
    }

    /**
     * Returns a collection of pair composed by items and their quantities
     * @param orderId
     * @return
     */
    public Collection<Pair<Item, Integer>> displayCart(Integer orderId) throws ContentNotFoundException{
        Order order = (Order) Storage.read(ContentType.ORDER, orderId);
        if(order != null) {
            return order.getCart();
        } else {
            throw new ContentNotFoundException("The specified order does not exist");
        }
    }

    /**
     * Empty the cart associated to the specified order
     * @param orderId
     */
    public Order emptyCart(Integer orderId) throws ContentNotFoundException, RestrictedFonctionalityException {
        Order order = (Order) Storage.read(ContentType.ORDER, orderId);
        if(order != null) {
            if(order.getStatus().equals(OrderStatus.NOT_VALIDATED)){
                order.setCart(new ArrayList<Pair<Item, Integer>>());
                return Storage.merge(ContentType.ORDER, order);
            } else {
                throw new RestrictedFonctionalityException("Your order has already been validated");
            }

        } else {
            throw new ContentNotFoundException("The specified order does not exist");
        }
    }

    /**
     * Cancel the specified order.
     * Be careful, a canceled order cannot be recovered.
     * @param orderId
     */
    public boolean cancelOrder(Integer orderId) throws RestrictedFonctionalityException {
        Order order = (Order)Storage.read(ContentType.ORDER, orderId);

        if(order != null && order.getStatus().equals(OrderStatus.NOT_VALIDATED)){
            Storage.delete(ContentType.ORDER, orderId);
            return true;
        } else {
            throw new RestrictedFonctionalityException("Your order has already been validated");
        }


    }

    /**
     * This method can be called to track order status at any moment during order process.
     * @param orderId
     * @param email
     * @return
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public OrderStatus trackOrderStatus(int orderId, String email) throws ContentNotFoundException,RequestFailException {
        if (orderId < 0 ) {
            throw new RequestFailException("orderId cannot be negative");
        }
        if (email == null) {
            throw new RequestFailException("Email cannot be empty");
        }

        Order orderFound = (Order) Storage.read(ContentType.ORDER,orderId);
        if (orderFound != null) {
            Customer customerInOrder = orderFound.getCustomer();
            if (customerInOrder != null && (email.equals(customerInOrder.getEmail()))) {
                return orderFound.getStatus();
            } else {
                throw new ContentNotFoundException("cannot find the customer in the order " + orderId);
            }
        } else {
            throw new ContentNotFoundException("cannot find the order with orderId = "+orderId);
        }
    }

}
