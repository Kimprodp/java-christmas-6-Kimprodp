package christmas;

import christmas.view.ErrorMessage;
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

    public void validateVisitDate(int day) {
        LocalDate startDate = date.withDayOfMonth(1);
        LocalDate lastDate = date.withDayOfMonth(date.lengthOfMonth());
        int startDay = startDate.getDayOfMonth();
        int lastDay = lastDate.getDayOfMonth();

        if (day < startDay || day > lastDay) {
            throw new IllegalArgumentException(ErrorMessage.DATE_ERROR);
        }
    }

}
