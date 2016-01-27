package lesson6_9.adapter.mvc.shop;


public class Launcher {

    public static void main(String[] args) {

        Store store = new Store();
        new StoreUI(store);
    }
}
