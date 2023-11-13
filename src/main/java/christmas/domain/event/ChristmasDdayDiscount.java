package christmas.domain.event;

import christmas.domain.Calendar;
import java.time.LocalDate;

public class ChristmasDdayDiscount {

    private final Calendar calendar;
    private final int minDiscount;
    private final int maxDiscount;
    private final int dailyDiscount;
    private int totalDiscount;

    public ChristmasDdayDiscount(Calendar calendar) {
        this.calendar = calendar;
        minDiscount = 1_000;
        maxDiscount = 3_400;
        dailyDiscount = 100;
        totalDiscount = 0;
    }

    public int getBenefit(LocalDate date) {
        if (isEventAvailable(date)) {
            applyDiscount(date);
        }
        return totalDiscount;
    }

    private boolean isEventAvailable(LocalDate date) {
        return calendar.isBeforeChristmas(date);
    }

    private void applyDiscount(LocalDate date) {
        int discountAmount = (date.getDayOfMonth() - 1) * dailyDiscount + minDiscount;
        totalDiscount = Math.min(discountAmount, maxDiscount);
    }
}
