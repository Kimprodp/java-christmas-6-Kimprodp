package christmas.domain;

import christmas.view.ErrorMessage;
import java.time.LocalDate;
import java.util.LinkedHashMap;

public class Reservation {

    private static final int MIN_ORDER_QUANTITY = 1;
    private static final int MAX_ORDER_QUANTITY = 20;

    private LocalDate visitDate;
    private LinkedHashMap<String, Integer> orderMenu;
    private int orderAmount;

    public void registerVisitDate(Calendar calendar, int day) {
        validateVisitDate(calendar, day);
        visitDate = LocalDate.of(calendar.getYear(), calendar.getMonth(), day);
    }

    public void registerOrderMenu(Menu menu, LinkedHashMap<String, Integer> orderMenu) {
        validateAvailableOrder(menu, orderMenu);
        validateAllBeverage(menu, orderMenu);
        validateMinOrderQuantity(orderMenu);
        validateMaxOrderQuantity(orderMenu);
        this.orderMenu = orderMenu;
    }

    public int getVisitDay() {
        return visitDate.getDayOfMonth();
    }

    public LinkedHashMap<String, Integer> getOrderMenu() {
        return orderMenu;
    }

    public void calculateOrderAmount(Menu menu) {
        orderAmount = orderMenu.keySet().stream()
                .mapToInt(menu::getPrice)
                .sum();
    }

    private void validateVisitDate(Calendar calendar, int day) {
        if (!calendar.isDateAvailable(day)) {
            throw new IllegalArgumentException(ErrorMessage.DATE_ERROR);
        }
    }

    private void validateAvailableOrder(Menu menu, LinkedHashMap<String, Integer> orderMenu) {
        if (!orderMenu.keySet().stream().allMatch(menu::isContain)) {
            throw new IllegalArgumentException(ErrorMessage.MENU_ERROR);
        }
    }

    private void validateAllBeverage(Menu menu, LinkedHashMap<String, Integer> orderMenu) {
        if (orderMenu.keySet().stream().allMatch(menu::isAllBeverage)) {
            throw new IllegalArgumentException(ErrorMessage.MENU_ERROR);
        }
    }

    private void validateMinOrderQuantity(LinkedHashMap<String, Integer> orderMenu) {
        if (orderMenu.values().stream().anyMatch(n -> n < MIN_ORDER_QUANTITY)) {
            throw new IllegalArgumentException(ErrorMessage.MENU_ERROR);
        }
    }

    private void validateMaxOrderQuantity(LinkedHashMap<String, Integer> orderMenu) {
        int totalOrderQuantity = orderMenu.values().stream()
                .mapToInt(Integer::intValue)
                .sum();

        if (totalOrderQuantity > MAX_ORDER_QUANTITY) {
            throw new IllegalArgumentException(ErrorMessage.MENU_ERROR);
        }
    }
}
