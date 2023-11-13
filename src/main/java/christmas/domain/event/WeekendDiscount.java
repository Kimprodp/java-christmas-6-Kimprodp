package christmas.domain.event;

import christmas.domain.Calendar;
import java.time.LocalDate;

public class WeekendDiscount {

    private static final String EVENT_NAME = "주말 할인";
    private static final int WEEKEND_DISCOUNT = 2_023;
    private static final int TOTAL_DISCOUNT_DEFAULT = 0;

    private final Calendar calendar;
    private int totalDiscount;

    public WeekendDiscount(Calendar calendar) {
        this.calendar = calendar;
        totalDiscount = TOTAL_DISCOUNT_DEFAULT;
    }

    public String getEventName() {
        return EVENT_NAME;
    }

    public int getBenefit(LocalDate date, int mainQuantity) {
        if (isEventAvailable(date)) {
            applyDiscount(mainQuantity);
        }
        return totalDiscount;
    }

    private boolean isEventAvailable(LocalDate date) {
        return calendar.isWeekend(date);
    }

    private void applyDiscount(int mainQuantity) {
        totalDiscount = mainQuantity * WEEKEND_DISCOUNT;
    }
}
