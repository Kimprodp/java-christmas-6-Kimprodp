package christmas;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import christmas.view.InputValidation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExceptionTest {

    @DisplayName("방문일 입력 시, 숫자가 입력되지 않을 경우 예외가 발생함")
    @Test
    void inputDateExceptionByNotNumber() {
        //given
        InputValidation inputValidation = new InputValidation();

        //when, then
        assertThatThrownBy(() -> inputValidation.validateNumber("a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }
}
