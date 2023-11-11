package christmas.domain;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Calendar {

    protected static final int DEFAULT_YEAR = 2023;
    protected static final int DEFAULT_MONTH = 12;
    protected static final int DEFAULT_DAY = 1;
    protected static final int LAST_DAY = 1;
    protected static final int CHRISTMAS = 25;

    protected final LocalDate startDate;
    protected final LocalDate endDate;
    protected final List<LocalDate> specialDay;


    public Calendar() {
        startDate = LocalDate.of(DEFAULT_YEAR, DEFAULT_MONTH, DEFAULT_DAY);
        endDate = LocalDate.of(DEFAULT_YEAR, DEFAULT_MONTH, LAST_DAY);
        specialDay = Arrays.asList(
                LocalDate.of(DEFAULT_YEAR, DEFAULT_MONTH, 3),
                LocalDate.of(DEFAULT_YEAR, DEFAULT_MONTH, 10),
                LocalDate.of(DEFAULT_YEAR, DEFAULT_MONTH, 17),
                LocalDate.of(DEFAULT_YEAR, DEFAULT_MONTH, 24),
                LocalDate.of(DEFAULT_YEAR, DEFAULT_MONTH, 25),
                LocalDate.of(DEFAULT_YEAR, DEFAULT_MONTH, 31));
    }

    public int getYear() {
        return startDate.getYear();
    }

    public int getMonth() {
        return startDate.getMonthValue();
    }

    public boolean isDateAvailable(int day) {
        int startDay = startDate.getDayOfMonth();
        int lastDay = endDate.getDayOfMonth();

        return (day >= startDay && day <= lastDay);
    }

    public boolean isDateAvailable(LocalDate date) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }

    public boolean isSpecialDay(LocalDate date) {
        return specialDay.contains(date);
    }
}
