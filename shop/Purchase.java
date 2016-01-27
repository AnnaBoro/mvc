package lesson6_9.adapter.mvc.shop;

import java.util.Calendar;
import java.util.Date;

public class Purchase {

    private Bird bird;
    private Buyer buyer;
    private Date date;
    private int number;

    public Purchase() {
    }

    public Purchase(Bird bird, Buyer buyer, Date date, int number) {
        this.bird = bird;
        this.buyer = buyer;
        this.date = date;
        this.number = number;
    }

    public Bird getBird() {
        return bird;
    }

    public void setBird(Bird bird) {
        this.bird = bird;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
