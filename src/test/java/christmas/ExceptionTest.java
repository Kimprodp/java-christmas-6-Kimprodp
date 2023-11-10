package christmas;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import christmas.domain.Calendar;
import christmas.domain.Reservation;
import christmas.controller.InputProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExceptionTest {

    @DisplayName("방문일 입력 시, 입력이 없을 경우 예외가 발생함")
    @Test
    void inputDateExceptionByEmpty() {
        //given
        InputProcessor inputProcessor = new InputProcessor();

        //when, then
        assertThatThrownBy(() -> inputProcessor.convertDate(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("방문일 입력 시, 숫자가 입력되지 않을 경우 예외가 발생함")
    @Test
    void inputDateExceptionByNotNumber() {
        //given
        InputProcessor inputProcessor = new InputProcessor();

        //when, then
        assertThatThrownBy(() -> inputProcessor.convertDate("a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("방문일 입력 시, 공백이 있을 경우 예외가 발생함")
    @Test
    void inputDateExceptionByBlank() {
        //given
        InputProcessor inputProcessor = new InputProcessor();

        //when, then
        assertThatThrownBy(() -> inputProcessor.convertDate("1 2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("방문일 등록 시, 1보다 작은 경우 예외가 발생함")
    @Test
    void registerDateExceptionByUnderRange() {
        //given
        Calendar calendar = new Calendar();
        Reservation reservation = new Reservation();

        //when, then
        assertThatThrownBy(() -> reservation.registerVisitDate(calendar, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("방문일 등록 시, 31보다 큰 경우 예외가 발생함")
    @Test
    void registerDateExceptionByOverRange() {
        //given
        Calendar calendar = new Calendar();
        Reservation reservation = new Reservation();

        //when, then
        assertThatThrownBy(() -> reservation.registerVisitDate(calendar, 32))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }
}
