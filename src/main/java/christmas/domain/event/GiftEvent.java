package christmas.domain.event;

import christmas.domain.Calendar;
import christmas.domain.Menu;
import christmas.view.ErrorMessage;
import java.time.LocalDate;
import java.util.LinkedHashMap;

public class GiftEvent {

    private static final String EVENT_NAME = "증정 이벤트";
    private static final String GIFT_NAME = "샴페인";
    private static final int GIFT_QUANTITY_DEFAULT = 0;
    private static final int GIFT_QUANTITY_AVAILABLE = 1;
    private static final int TOTAL_DISCOUNT_DEFAULT = 0;
    private static final int CONDITION_AMOUNT = 120_000;

    private final Calendar calendar;
    private final LinkedHashMap<String, Integer> gift = new LinkedHashMap<>();
    private int totalDiscount;

    public GiftEvent(Calendar calendar, Menu menu) {
        this.calendar = calendar;
        validateGift(menu);
        gift.put(GIFT_NAME, GIFT_QUANTITY_DEFAULT);
        totalDiscount = TOTAL_DISCOUNT_DEFAULT;
    }

    public String getEventName() {
        return EVENT_NAME;
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
        return calendar.isDecember(date) && orderAmount >= CONDITION_AMOUNT;
    }

    private void applyGift() {
        gift.put(GIFT_NAME, GIFT_QUANTITY_AVAILABLE);
    }

    private void applyDiscount(Menu menu) {
        totalDiscount = GIFT_QUANTITY_AVAILABLE * menu.getPrice(GIFT_NAME);
    }

    private void validateGift(Menu menu) {
        if (!menu.isAllCategoryContain(GIFT_NAME)) {
            throw new IllegalArgumentException(ErrorMessage.NONE_MENU);
        }
    }
}
