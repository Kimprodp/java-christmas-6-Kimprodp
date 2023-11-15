package christmas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import christmas.domain.Calendar;
import christmas.domain.Menu;
import christmas.domain.Reservation;
import christmas.domain.event.EventBadge.Badge;
import christmas.service.EventService;
import java.util.LinkedHashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventServiceTest {

    Menu menu = new Menu();
    Calendar calendar = new Calendar();
    Reservation reservation = new Reservation();
    EventService eventService = new EventService(calendar, menu);

    @DisplayName("예약 날짜와 주문한 메뉴의 정보로 각 이벤트의 혜택이 정확하게 적용되어야 함")
    @Test
    void ConfirmEventApplicationByReservationInformation() {
        //given
        LinkedHashMap<String, Integer> orderMenu = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> benefitDetails;
        orderMenu.put("티본스테이크", 2);
        orderMenu.put("바비큐립", 2);
        orderMenu.put("초코케이크", 2);
        orderMenu.put("제로콜라", 1);

        //when
        reservation.registerVisitDate(calendar, 25);
        reservation.registerOrderMenu(menu, orderMenu);
        reservation.calculateOrderAmount(menu);
        eventService.setReservationInfo(reservation, menu);
        benefitDetails = eventService.applyBenefit(menu);

        //then
        assertThat(benefitDetails).containsExactly(
                entry("크리스마스 디데이 할인", 3400),
                entry("평일 할인", 4046),
                entry("주말 할인", 0),
                entry("특별 할인", 1000),
                entry("증정 이벤트", 25000)
        );
    }

    @DisplayName("결제 금액에서 차감될 할인 금액에는 증정 이벤트의 혜택은 제외되어야 함")
    @Test
    void ConfirmDiscountAmountByReservationInformation() {
        //given
        LinkedHashMap<String, Integer> orderMenu = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> benefitDetails;
        orderMenu.put("티본스테이크", 2);
        orderMenu.put("바비큐립", 2);
        orderMenu.put("초코케이크", 2);
        orderMenu.put("제로콜라", 1);

        //when
        reservation.registerVisitDate(calendar, 25);
        reservation.registerOrderMenu(menu, orderMenu);
        reservation.calculateOrderAmount(menu);
        eventService.setReservationInfo(reservation, menu);
        reservation.applyEvent(eventService, menu);
        benefitDetails = eventService.applyBenefit(menu);
        int discountAmount = eventService.applyDiscount();

        //then
        assertThat(reservation.getBenefitAmount()).isEqualTo(33446); // 총혜택 금액
        assertThat(benefitDetails.get("증정 이벤트")).isEqualTo(25000); // 증정 이벤트 금액
        assertThat(discountAmount).isEqualTo(8446); // 주문 금액에서 할인될 금액
    }

    @DisplayName("증정 이벤트는 샴페인 1개가 지급 되어야 함")
    @Test
    void ConfirmGiftEventByReservationInformation() {
        //given
        LinkedHashMap<String, Integer> orderMenu = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> giftMenu;
        orderMenu.put("티본스테이크", 2);
        orderMenu.put("바비큐립", 2);
        orderMenu.put("초코케이크", 2);
        orderMenu.put("제로콜라", 1);

        //when
        reservation.registerVisitDate(calendar, 25);
        reservation.registerOrderMenu(menu, orderMenu);
        reservation.calculateOrderAmount(menu);
        eventService.setReservationInfo(reservation, menu);
        reservation.applyEvent(eventService, menu);
        giftMenu = eventService.applyGiftEvents();

        //then
        assertThat(giftMenu).containsExactly(entry("샴페인", 1));
    }

    @DisplayName("총혜택 금액에 따른 배지가 부여되어야 함")
    @Test
    void ConfirmEventBadgeByReservationInformation() {
        //given
        LinkedHashMap<String, Integer> orderMenu = new LinkedHashMap<>();
        Badge badge;
        orderMenu.put("티본스테이크", 2);
        orderMenu.put("바비큐립", 2);
        orderMenu.put("초코케이크", 2);
        orderMenu.put("제로콜라", 1);

        //when
        reservation.registerVisitDate(calendar, 25);
        reservation.registerOrderMenu(menu, orderMenu);
        reservation.calculateOrderAmount(menu);
        eventService.setReservationInfo(reservation, menu);
        reservation.applyEvent(eventService, menu);
        badge = eventService.applyEventBadge(reservation.getBenefitAmount());

        //then
        assertThat(badge.getBadgeName()).isEqualTo("산타");
    }

}