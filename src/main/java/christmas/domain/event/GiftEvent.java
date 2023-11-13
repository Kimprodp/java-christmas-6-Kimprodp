package christmas.domain.event;

import christmas.domain.Calendar;
import christmas.domain.Foods;
import christmas.domain.Menu;
import christmas.view.ErrorMessage;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class GiftEvent {

    private static final String EVENT_NAME = "증정 이벤트";
    private static final String GIFT_NAME = "샴페인";
    private static final int GIFT_QUANTITY_DEFAULT = 0;
    private static final int GIFT_QUANTITY_AVAILABLE = 1;
    private static final int TOTAL_DISCOUNT_DEFAULT = 0;

    private final Calendar calendar;
    private final LinkedHashMap<String, Integer> gift = new LinkedHashMap<>();
    private final int conditionAmount;
    private int totalDiscount;

    public GiftEvent(Calendar calendar) {
        this.calendar = calendar;
        validateGift();
        gift.put(GIFT_NAME, GIFT_QUANTITY_DEFAULT);
        conditionAmount = 120_000;
        totalDiscount = TOTAL_DISCOUNT_DEFAULT;
    }

    public String getEventName() {
        return EVENT_NAME;
    }

    public String getGiftName() {
        return GIFT_NAME;
    }

    public LinkedHashMap<String, Integer> getGift() {
        return gift;
    }

    public int getBenefit(LocalDate date, Menu menu, int orderAmount) {
        if (isEventAvailable(date, orderAmount)) {
            applyGift();
            applyDiscount(menu);
        }
        return totalDiscount;
    }

    private boolean isEventAvailable(LocalDate date, int orderAmount) {
        return calendar.isDecember(date) && orderAmount >= conditionAmount;
    }

    private void applyGift() {
        gift.put(GIFT_NAME, GIFT_QUANTITY_AVAILABLE);
    }

    private void applyDiscount(Menu menu) {
        totalDiscount = GIFT_QUANTITY_AVAILABLE * menu.getPrice(GIFT_NAME);
    }

    private void validateGift() {
        if (!Foods.BEVERAGE.isContain(GIFT_NAME)) {
            throw new IllegalArgumentException(ErrorMessage.GIFT_ERROR);
        }
    }
}
