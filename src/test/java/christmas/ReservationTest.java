package christmas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import christmas.domain.Calendar;
import christmas.domain.Menu;
import christmas.domain.Reservation;
import christmas.service.EventService;
import java.util.LinkedHashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReservationTest {

    Calendar calendar = new Calendar();
    Menu menu = new Menu();

    @DisplayName("예약한 날짜로 방문일이 등록 되어야 함")
    @Test
    void registerVisitDateByInputDate() {
        //given
        Reservation reservation = new Reservation();
        int date = 17;

        //when
        reservation.registerVisitDate(calendar, date);

        //then
        assertThat(reservation.getVisitDate().getDayOfMonth()).isEqualTo(17);
    }

    @DisplayName("주문한 메뉴로 주문이 등록 되어야 함")
    @Test
    void registerOrderMenuByInputDate() {
        //given
        Reservation reservation = new Reservation();
        LinkedHashMap<String, Integer> orderMenu = new LinkedHashMap<>();
        orderMenu.put("티본스테이크", 1);
        orderMenu.put("바비큐립", 2);
        orderMenu.put("제로콜라", 5);

        //when
        reservation.registerOrderMenu(menu, orderMenu);

        //then
        assertThat(reservation.getOrderMenu()).isEqualTo(orderMenu);
    }

    @DisplayName("주문한 메뉴들의 가격으로 총주문 금액을 계산해야 함")
    @Test
    void calculateOrderAmountByOrderMenu() {
        //given
        Reservation reservation = new Reservation();
        LinkedHashMap<String, Integer> orderMenu = new LinkedHashMap<>();
        orderMenu.put("티본스테이크", 1);
        orderMenu.put("바비큐립", 2);
        orderMenu.put("제로콜라", 5);

        //when
        reservation.registerOrderMenu(menu, orderMenu);
        reservation.calculateOrderAmount(menu);

        //then
        assertThat(reservation.getOrderAmount()).isEqualTo(178000);
    }

    @DisplayName("이벤트 혜택 적용 시, 혜택 상세 내용이 등록 되어야 함")
    @Test
    void registerBenefitDetailsByApplyEvent() {
        //given
        Reservation reservation = new Reservation();
        EventService eventService = new EventService(calendar, menu);
        LinkedHashMap<String, Integer> orderMenu = new LinkedHashMap<>();
        int date = 2;
        orderMenu.put("티본스테이크", 1);
        orderMenu.put("바비큐립", 2);
        orderMenu.put("제로콜라", 5);

        //when
        reservation.registerVisitDate(calendar, date);
        reservation.registerOrderMenu(menu, orderMenu);
        reservation.calculateOrderAmount(menu);
        eventService.setReservationInfo(reservation, menu);
        reservation.applyEvent(eventService, menu);

        //then
        assertThat(reservation.getBenefitDetails()).containsExactly(
                entry("크리스마스 디데이 할인", 1100),
                entry("평일 할인", 0),
                entry("주말 할인", 6069),
                entry("특별 할인", 0),
                entry("증정 이벤트", 25000)
        );
    }

    @DisplayName("이벤트 혜택 적용 시, 증정품이 등록 되어야 함")
    @Test
    void registerGiftItemByApplyEvent() {
        //given
        Reservation reservation = new Reservation();
        EventService eventService = new EventService(calendar, menu);
        LinkedHashMap<String, Integer> orderMenu = new LinkedHashMap<>();
        int date = 2;
        orderMenu.put("티본스테이크", 1);
        orderMenu.put("바비큐립", 2);
        orderMenu.put("제로콜라", 5);

        //when
        reservation.registerVisitDate(calendar, date);
        reservation.registerOrderMenu(menu, orderMenu);
        reservation.calculateOrderAmount(menu);
        eventService.setReservationInfo(reservation, menu);
        reservation.applyEvent(eventService, menu);

        //then
        assertThat(reservation.getGiftItem()).containsExactly(entry("샴페인", 1));
    }

    @DisplayName("이벤트 혜택 적용 시, 혜택 금액이 계산 되어야 함")
    @Test
    void calculateBenefitAmountByApplyEvent() {
        //given
        Reservation reservation = new Reservation();
        EventService eventService = new EventService(calendar, menu);
        LinkedHashMap<String, Integer> orderMenu = new LinkedHashMap<>();
        int date = 2;
        orderMenu.put("티본스테이크", 1);
        orderMenu.put("바비큐립", 2);
        orderMenu.put("제로콜라", 5);

        //when
        reservation.registerVisitDate(calendar, date);
        reservation.registerOrderMenu(menu, orderMenu);
        reservation.calculateOrderAmount(menu);
        eventService.setReservationInfo(reservation, menu);
        reservation.applyEvent(eventService, menu);

        //then
        assertThat(reservation.getBenefitAmount()).isEqualTo(32169);
    }

    @DisplayName("이벤트 혜택 적용 시, 결제 예정 금액이 계산 되어야 함")
    @Test
    void calculatePaymentAmountByApplyEvent() {
        //given
        Reservation reservation = new Reservation();
        EventService eventService = new EventService(calendar, menu);
        LinkedHashMap<String, Integer> orderMenu = new LinkedHashMap<>();
        int date = 2;
        orderMenu.put("티본스테이크", 1);
        orderMenu.put("바비큐립", 2);
        orderMenu.put("제로콜라", 5);

        //when
        reservation.registerVisitDate(calendar, date);
        reservation.registerOrderMenu(menu, orderMenu);
        reservation.calculateOrderAmount(menu);
        eventService.setReservationInfo(reservation, menu);
        reservation.applyEvent(eventService, menu);

        //then
        assertThat(reservation.getExpectedPaymentAmount()).isEqualTo(170831);
    }

    @DisplayName("이벤트 혜택 적용 시, 이벤트 배지가 등록 되어야 함")
    @Test
    void registerEventBadgeByApplyEvent() {
        //given
        Reservation reservation = new Reservation();
        EventService eventService = new EventService(calendar, menu);
        LinkedHashMap<String, Integer> orderMenu = new LinkedHashMap<>();
        int date = 2;
        orderMenu.put("티본스테이크", 1);
        orderMenu.put("바비큐립", 2);
        orderMenu.put("제로콜라", 5);

        //when
        reservation.registerVisitDate(calendar, date);
        reservation.registerOrderMenu(menu, orderMenu);
        reservation.calculateOrderAmount(menu);
        eventService.setReservationInfo(reservation, menu);
        reservation.applyEvent(eventService, menu);

        //then
        assertThat(reservation.getEventBadge()).isEqualTo("산타");
    }
}
