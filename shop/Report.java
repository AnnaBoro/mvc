package lesson6_9.adapter.mvc.shop;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Report {

    Store store;

    public Report (Store store) {
        this.store = store;
    }

    public ArrayList<String> getPrice () {

        ArrayList<String> priceList = new ArrayList<String>();

        for (Bird b : store.getBirds()) {
            if (b != null) {
                priceList.add(b.getName() + " " + b.getPrice());
            }
        }

        return priceList;
    }

    public ArrayList<String> getBirdsOnStock () {

        ArrayList<String> birdsOnStock = new ArrayList<String>();

        for (Bird b : store.getBirds()) {

            birdsOnStock.add(b.getName() + ": " + b.getQuantity());
        }

        return birdsOnStock;
    }

//    public int[] getPurchasesInLast7Days() {
//
//
//    }

    public void printListTodayPurchases() {

        List<Purchase> purchases = store.getPurchases();
        long today = getToday();
        int numPurchase = 0;
        int todayProfit = 0;
        int numSoldBirds = 0;

        System.out.println("__________Today Purchases:");
        System.out.println("№  Buyer  Birds  Price  Number");
//        for (Purchase p : purchases) {
//            if (p.getDate().getTime().getTime() > today) {
//                numSoldBirds += numSoldBirds + p.getNumber();
//                todayProfit += todayProfit + p.getNumber() * p.getBird().getPrice();
//                numPurchase++;
//                System.out.println(numPurchase + " " + p.getBuyer().getName() + " " + p.getBird().getName() + " " +
//                        p.getBird().getPrice() + " " + p.getNumber());
//            }
//        }
        System.out.println("In total " + numPurchase + " purchases    " + todayProfit + "   " + numSoldBirds);
    }

    public Long getToday() {

        Date dt1 = new Date();
        SimpleDateFormat c = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
        String d1 =  c.format(dt1);
        Date today = null;
        try {
            today = c.parse(d1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long todayWithoutHours = today.getTime();
        return todayWithoutHours;
    }

    public void printCatalogByCategory(Category category) {

        System.out.println("__________Catalog by Category: " + category.toString());
        System.out.println("Bird  Price  Number");
        for (Bird b : store.getBirds()) {
            if (category != null && b.getCategory().toString().equalsIgnoreCase(category.toString())) {
                System.out.println(b.getName() + ":     " + b.getPrice() + "     " + b.getQuantity());
            }
        }
    }

    public Store getStore() {
        return store;
    }
}
