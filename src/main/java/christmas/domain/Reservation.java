package christmas.domain;

import christmas.view.ErrorMessage;
import java.time.LocalDate;
import java.util.HashMap;

public class Reservation {

    private static final int MIN_ORDER_QUANTITY = 1;
    private static final int MAX_ORDER_QUANTITY = 20;

    private LocalDate visitDate;
    private HashMap<String, Integer> orderMenu;

    public void registerVisitDate(Calendar calendar, int day) {
        validateVisitDate(calendar, day);
        visitDate = LocalDate.of(calendar.getYear(), calendar.getMonth(), day);
    }

    public void registerOrderMenu(HashMap<String, Integer> separatedValue) {
        validateOrderMenu(separatedValue);
        validateMinOrderQuantity(separatedValue);
        validateMaxOrderQuantity(separatedValue);
        validateAllBeverage(separatedValue);
        orderMenu = separatedValue;

        System.out.println(orderMenu);
    }

    public int getVisitDay() {
        return visitDate.getDayOfMonth();
    }

    private void validateVisitDate(Calendar calendar, int day) {
        if (!calendar.isDateAvailable(day)) {
            throw new IllegalArgumentException(ErrorMessage.DATE_ERROR);
        }
    }

    private void validateOrderMenu(HashMap<String, Integer> separatedValue) {
        for (String menuName : separatedValue.keySet()) {
            if (!Menu.APPETIZER.isContain(menuName)
                    && !Menu.MAIN.isContain(menuName)
                    && !Menu.DESSERT.isContain(menuName)
                    && !Menu.BEVERAGE.isContain(menuName)) {
                throw new IllegalArgumentException(ErrorMessage.MENU_ERROR);
            }
        }
    }

    private void validateMinOrderQuantity(HashMap<String, Integer> separatedValue) {
        if (separatedValue.values().stream().anyMatch(n -> n < MIN_ORDER_QUANTITY)) {
            throw new IllegalArgumentException(ErrorMessage.MENU_ERROR);
        }
    }

    private void validateMaxOrderQuantity(HashMap<String, Integer> separatedValue) {
        int totalOrderQuantity = separatedValue.values().stream().mapToInt(Integer::intValue).sum();

        if (totalOrderQuantity > MAX_ORDER_QUANTITY) {
            throw new IllegalArgumentException(ErrorMessage.MENU_ERROR);
        }
    }

    private void validateAllBeverage(HashMap<String, Integer> separatedValue) {
        boolean isAllBeverageMenu = true;
        for (String menuName : separatedValue.keySet()) {
            if (!Menu.BEVERAGE.isContain(menuName)) {
                isAllBeverageMenu = false;
                break;
            }
        }
        if (isAllBeverageMenu) {
            throw new IllegalArgumentException(ErrorMessage.MENU_ERROR);
        }
    }
}
