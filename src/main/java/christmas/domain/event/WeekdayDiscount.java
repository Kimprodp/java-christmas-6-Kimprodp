package christmas.domain.event;

import christmas.domain.Calendar;
import java.time.LocalDate;

public class WeekdayDiscount {

    private static final String EVENT_NAME = "평일 할인";
    private static final int WEEKDAY_DISCOUNT = 2_023;
    private static final int TOTAL_DISCOUNT_DEFAULT = 0;

    private final Calendar calendar;
    private int totalDiscount;

    public WeekdayDiscount(Calendar calendar) {
        this.calendar = calendar;
        totalDiscount = TOTAL_DISCOUNT_DEFAULT;
    }

    public String getEventName() {
        return EVENT_NAME;
    }

    public int getBenefit(LocalDate date, int dessertQuantity) {
        if (isEventAvailable(date)) {
            applyDiscount(dessertQuantity);
        }
        return totalDiscount;
    }

    protected boolean isEventAvailable(LocalDate date) {
        return calendar.isWeekday(date) && calendar.isDecember(date);
    }

    private void applyDiscount(int dessertQuantity) {
        totalDiscount = dessertQuantity * WEEKDAY_DISCOUNT;
    }
}
