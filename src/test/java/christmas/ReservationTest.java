package christmas;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReservationTest {

    @DisplayName("예약한 날짜로 예약이 등록되어야 함")
    @Test
    void confirmReservationDateRegistration() {
        //given
        Calendar calendar = new Calendar();
        Reservation reservation = new Reservation();
        int date = 17;

        //when
        reservation.registerVisitDate(calendar, date);

        //then
        assertThat(reservation.getVisitDay()).isEqualTo(17);
    }
}
