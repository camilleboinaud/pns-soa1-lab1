package fr.unice.polytech.soa1.lab1;

import fr.unice.polytech.soa1.lab1.business.*;
import fr.unice.polytech.soa1.lab1.business.Package;
import fr.unice.polytech.soa1.lab1.utils.ContentType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by camille on 28/09/15.
 */
public class Storage {

    public static Integer _INDEX = 0;

    private static HashMap<ContentType, Stock> database = new HashMap<ContentType, Stock>();

    public static <T extends StorableContent> T create(ContentType type, StorableContent object){
        return (T) database.get(type).create(object);
    }

    public static boolean delete(ContentType type, Integer id){
        return database.get(type).delete(id);
    }

    public static StorableContent read(ContentType type, Integer index){
        return database.get(type).read(index);
    }

    public static <S extends StorableContent> Collection<S> findAll(ContentType... types){
        Collection<S> collection = new ArrayList<S>();
        for(ContentType type : types){
            collection.addAll(database.get(type).getAll());
        }
        return collection;
    }


    private static class Stock<T extends StorableContent>{

        HashMap<Integer, T> stock;

        Stock(){
            stock = new HashMap<Integer, T>();
        }

        T create(T t){
            if(!stock.containsValue(t)){
                stock.put(t.getId(), t);
            }
            return t;
        }

        boolean delete(Integer id){
            if(stock.containsKey(id)){
                stock.remove(id);
                return true;
            }
            return false;
        }

        T read(Integer id){
            return (stock.containsKey(id)) ? stock.get(id) : null;
        }

        Collection<T> getAll(){
            return stock.values();
        }

    }

    static {

        database.put(ContentType.CUSTOMER, new Stock<Customer>());
        database.put(ContentType.DELIVERY, new Stock<Delivery>());
        database.put(ContentType.INVOICE, new Stock<Invoice>());
        database.put(ContentType.ITEM, new Stock<Item>());
        database.put(ContentType.ORDER, new Stock<Order>());
        database.put(ContentType.PACKAGE, new Stock<Package>());

        //Items
        //0:
        create(ContentType.ITEM, new Item(1.3, "Candy1", "A full candy1 description which tempts you to eat it now."));
        //1:
        create(ContentType.ITEM, new Item(1.7, "Candy2", "A full candy2 description which tempts you to eat it now."));
        //2:
        create(ContentType.ITEM, new Item(2.2, "Candy3", "A full candy3 description which tempts you to eat it now."));
        //3:
        create(ContentType.ITEM, new Item(2.9, "Candy4", "A full candy4 description which tempts you to eat it now."));
        //4:
        create(ContentType.ITEM, new Item(0.26, "Candy5", "A full candy5 description which tempts you to eat it now."));
        //5:
        create(ContentType.ITEM, new Item(0.19, "Candy6", "A full candy6 description which tempts you to eat it now."));

        //Packages
        //6:
        create(ContentType.PACKAGE, new Package("BAG", 0.1, "Package candies into individual plastic bags"));
        //7:
        create(ContentType.PACKAGE, new Package("CARDBOARD", 0.2, "Package candies into individual cardboard boxes"));
        //8:
        create(ContentType.PACKAGE, new Package("BOX", 1.2, "Package candies into individual metal boxes"));

        //Delivery Modes
        //9:
        create(ContentType.DELIVERY, new Delivery("FAST", 8.0, "Get your package delivered in 24h"));
        //10:
        create(ContentType.DELIVERY, new Delivery("ECONOMIC", 2.0, "Get your package delivered in a week"));

        //Customers
        //11:
        create(ContentType.CUSTOMER, new Customer("Tom", "Loeza", "447 Commercial St Se", "J7V 4T4", "LIle-Perrot", "Canada", "514-487-6096", "tom.loeza@gmail.com"));
        //12:
        create(ContentType.CUSTOMER, new Customer("Lourdes", "Bauswell", "9547 Belmont Rd #21", "K8P 1B3", "Belleville", "Canada", "613-903-7043", "lourdes_bauswell@aol.com"));

        //Orders
        Order order = new Order();
        order.setCustomer((Customer)read(ContentType.CUSTOMER,11));
        order.setDelivery((Delivery)read(ContentType.DELIVERY, 9));
        order.setPackaging((Package)read(ContentType.PACKAGE, 8));
        order.addItemToCart((Item) read(ContentType.ITEM, 4), 25);
        order.addItemToCart((Item) read(ContentType.ITEM, 2), 25);
        //13:
        create(ContentType.ORDER, order);

    }



}
