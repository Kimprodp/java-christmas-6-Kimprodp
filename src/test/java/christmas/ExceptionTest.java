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
                .hasMessageContaining("[ERROR] 입력값이 없습니다. 다시 입력해 주세요.");
    }

    @DisplayName("방문일 입력 시, 공백이 있을 경우 예외가 발생함")
    @Test
    void inputDateExceptionByBlank() {
        //given
        InputProcessor inputProcessor = new InputProcessor();

        //when, then
        assertThatThrownBy(() -> inputProcessor.convertDate(" "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 입력값에 공백이 있습니다. 다시 입력해 주세요.");
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

    @DisplayName("주문 메뉴 입력 시, 입력이 없을 경우 예외가 발생함")
    @Test
    void inputMenuExceptionByEmpty() {
        //given
        InputProcessor inputProcessor = new InputProcessor();

        //when, then
        assertThatThrownBy(() -> inputProcessor.convertMenu(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 입력값이 없습니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문 메뉴 입력 시, 공백이 있을 경우 예외가 발생함")
    @Test
    void inputMenuExceptionByBlank() {
        //given
        InputProcessor inputProcessor = new InputProcessor();

        //when, then
        assertThatThrownBy(() -> inputProcessor.convertMenu(" "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 입력값에 공백이 있습니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문 메뉴 입력 시, 하이픈으로 구분되지 않을 경우 예외가 발생함")
    @Test
    void inputMenuExceptionByNotHyphen() {
        //given
        InputProcessor inputProcessor = new InputProcessor();

        //when, then
        assertThatThrownBy(() -> inputProcessor.convertMenu("해산물파스타2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문 메뉴 입력 시, 주문 수량이 숫자가 아닐 경우 예외가 발생함")
    @Test
    void inputMenuExceptionByQuantityIsNotNumber() {
        //given
        InputProcessor inputProcessor = new InputProcessor();

        //when, then
        assertThatThrownBy(() -> inputProcessor.convertMenu("해산물파스타-a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문 메뉴 입력 시, 중복된 메뉴명이 있을 경우 예외가 발생함")
    @Test
    void inputMenuExceptionByDuplicate() {
        //given
        InputProcessor inputProcessor = new InputProcessor();

        //when, then
        assertThatThrownBy(() -> inputProcessor.convertMenu("해산물파스타-1,해산물파스타-2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}
