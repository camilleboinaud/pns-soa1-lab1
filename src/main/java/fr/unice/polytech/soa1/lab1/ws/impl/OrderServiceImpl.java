package fr.unice.polytech.soa1.lab1.ws.impl;

import fr.unice.polytech.soa1.lab1.business.*;
import fr.unice.polytech.soa1.lab1.business.Package;
import fr.unice.polytech.soa1.lab1.utils.Pair;
import fr.unice.polytech.soa1.lab1.ws.OrderService;

import javax.jws.WebService;
import java.util.Collection;

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

    public Collection<Item> listItems() {
        return null;
    }

    public Item displayItem(Integer itemId) {
        return null;
    }

    public Collection<Package> listPackages() {
        return null;
    }

    public Item displayPackage(Integer packageId) {
        return null;
    }

    public boolean addItemToCart(Integer orderId, Integer itemId, Integer quantity) {
        return false;
    }

    public boolean updateItemsIntoCart(Integer orderId, Integer itemId, Integer quantity) {
        return false;
    }

    public boolean updatePackagingMode(Integer orderId, Integer packageId) {
        return false;
    }

    public Collection<Pair<Item, Integer>> displayCart(Integer orderId) {
        return null;
    }

    public boolean emptyCart(Integer orderId) {
        return false;
    }

    public Order displayOrder(Integer orderId) {
        return null;
    }

    public boolean cancelOrder(Integer orderId) {
        return false;
    }

}
