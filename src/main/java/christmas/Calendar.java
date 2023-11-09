package christmas;

import java.time.LocalDate;

public class Calendar {

    private final LocalDate now;
    private final int year;
    private final int month;

    public Calendar() {
        now = LocalDate.now();
        year = now.getYear();
        month = now.getMonthValue();
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }


}
