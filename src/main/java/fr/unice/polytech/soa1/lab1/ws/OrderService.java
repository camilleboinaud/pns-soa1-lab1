package fr.unice.polytech.soa1.lab1.ws;

import fr.unice.polytech.soa1.lab1.business.*;
import fr.unice.polytech.soa1.lab1.business.Package;
import fr.unice.polytech.soa1.lab1.utils.Pair;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.Collection;

/**
 * Created by camille on 04/10/15.
 */

@WebService(name="OrderService")
public interface OrderService {

    @WebResult(name = "OrderService_list_items")
    Collection<Item> listItems();

    @WebResult(name = "OrderService_display_item")
    Item displayItem(
            @WebParam(name = "itemId") Integer itemId
    );

    @WebResult(name = "OrderService_list_packages")
    Collection<Package> listPackages();

    @WebResult(name = "OrderService_display_item")
    Item displayPackage(
            @WebParam(name = "packageId") Integer packageId
    );

    @WebResult(name = "OrderService_add_item_cart")
    boolean addItemToCart(
            @WebParam(name = "orderId") Integer orderId,
            @WebParam(name = "itemId") Integer itemId,
            @WebParam(name = "quantity") Integer quantity
    );

    @WebResult(name = "OrderService_update_item_cart")
    boolean updateItemsIntoCart(
            @WebParam(name = "orderId") Integer orderId,
            @WebParam(name = "itemId") Integer itemId,
            @WebParam(name = "quantity") Integer quantity
    );

    @WebResult(name = "OrderService_update_packaging")
    boolean updatePackagingMode(
            @WebParam(name = "orderId") Integer orderId,
            @WebParam(name = "packageId") Integer packageId
    );

    @WebResult(name = "OrderService_display_cart")
    Collection<Pair<Item, Integer>> displayCart(
            @WebParam(name = "orderId") Integer orderId
    );

    @WebResult(name = "OrderService_empty_cart")
    boolean emptyCart(
            @WebParam(name = "orderId") Integer orderId
    );

    @WebResult(name = "OrderService_display_order")
    Order displayOrder(
            @WebParam(name = "orderId") Integer orderId
    );

    @WebResult(name = "OrderService_cancel_order")
    boolean cancelOrder(
            @WebParam(name = "orderId") Integer orderId
    );


}
