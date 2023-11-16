package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calendar {

    public static final int DEFAULT_YEAR = 2023;
    public static final int DEFAULT_MONTH = 12;
    public static final int DEFAULT_DAY = 1;
    public static final int LAST_DAY = 31;
    public static final LocalDate CHRISTMAS = LocalDate.of(DEFAULT_YEAR, DEFAULT_MONTH, 25);

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final List<DayOfWeek> weekday;
    private final List<DayOfWeek> weekend;
    private final List<LocalDate> specialDay = new ArrayList<>();


    public Calendar() {
        startDate = LocalDate.of(DEFAULT_YEAR, DEFAULT_MONTH, DEFAULT_DAY);
        endDate = LocalDate.of(DEFAULT_YEAR, DEFAULT_MONTH, LAST_DAY);
        weekday = Arrays.asList(DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
                DayOfWeek.THURSDAY);
        weekend = Arrays.asList(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);

        for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
            if (date.getDayOfWeek() == DayOfWeek.SUNDAY || date.equals(CHRISTMAS)) {
                specialDay.add(date);
            }
        }
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

    public boolean isDecember(LocalDate date) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }

    public boolean isBeforeChristmas(LocalDate date) {
        return !date.isBefore(startDate) && !date.isAfter(CHRISTMAS);
    }

    public boolean isWeekday(LocalDate date) {
        return weekday.contains(date.getDayOfWeek());
    }

    public boolean isWeekend(LocalDate date) {
        return weekend.contains(date.getDayOfWeek());
    }

    public boolean isSpecialDay(LocalDate date) {
        return specialDay.contains(date);
    }
}
