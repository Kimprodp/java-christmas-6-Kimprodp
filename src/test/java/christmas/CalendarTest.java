package christmas;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.domain.Calendar;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalendarTest {

    Calendar calendar = new Calendar();

    @DisplayName("주어진 날짜가 1일부터 31일 범위에 있을때 true를 반환함")
    @Test
    void ConfirmDayOfMonthByCorrectDay() {
        //given
        int day = 3;
        boolean checkDate;

        //when
        checkDate = calendar.isDateAvailable(day);

        //then
        assertThat(checkDate).isEqualTo(true);
    }

    @DisplayName("주어진 날짜가 1일부터 31일 범위에 없을때 false를 반환함")
    @Test
    void ConfirmDayOfMonthByIncorrectDay() {
        //given
        int day = 32;
        boolean checkDate;

        //when
        checkDate = calendar.isDateAvailable(day);

        //then
        assertThat(checkDate).isEqualTo(false);
    }

    @DisplayName("주어진 날짜가 12월일 때 true를 반환함")
    @Test
    void ConfirmDecemberByCorrectDate() {
        //given
        LocalDate date = LocalDate.of(2023, 12, 3);
        boolean checkDate;

        //when
        checkDate = calendar.isDecember(date);

        //then
        assertThat(checkDate).isEqualTo(true);
    }

    @DisplayName("주어진 날짜가 12월이 아닐 때 false를 반환함")
    @Test
    void ConfirmDecemberByIncorrectDate() {
        //given
        LocalDate date = LocalDate.of(2023, 11, 3);
        boolean checkDate;

        //when
        checkDate = calendar.isDecember(date);

        //then
        assertThat(checkDate).isEqualTo(false);
    }

    @DisplayName("주어진 날짜가 12월이고, 크리스마스 이전일 때 true를 반환함")
    @Test
    void ConfirmBeforeChristmasByCorrectDate() {
        //given
        LocalDate date = LocalDate.of(2023, 12, 3);
        boolean checkDate;

        //when
        checkDate = calendar.isBeforeChristmas(date);

        //then
        assertThat(checkDate).isEqualTo(true);
    }

    @DisplayName("주어진 날짜가 크리스마스 이후일 때 flase를 반환함")
    @Test
    void ConfirmBeforeChristmasByIncorrectDate() {
        //given
        LocalDate date = LocalDate.of(2023, 12, 26);
        boolean checkDate;

        //when
        checkDate = calendar.isBeforeChristmas(date);

        //then
        assertThat(checkDate).isEqualTo(false);
    }

    @DisplayName("주어진 날짜가 평일(일요일~목요일)일 때 true를 반환함")
    @Test
    void ConfirmWeekdayByCorrectDate() {
        //given
        LocalDate date = LocalDate.of(2023, 12, 3);
        boolean checkDate;

        //when
        checkDate = calendar.isWeekday(date);

        //then
        assertThat(checkDate).isEqualTo(true);
    }

    @DisplayName("주어진 날짜가 평일(일요일~목요일)이 아닐 때 false를 반환함")
    @Test
    void ConfirmWeekdayByIncorrectDate() {
        //given
        LocalDate date = LocalDate.of(2023, 12, 1);
        boolean checkDate;

        //when
        checkDate = calendar.isWeekday(date);

        //then
        assertThat(checkDate).isEqualTo(false);
    }

    @DisplayName("주어진 날짜가 주말(금요일, 토요일)일 때 true를 반환함")
    @Test
    void ConfirmWeekendByCorrectDate() {
        //given
        LocalDate date = LocalDate.of(2023, 12, 1);
        boolean checkDate;

        //when
        checkDate = calendar.isWeekend(date);

        //then
        assertThat(checkDate).isEqualTo(true);
    }

    @DisplayName("주어진 날짜가 주말(금요일, 토요일)이 아닐 때 false를 반환함")
    @Test
    void ConfirmWeekendByIncorrectDate() {
        //given
        LocalDate date = LocalDate.of(2023, 12, 3);
        boolean checkDate;

        //when
        checkDate = calendar.isWeekend(date);

        //then
        assertThat(checkDate).isEqualTo(false);
    }

    @DisplayName("주어진 날짜가 특별 할일 적용일(매주 일요일, 크리스마스)일 때 true를 반환함")
    @Test
    void ConfirmSpecialDayByCorrectDate() {
        //given
        LocalDate date = LocalDate.of(2023, 12, 10);
        boolean checkDate;

        //when
        checkDate = calendar.isSpecialDay(date);

        //then
        assertThat(checkDate).isEqualTo(true);
    }

    @DisplayName("크리스마스가 특별 할일 적용일(매주 일요일, 크리스마스)일 때 true를 반환함")
    @Test
    void ConfirmSpecialDayByChristmas() {
        //given
        LocalDate date = LocalDate.of(2023, 12, 25);
        boolean checkDate;

        //when
        checkDate = calendar.isSpecialDay(date);

        //then
        assertThat(checkDate).isEqualTo(true);
    }

    @DisplayName("주어진 날짜가 특별 할일 적용일(매주 일요일, 크리스마스)이 아닐 때 false를 반환함")
    @Test
    void ConfirmSpecialDayByIncorrectDate() {
        //given
        LocalDate date = LocalDate.of(2023, 12, 11);
        boolean checkDate;

        //when
        checkDate = calendar.isSpecialDay(date);

        //then
        assertThat(checkDate).isEqualTo(false);
    }
}
