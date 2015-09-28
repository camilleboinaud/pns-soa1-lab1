package main.java.fr.unice.polytech.si5.soa1.lab1.business;

import main.java.fr.unice.polytech.si5.soa1.lab1.Storage;
import main.java.fr.unice.polytech.si5.soa1.lab1.utils.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by camille on 28/09/15.
 */
public class Order extends StorableContent{

    private Map<Integer, Pair<Item, Integer>> cart = null;
    private Customer customer = null;
    private Package packaging = null;
    private Delivery delivery = null;

    public Order(){
        super();
        this.cart = new HashMap<>();
    }

    public Map<Integer, Pair<Item, Integer>> getCart() {
        return cart;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Package getPackaging() {
        return packaging;
    }

    public void setPackaging(Package packaging) {
        this.packaging = packaging;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (getCart() != null ? !getCart().equals(order.getCart()) : order.getCart() != null) return false;
        if (getCustomer() != null ? !getCustomer().equals(order.getCustomer()) : order.getCustomer() != null)
            return false;
        if (getPackaging() != null ? !getPackaging().equals(order.getPackaging()) : order.getPackaging() != null)
            return false;
        return !(getDelivery() != null ? !getDelivery().equals(order.getDelivery()) : order.getDelivery() != null);

    }

    @Override
    public int hashCode() {
        int result = getCart() != null ? getCart().hashCode() : 0;
        result = 31 * result + (getCustomer() != null ? getCustomer().hashCode() : 0);
        result = 31 * result + (getPackaging() != null ? getPackaging().hashCode() : 0);
        result = 31 * result + (getDelivery() != null ? getDelivery().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "cart=" + cart +
                ", customer=" + customer +
                ", packaging=" + packaging +
                ", delivery=" + delivery +
                '}';
    }
}
