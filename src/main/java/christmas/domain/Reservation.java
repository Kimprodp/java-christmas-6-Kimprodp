package christmas.domain;

import christmas.domain.event.EventBadge.Badge;
import christmas.service.EventService;
import christmas.view.ErrorMessage;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Reservation {

    private static final int MIN_ORDER_QUANTITY = 1;
    private static final int MAX_ORDER_QUANTITY = 20;

    private LocalDate visitDate;
    private LinkedHashMap<String, Integer> orderMenu;
    private LinkedHashMap<String, Integer> giftItem;
    private LinkedHashMap<String, Integer> benefitDetails;
    private int orderAmount;
    private int benefitAmount;
    private int discountAmount;
    private int expectedPaymentAmount;
    private Badge eventBadge;

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

    public void applyEvent(EventService eventService, Menu menu) {
        benefitDetails = eventService.applyBenefit(menu);
        discountAmount = eventService.applyDiscount();
        giftItem = eventService.applyGiftEvents();
        calculateBenefitAmount();
        calculateExpectedPaymentAmount();
        eventBadge = eventService.applyEventBadge(benefitAmount);
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public LinkedHashMap<String, Integer> getOrderMenu() {
        return orderMenu;
    }

    public HashMap<String, Integer> getGiftItem() {
        return giftItem;
    }

    public LinkedHashMap<String, Integer> getBenefitDetails() {
        return benefitDetails;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public int getBenefitAmount() {
        return benefitAmount;
    }

    public int getExpectedPaymentAmount() {
        return expectedPaymentAmount;
    }

    public String getEventBadge() {
        return eventBadge.getBadgeName();
    }

    public void calculateOrderAmount(Menu menu) {
        orderAmount = orderMenu.keySet().stream()
                .mapToInt(food -> menu.getPrice(food) * orderMenu.get(food))
                .sum();
    }

    private void calculateBenefitAmount() {
        benefitAmount = benefitDetails.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private void calculateExpectedPaymentAmount() {
        expectedPaymentAmount = orderAmount - discountAmount;
    }

    private void validateVisitDate(Calendar calendar, int day) {
        if (!calendar.isDateAvailable(day)) {
            throw new IllegalArgumentException(ErrorMessage.DATE_ERROR);
        }
    }

    private void validateAvailableOrder(Menu menu, LinkedHashMap<String, Integer> orderMenu) {
        if (!orderMenu.keySet().stream().allMatch(menu::isAllCategoryContain)) {
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
