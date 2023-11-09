package christmas;

import java.time.LocalDate;

public class Reservation {

    private LocalDate visitDate;

    public void registerVisitDate(Calendar calendar, int day) {
        visitDate = LocalDate.of(calendar.getYear(), calendar.getMonth(), day);
    }

    public int getVisitDay() {
        return visitDate.getDayOfMonth();
    }
}
