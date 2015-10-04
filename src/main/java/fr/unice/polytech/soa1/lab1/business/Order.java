package fr.unice.polytech.soa1.lab1.business;

import fr.unice.polytech.soa1.lab1.utils.Pair;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.HashMap;

/**
 * Created by camille on 28/09/15.
 */

@XmlType(name = "order")
public class Order extends StorableContent{

    private HashMap<Integer, Pair<Item, Integer>> cart = null;
    private Customer customer;
    private Package packaging = null;
    private Delivery delivery = null;

    public Order(){
        super();
        this.cart = new HashMap<Integer, Pair<Item, Integer>>();
    }

    @XmlElement(required = true)
    public HashMap<Integer, Pair<Item, Integer>> getCart() {
        return cart;
    }

    public void addToCart(Item item, Integer number){
        if(cart.containsKey(item.getId())){
            number += cart.get(item.getId()).getRight();
            cart.remove(item.getId());
        }
        cart.put(item.getId(), new Pair<Item, Integer>(item, number));
    }

    @XmlElement(required = true)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    @XmlElement(required = true)
    public Package getPackaging() {
        return packaging;
    }

    public void setPackaging(Package packaging) {
        this.packaging = packaging;
    }

    @XmlElement(required = true)
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
