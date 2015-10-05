package fr.unice.polytech.soa1.lab1.business;


import fr.unice.polytech.soa1.lab1.Storage;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by camille on 28/09/15.
 */

@XmlType(name = "storable_content")
@XmlSeeAlso({ Customer.class, Delivery.class, Invoice.class, Item.class, Order.class, Package.class})
public class StorableContent {

    private Integer id = null;

    public StorableContent(){
        this.id = Storage._INDEX++;
    }

    @XmlElement(required = false)
    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

}
