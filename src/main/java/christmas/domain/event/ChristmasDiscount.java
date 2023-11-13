package christmas.domain.event;

import christmas.domain.Calendar;
import java.time.LocalDate;
import java.util.HashMap;

public class ChristmasDdayDiscount extends Event {

    private static final String EVENT_NAME = "크리스마스 디데이 할인";
    private static final int MIN_DISCOUNT = 1_000;
    private static final int MAX_DISCOUNT = 3_400;
    private static final int DAILY_DISCOUNT = 100;
    private static final int TOTAL_DISCOUNT_DEFAULT = 0;

    private int totalDiscount;

    public ChristmasDdayDiscount(Calendar calendar) {
        super(calendar);
        totalDiscount = TOTAL_DISCOUNT_DEFAULT;
    }

    public HashMap<String, Integer> getBenefit(LocalDate date) {
        if (isEventAvailable(date)) {
            applyDiscount(date);
        }
        HashMap<String, Integer> benefitDetail = new HashMap<>();
        benefitDetail.put(EVENT_NAME, totalDiscount);
        return benefitDetail;
    }

    @Override
    protected boolean isEventAvailable(LocalDate date) {
        return calendar.isBeforeChristmas(date);
    }

    protected void applyDiscount(LocalDate date) {
        int discountAmount = (date.getDayOfMonth() - 1) * DAILY_DISCOUNT + MIN_DISCOUNT;
        totalDiscount = Math.min(discountAmount, MAX_DISCOUNT);
    }
}
