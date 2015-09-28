package main.java.fr.unice.polytech.si5.soa1.lab1.business;

import main.java.fr.unice.polytech.si5.soa1.lab1.Storage;
import main.java.fr.unice.polytech.si5.soa1.lab1.utils.Pair;

/**
 * Created by camille on 28/09/15.
 */
public class Package extends StorableContent {

    private String type = null;
    private Double price = null;
    private String description = null;

    public Package(String type, Double price, String description){
        super();
        this.type = type;
        this.price = price;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Package)) return false;

        Package aPackage = (Package) o;

        if (getType() != null ? !getType().equals(aPackage.getType()) : aPackage.getType() != null) return false;
        if (getPrice() != null ? !getPrice().equals(aPackage.getPrice()) : aPackage.getPrice() != null) return false;
        return !(getDescription() != null ? !getDescription().equals(aPackage.getDescription()) : aPackage.getDescription() != null);

    }

    @Override
    public int hashCode() {
        int result = getType() != null ? getType().hashCode() : 0;
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Package{" +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
