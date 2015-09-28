package main.java.fr.unice.polytech.si5.soa1.lab1.business;

import main.java.fr.unice.polytech.si5.soa1.lab1.Storage;
import org.json.JSONArray;

/**
 * Created by camille on 28/09/15.
 */
public class Customer extends StorableContent{

    private String firstname = null;
    private String lastname = null;
    private String address = null;
    private String zipcode = null;
    private String city = null;
    private String country = null;
    private String phone = null;
    private String email = null;

    public Customer(String firstname, String lastname, String address, String zipcode,
                    String city, String country, String phone, String email){
        super();
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.country = country;
        this.phone = phone;
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;

        Customer customer = (Customer) o;

        if (getFirstname() != null ? !getFirstname().equals(customer.getFirstname()) : customer.getFirstname() != null)
            return false;
        if (getLastname() != null ? !getLastname().equals(customer.getLastname()) : customer.getLastname() != null)
            return false;
        if (getAddress() != null ? !getAddress().equals(customer.getAddress()) : customer.getAddress() != null)
            return false;
        if (getZipcode() != null ? !getZipcode().equals(customer.getZipcode()) : customer.getZipcode() != null)
            return false;
        if (getCity() != null ? !getCity().equals(customer.getCity()) : customer.getCity() != null) return false;
        if (getCountry() != null ? !getCountry().equals(customer.getCountry()) : customer.getCountry() != null)
            return false;
        if (getPhone() != null ? !getPhone().equals(customer.getPhone()) : customer.getPhone() != null) return false;
        return !(getEmail() != null ? !getEmail().equals(customer.getEmail()) : customer.getEmail() != null);

    }

    @Override
    public int hashCode() {
        int result = getFirstname() != null ? getFirstname().hashCode() : 0;
        result = 31 * result + (getLastname() != null ? getLastname().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getZipcode() != null ? getZipcode().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
