package christmas.domain.event;

import christmas.domain.Calendar;
import java.time.LocalDate;

public class ChristmasDiscount {

    private static final String EVENT_NAME = "크리스마스 디데이 할인";
    private static final int MIN_DISCOUNT = 1_000;
    private static final int MAX_DISCOUNT = 3_400;
    private static final int DAILY_DISCOUNT = 100;
    private static final int TOTAL_DISCOUNT_DEFAULT = 0;

    private final Calendar calendar;
    private int totalDiscount;

    public ChristmasDiscount(Calendar calendar) {
        this.calendar = calendar;
        totalDiscount = TOTAL_DISCOUNT_DEFAULT;
    }

    public String getEventName() {
        return EVENT_NAME;
    }

    public int getBenefit(LocalDate date) {
        if (isEventAvailable(date)) {
            applyDiscount(date);
        }
        return totalDiscount;
    }

    protected boolean isEventAvailable(LocalDate date) {
        return calendar.isBeforeChristmas(date);
    }

    protected void applyDiscount(LocalDate date) {
        int discountAmount = (date.getDayOfMonth() - Calendar.DEFAULT_DAY) * DAILY_DISCOUNT + MIN_DISCOUNT;
        totalDiscount = Math.min(discountAmount, MAX_DISCOUNT);
    }
}
