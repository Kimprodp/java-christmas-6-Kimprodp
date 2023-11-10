package christmas.domain;

import java.util.Arrays;
import java.util.List;

public class Menu {

    private final List<Foods> categories;

    public Menu() {
        categories = Arrays.asList(Foods.APPETIZER, Foods.MAIN, Foods.DESSERT, Foods.BEVERAGE);
    }

    public boolean isContain(String foodName) {
        return categories.stream()
                .anyMatch(category -> category.isContain(foodName));
    }

    public boolean isAllBeverage(String foodName) {
       return categories.stream()
                .filter(category -> category.equals(Foods.BEVERAGE))
                .anyMatch(category -> category.isContain(foodName));
    }

    public int getPrice(String foodName) {
        return categories.stream()
                .filter(category -> category.isContain(foodName))
                .findFirst()
                .map(category -> category.getPrice(foodName))
                .orElse(0);
    }
}
