package lesson6_9.adapter.mvc.shop;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Store {

    private List<Bird> birds = new ArrayList<Bird>();
    private List<Buyer> buyers = new ArrayList<Buyer>();
    private List<Purchase> purchases = new ArrayList<Purchase>();

    public Store() {

        initStore();
    }

    public void sell (String birdName, int num, Buyer b) {

        int numPurchase = 0;
        for (Bird bird : birds) {
            if (bird.getName().equalsIgnoreCase(birdName)) {
                if (bird.getQuantity() < num) {
                    System.out.println("There is not such quantity of birds");
                }
                else {
                    buyers.add(b);
                    Purchase p = new Purchase(bird, b, new Date(), num);
                    numPurchase = purchases.size() + 1;
                    purchases.add(p);
                    bird.setQuantity(bird.getQuantity() - num);
                    bird.setSoldQuantity(bird.getSoldQuantity() + num);
                    System.out.println(numPurchase + " " + b.getName() + " buy " + num + " " + birdName);
                }
            }
        }
    }

    public void addBird (Bird bird) {


        birds.add(bird);
    }

    public void addBuyer (Buyer buyer) {

        buyers.add(buyer);
    }

    public void addPurchase (Purchase purchase) {

        purchases.add(purchase);
    }

    public Buyer createBuyer (String name, String email, int phoneNumber) {

        return new Buyer(name, email, phoneNumber);
    }

    public void initStore() {

        Bird b1 = new Owl();
        b1.setPrice(15);
        b1.setCategory(Category.WILD);
        b1.setQuantity(10);
        addBird(b1);

        b1 = new Hen();
        b1.setPrice(5);
        b1.setCategory(Category.DOMESTIC);
        b1.setQuantity(8);
        addBird(b1);

        b1 = new Eagle();
        b1.setPrice(35);
        b1.setCategory(Category.WILD);
        b1.setQuantity(10);
        addBird(b1);
    }

    public List<Bird> getBirds() {
        return birds;
    }

    public List<Buyer> getBuyers() {
        return buyers;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }
}
