package fr.unice.polytech.soa1.lab1.business;

import fr.unice.polytech.soa1.lab1.Storage;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by camille on 28/09/15.
 */

@XmlType(name = "storable_content")
public class StorableContent {

    private Integer id = null;

    public StorableContent(){
        this.id = Storage._INDEX++;
    }

    @XmlElement(required = true)
    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

}
