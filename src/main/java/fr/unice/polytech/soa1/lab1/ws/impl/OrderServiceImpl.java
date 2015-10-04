package fr.unice.polytech.soa1.lab1.ws.impl;

import fr.unice.polytech.soa1.lab1.Storage;
import fr.unice.polytech.soa1.lab1.business.*;
import fr.unice.polytech.soa1.lab1.business.Package;
import fr.unice.polytech.soa1.lab1.utils.ContentType;
import fr.unice.polytech.soa1.lab1.utils.Pair;
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
     * Returns a collection containing all candies available
     * @return
     */
    public Collection<Item> listItems() {
        return Storage.findAll(ContentType.ITEM);
    }

    /**
     * Displays a specified candy with all its details
     * @param itemId
     * @return
     */
    public Item displayItem(Integer itemId) {
        return (Item) Storage.read(ContentType.ITEM, itemId);
    }

    /**
     * Returns a collection of packaging solutions offered by minibo company
     * @return
     */
    public Collection<Package> listPackages() {
        return Storage.findAll(ContentType.PACKAGE);
    }

    /**
     * Displays a specified packaging solution
     * @param packageId
     * @return
     */
    public Package displayPackage(Integer packageId) {
        return (Package) Storage.read(ContentType.PACKAGE, packageId);
    }

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
    public Order updateItemToCart(Integer orderId, Integer itemId, Integer quantity) {
        Order order = (Order) Storage.read(ContentType.ORDER, orderId);
        if(order.addItemToCart((Item) Storage.read(ContentType.ITEM, itemId), quantity)) {
            return order;
        }
        return null;
    }

    /**
     * This method removes from cart the specified item.
     * @param orderId
     * @param itemId
     * @return
     */
    public Order removeItemFromCart(Integer orderId, Integer itemId) {
        Order order = (Order) Storage.read(ContentType.ORDER, orderId);
        if(order.removeItemFromCart((Item)Storage.read(ContentType.ITEM, itemId))) {
            return order;
        }

        return null;
    }

    /**
     * Select a packaging mode (if not specified during the order process, one will be selected
     * by default)
     * @param orderId
     * @param packageId
     */
    public boolean updatePackagingMode(Integer orderId, Integer packageId) {
        Order order = (Order) Storage.read(ContentType.ORDER, orderId);
        order.setPackaging((Package) Storage.read(ContentType.PACKAGE, packageId));
        return true;
    }

    /**
     * Returns a collection of pair composed by items and their quantities
     * @param orderId
     * @return
     */
    public Collection<Pair<Item, Integer>> displayCart(Integer orderId) {
        Order order = (Order) Storage.read(ContentType.ORDER, orderId);
        return order.getCart();
    }

    /**
     * Empty the cart associated to the specified order
     * @param orderId
     */
    public boolean emptyCart(Integer orderId) {
        ((Order) Storage.read(ContentType.ORDER, orderId))
                .setCart(new ArrayList<Pair<Item, Integer>>());
        return true;
    }

    /**
     * Returns the order object
     * @param orderId
     * @return
     */
    public Order displayOrder(Integer orderId) {
        return (Order) Storage.read(ContentType.ORDER, orderId);
    }

    /**
     * Cancel the specified order.
     * Be careful, a canceled order cannot be recovered.
     * @param orderId
     */
    public boolean cancelOrder(Integer orderId) {
        Storage.delete(ContentType.ORDER, orderId);
        return true;
    }

}
