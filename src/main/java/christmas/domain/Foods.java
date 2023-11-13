package christmas.domain;

import java.util.HashMap;

public enum Foods {

    APPETIZER(createAppetizer()),
    MAIN(createMain()),
    DESSERT(createDessert()),
    BEVERAGE(createBeverage());

    private final HashMap<String, Integer> foodInfo;

    Foods(HashMap<String, Integer> foodInfo) {
        this.foodInfo = foodInfo;
    }

    public int getPrice(String menuName) {
        return foodInfo.get(menuName);
    }

    public boolean isContain(String menuName) {
        return foodInfo.containsKey(menuName);
    }

    private static HashMap<String, Integer> createAppetizer() {
        HashMap<String, Integer> appetizer = new HashMap<>();
        appetizer.put("양송이수프", 6_000);
        appetizer.put("타파스", 5_500);
        appetizer.put("시저샐러드", 8_000);
        return appetizer;
    }

    private static HashMap<String, Integer> createMain() {
        HashMap<String, Integer> main = new HashMap<>();
        main.put("티본스테이크", 55_000);
        main.put("바비큐립", 54_000);
        main.put("해산물파스타", 35_000);
        main.put("크리스마스파스타", 25_000);
        return main;
    }

    private static HashMap<String, Integer> createDessert() {
        HashMap<String, Integer> dessert = new HashMap<>();
        dessert.put("초코케이크", 15_000);
        dessert.put("아이스크림", 5_000);
        return dessert;
    }

    private static HashMap<String, Integer> createBeverage() {
        HashMap<String, Integer> beverage = new HashMap<>();
        beverage.put("제로콜라", 3_000);
        beverage.put("레드와인", 60_500);
        beverage.put("샴페인", 25_000);
        return beverage;
    }
}
