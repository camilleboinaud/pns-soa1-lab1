package main.java.fr.unice.polytech.si5.soa1.lab1.business;

import main.java.fr.unice.polytech.si5.soa1.lab1.Storage;

/**
 * Created by camille on 28/09/15.
 */
public class StorableContent {

    public Integer id = null;

    protected StorableContent(){
        this.id = Storage._INDEX++;
    }

    public Integer getId(){
        return id;
    }

}
