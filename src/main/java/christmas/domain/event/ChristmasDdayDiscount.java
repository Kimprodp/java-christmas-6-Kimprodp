package christmas.domain.event;

import christmas.domain.Calendar;
import java.time.LocalDate;

public class ChristmasDdayDiscount extends Calendar implements Event {

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final int minDiscountAmount;
    private final int maxDiscountAmount;
    private final int dailyDiscountAmount;

    public ChristmasDdayDiscount() {
        startDate = LocalDate.of(DEFAULT_YEAR, DEFAULT_MONTH, DEFAULT_DAY);
        endDate = LocalDate.of(DEFAULT_YEAR, DEFAULT_MONTH, CHRISTMAS);
        minDiscountAmount = 1_000;
        maxDiscountAmount = 3_400;
        dailyDiscountAmount = 100;
    }

    @Override
    public int getBenefit(LocalDate date) {
        int discountAmount = 0;
        if (isDateAvailable(date)) {
            int day = date.getDayOfMonth();
            discountAmount = calculateDiscount(day);
        }
        return discountAmount;
    }

    @Override
    public boolean isDateAvailable(LocalDate date) {
        return !date.isBefore(this.startDate) && !date.isAfter(this.endDate);
    }

    private int calculateDiscount(int day) {
        int startDay = startDate.getDayOfMonth();
        int discountAmount = ((day - startDay) * dailyDiscountAmount) + minDiscountAmount;
        if (discountAmount > maxDiscountAmount) {
            discountAmount = maxDiscountAmount;
        }

        return discountAmount;
    }
}
