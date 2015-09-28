package main.java.fr.unice.polytech.si5.soa1.lab1.business;

import main.java.fr.unice.polytech.si5.soa1.lab1.Storage;

import java.util.Calendar;

/**
 * Created by camille on 28/09/15.
 */
public class Bill extends StorableContent{

    private Calendar createdAt = Calendar.getInstance();
    private Calendar updatedAt = Calendar.getInstance();
    private Order order = null;
    private boolean paid = false;

    public Bill(Order order){
        super();
        this.order = order;
    }

    public void setPaid(){
        this.paid = true;
        this.updatedAt = Calendar.getInstance();
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public Calendar getUpdatedAt() {
        return updatedAt;
    }

    public Order getOrder() {
        return order;
    }

    public boolean isPaid() {
        return paid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bill)) return false;

        Bill bill = (Bill) o;

        if (isPaid() != bill.isPaid()) return false;
        if (getCreatedAt() != null ? !getCreatedAt().equals(bill.getCreatedAt()) : bill.getCreatedAt() != null)
            return false;
        if (getUpdatedAt() != null ? !getUpdatedAt().equals(bill.getUpdatedAt()) : bill.getUpdatedAt() != null)
            return false;
        return !(getOrder() != null ? !getOrder().equals(bill.getOrder()) : bill.getOrder() != null);

    }

    @Override
    public int hashCode() {
        int result = getCreatedAt() != null ? getCreatedAt().hashCode() : 0;
        result = 31 * result + (getUpdatedAt() != null ? getUpdatedAt().hashCode() : 0);
        result = 31 * result + (getOrder() != null ? getOrder().hashCode() : 0);
        result = 31 * result + (isPaid() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", order=" + order +
                ", paid=" + paid +
                '}';
    }
}
