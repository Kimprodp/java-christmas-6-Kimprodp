package christmas.domain;

import java.time.LocalDate;

public class Calendar {

    private static final int DEFAULT_YEAR = 2023;
    private static final int DEFAULT_MONTH = 12;
    private static final int DEFAULT_DAY = 1;

    private final LocalDate date;

    public Calendar() {
        date = LocalDate.of(DEFAULT_YEAR, DEFAULT_MONTH, DEFAULT_DAY);
    }

    public int getYear() {
        return date.getYear();
    }

    public int getMonth() {
        return date.getMonthValue();
    }

    public boolean isDateAvailable(int day) {
        LocalDate startDate = date.withDayOfMonth(DEFAULT_DAY);
        LocalDate lastDate = date.withDayOfMonth(date.lengthOfMonth());
        int startDay = startDate.getDayOfMonth();
        int lastDay = lastDate.getDayOfMonth();

        return (day >= startDay && day <= lastDay);
    }
}
