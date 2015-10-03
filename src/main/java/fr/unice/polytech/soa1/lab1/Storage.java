package fr.unice.polytech.soa1.lab1;

import fr.unice.polytech.soa1.lab1.business.StorableContent;
import fr.unice.polytech.soa1.lab1.utils.ContentType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by camille on 28/09/15.
 */
public class Storage {

    public static Integer _INDEX = 0;

    private static HashMap<ContentType, Map<Integer, StorableContent>> database = new HashMap<ContentType, Map<Integer, StorableContent>>();

    public static boolean create(ContentType type, StorableContent object){
        if(database.get(type).containsValue(object)){
            return false;
        }
        database.get(type).put(object.getId(), object);
        return true;
    }

    public static boolean delete(ContentType type, Object object){
        if(!database.get(type).containsValue(object)){
            return false;
        }
        database.get(type).remove(object);
        return true;
    }

    public static StorableContent read(ContentType type, Integer index){
        return database.get(type).get(index);
    }

    public static Collection<StorableContent> findAll(ContentType type){
        return database.get(type).values();
    }

    static {
        //TODO implement some test case to fill database
    }



}
