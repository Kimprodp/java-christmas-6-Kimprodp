package christmas;

import java.time.LocalDate;

public class Reservation {

    private LocalDate reservationDate;

    public void registerReservationDate(Calendar calendar, int day) {
        reservationDate = LocalDate.of(calendar.getYear(), calendar.getMonth(), day);
    }

    public int getReservationDay() {
        return reservationDate.getDayOfMonth();
    }
}
