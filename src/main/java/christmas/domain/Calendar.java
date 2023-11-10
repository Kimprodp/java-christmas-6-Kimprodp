package christmas.domain;

import java.time.LocalDate;

public class Calendar {

    private final LocalDate date;

    public Calendar() {
        date = LocalDate.of(2023, 12, 1);
    }

    public int getYear() {
        return date.getYear();
    }

    public int getMonth() {
        return date.getMonthValue();
    }

    public boolean isDateAvailable(int day) {
        LocalDate startDate = date.withDayOfMonth(1);
        LocalDate lastDate = date.withDayOfMonth(date.lengthOfMonth());
        int startDay = startDate.getDayOfMonth();
        int lastDay = lastDate.getDayOfMonth();

        return (day >= startDay && day <= lastDay);
    }
}
