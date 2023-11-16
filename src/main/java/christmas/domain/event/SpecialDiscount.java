package christmas.domain.event;

import christmas.domain.Calendar;
import java.time.LocalDate;

public class SpecialDiscount {

    private static final String EVENT_NAME = "특별 할인";
    private static final int SPECIAL_DISCOUNT = 1_000;
    private static final int TOTAL_DISCOUNT_DEFAULT = 0;

    private final Calendar calendar;
    private int totalDiscount;

    public SpecialDiscount(Calendar calendar) {
        this.calendar = calendar;
        totalDiscount = TOTAL_DISCOUNT_DEFAULT;
    }

    public String getEventName() {
        return EVENT_NAME;
    }

    public int getBenefit(LocalDate date) {
        if (isEventAvailable(date)) {
            applyDiscount();
        }
        return totalDiscount;
    }

    private boolean isEventAvailable(LocalDate date) {
        return calendar.isSpecialDay(date);
    }

    private void applyDiscount() {
        totalDiscount = SPECIAL_DISCOUNT;
    }
}
