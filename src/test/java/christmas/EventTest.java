package christmas;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.domain.Calendar;
import christmas.domain.Menu;
import christmas.domain.event.ChristmasDiscount;
import christmas.domain.event.EventBadge;
import christmas.domain.event.EventBadge.Badge;
import christmas.domain.event.GiftEvent;
import christmas.domain.event.SpecialDiscount;
import christmas.domain.event.WeekdayDiscount;
import christmas.domain.event.WeekendDiscount;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EventTest {

    @DisplayName("예약 일자가 3일일 때, 크리스마스 디데이 할인 금액은 1200원 이여야 함")
    @Test
    void ConfirmChristmasDiscountByDateBeforeChristmas() {
        //given
        Calendar calendar = new Calendar();
        ChristmasDiscount christmasDiscount = new ChristmasDiscount(calendar);
        LocalDate date = LocalDate.of(2023, 12, 3);
        int christmasDiscountAmount;

        //when
        christmasDiscountAmount = christmasDiscount.getBenefit(date);

        //then
        assertThat(christmasDiscountAmount).isEqualTo(1200);
    }

    @DisplayName("예약 일자가 25일일 때, 크리스마스 디데이 할인 금액은 3400원 이여야 함")
    @Test
    void ConfirmChristmasDiscountByDateAtChristmas() {
        //given
        Calendar calendar = new Calendar();
        ChristmasDiscount christmasDiscount = new ChristmasDiscount(calendar);
        LocalDate date = LocalDate.of(2023, 12, 25);
        int christmasDiscountAmount;

        //when
        christmasDiscountAmount = christmasDiscount.getBenefit(date);

        //then
        assertThat(christmasDiscountAmount).isEqualTo(3400);
    }

    @DisplayName("예약 일자가 31일일 때, 크리스마스 디데이 할인 금액은 0원 이여야 함")
    @Test
    void ConfirmChristmasDiscountByDateAfterChristmas() {
        //given
        Calendar calendar = new Calendar();
        ChristmasDiscount christmasDiscount = new ChristmasDiscount(calendar);
        LocalDate date = LocalDate.of(2023, 12, 31);
        int christmasDiscountAmount;

        //when
        christmasDiscountAmount = christmasDiscount.getBenefit(date);

        //then
        assertThat(christmasDiscountAmount).isEqualTo(0);
    }

    @DisplayName("예약 일자가 3일일 때, 특별 할인 금액은 1000원 이여야 함")
    @Test
    void ConfirmSpecialDiscountByDateWithSpecialDay() {
        //given
        Calendar calendar = new Calendar();
        SpecialDiscount specialDiscount = new SpecialDiscount(calendar);
        LocalDate date = LocalDate.of(2023, 12, 3);
        int specialDiscountAmount;

        //when
        specialDiscountAmount = specialDiscount.getBenefit(date);

        //then
        assertThat(specialDiscountAmount).isEqualTo(1000);
    }

    @DisplayName("예약 일자가 25일일 때, 특별 할인 금액은 1000원 이여야 함")
    @Test
    void ConfirmSpecialDiscountByDateAtChristmas() {
        //given
        Calendar calendar = new Calendar();
        SpecialDiscount specialDiscount = new SpecialDiscount(calendar);
        LocalDate date = LocalDate.of(2023, 12, 25);
        int specialDiscountAmount;

        //when
        specialDiscountAmount = specialDiscount.getBenefit(date);

        //then
        assertThat(specialDiscountAmount).isEqualTo(1000);
    }

    @DisplayName("예약 일자가 4일일 때, 특별 할인 금액은 0원 이여야 함")
    @Test
    void ConfirmSpecialDiscountByDateWithinSpecialDay() {
        //given
        Calendar calendar = new Calendar();
        SpecialDiscount specialDiscount = new SpecialDiscount(calendar);
        LocalDate date = LocalDate.of(2023, 12, 4);
        int specialDiscountAmount;

        //when
        specialDiscountAmount = specialDiscount.getBenefit(date);

        //then
        assertThat(specialDiscountAmount).isEqualTo(0);
    }

    @DisplayName("예약 일자가 12월이 아닐 때, 특별 할인 금액은 0원 이여야 함")
    @Test
    void ConfirmSpecialDiscountByDateWithinDecember() {
        //given
        Calendar calendar = new Calendar();
        SpecialDiscount specialDiscount = new SpecialDiscount(calendar);
        LocalDate date = LocalDate.of(2023, 11, 3);
        int specialDiscountAmount;

        //when
        specialDiscountAmount = specialDiscount.getBenefit(date);

        //then
        assertThat(specialDiscountAmount).isEqualTo(0);
    }

    @DisplayName("예약 일자가 평일이고, 디저트 메뉴가 2개 일 때, 평일 할인 금액은 4046원 이여야 함")
    @Test
    void ConfirmWeekdayDiscountByWeekdayAndTwoDesertMenu() {
        //given
        Calendar calendar = new Calendar();
        WeekdayDiscount weekdayDiscount = new WeekdayDiscount(calendar);
        LocalDate date = LocalDate.of(2023, 12, 3);
        int dessertQuantity = 2;
        int weekdayDiscountAmount;

        //when
        weekdayDiscountAmount = weekdayDiscount.getBenefit(date, dessertQuantity);

        //then
        assertThat(weekdayDiscountAmount).isEqualTo(4046);
    }

    @DisplayName("예약 일자가 평일이고, 디저트 메뉴가 0개 일 때, 평일 할인 금액은 0원 이여야 함")
    @Test
    void ConfirmWeekdayDiscountByWeekdayAndNoneDesertMenu() {
        //given
        Calendar calendar = new Calendar();
        WeekdayDiscount weekdayDiscount = new WeekdayDiscount(calendar);
        LocalDate date = LocalDate.of(2023, 12, 3);
        int dessertQuantity = 0;
        int weekdayDiscountAmount;

        //when
        weekdayDiscountAmount = weekdayDiscount.getBenefit(date, dessertQuantity);

        //then
        assertThat(weekdayDiscountAmount).isEqualTo(0);
    }

    @DisplayName("예약 일자가 주말이고, 디저트 메뉴가 2개 일 때, 평일 할인 금액은 0원 이여야 함")
    @Test
    void ConfirmWeekdayDiscountByWeekendAndTwoDesertMenu() {
        //given
        Calendar calendar = new Calendar();
        WeekdayDiscount weekdayDiscount = new WeekdayDiscount(calendar);
        LocalDate date = LocalDate.of(2023, 12, 1);
        int dessertQuantity = 2;
        int weekdayDiscountAmount;

        //when
        weekdayDiscountAmount = weekdayDiscount.getBenefit(date, dessertQuantity);

        //then
        assertThat(weekdayDiscountAmount).isEqualTo(0);
    }

    @DisplayName("예약 일자가 12월이 아닌 평일이고, 디저트 메뉴가 2개 일 때, 평일 할인 금액은 0원 이여야 함")
    @Test
    void ConfirmWeekdayDiscountByWeekdayWithinDecemberAndTwoDesertMenu() {
        //given
        Calendar calendar = new Calendar();
        WeekdayDiscount weekdayDiscount = new WeekdayDiscount(calendar);
        LocalDate date = LocalDate.of(2023, 11, 1);
        int dessertQuantity = 2;
        int weekdayDiscountAmount;

        //when
        weekdayDiscountAmount = weekdayDiscount.getBenefit(date, dessertQuantity);

        //then
        assertThat(weekdayDiscountAmount).isEqualTo(0);
    }

    @DisplayName("예약 일자가 주말이고, 메인 메뉴가 2개 일 때, 주말 할인 금액은 4046원 이여야 함")
    @Test
    void ConfirmWeekendDiscountByWeekendAndTwoMainMenu() {
        //given
        Calendar calendar = new Calendar();
        WeekendDiscount weekendDiscount = new WeekendDiscount(calendar);
        LocalDate date = LocalDate.of(2023, 12, 8);
        int mainQuantity = 2;
        int weekendDiscountAmount;

        //when
        weekendDiscountAmount = weekendDiscount.getBenefit(date, mainQuantity);

        //then
        assertThat(weekendDiscountAmount).isEqualTo(4046);
    }

    @DisplayName("예약 일자가 주말이고, 메인 메뉴가 0개 일 때, 주말 할인 금액은 0원 이여야 함")
    @Test
    void ConfirmWeekendDiscountByWeekendAndNoneMainMenu() {
        //given
        Calendar calendar = new Calendar();
        WeekendDiscount weekendDiscount = new WeekendDiscount(calendar);
        LocalDate date = LocalDate.of(2023, 12, 8);
        int mainQuantity = 0;
        int weekendDiscountAmount;

        //when
        weekendDiscountAmount = weekendDiscount.getBenefit(date, mainQuantity);

        //then
        assertThat(weekendDiscountAmount).isEqualTo(0);
    }

    @DisplayName("예약 일자가 평일이고, 메인 메뉴가 2개 일 때, 주말 할인 금액은 0원 이여야 함")
    @Test
    void ConfirmWeekendDiscountByWeekdayAndTwoMainMenu() {
        //given
        Calendar calendar = new Calendar();
        WeekendDiscount weekendDiscount = new WeekendDiscount(calendar);
        LocalDate date = LocalDate.of(2023, 12, 3);
        int mainQuantity = 2;
        int weekendDiscountAmount;

        //when
        weekendDiscountAmount = weekendDiscount.getBenefit(date, mainQuantity);

        //then
        assertThat(weekendDiscountAmount).isEqualTo(0);
    }

    @DisplayName("예약 일자가 12월이 아닌 주말이고, 메인 메뉴가 2개 일 때, 주말 할인 금액은 0원 이여야 함")
    @Test
    void ConfirmWeekendDiscountByWeekendWithinDecemberAndTwoMainMenu() {
        //given
        Calendar calendar = new Calendar();
        WeekendDiscount weekendDiscount = new WeekendDiscount(calendar);
        LocalDate date = LocalDate.of(2023, 11, 4);
        int mainQuantity = 2;
        int weekendDiscountAmount;

        //when
        weekendDiscountAmount = weekendDiscount.getBenefit(date, mainQuantity);

        //then
        assertThat(weekendDiscountAmount).isEqualTo(0);
    }

    @DisplayName("할인 전 총주문 금액이 12만원 이상일 때, 샴페인 1개가 주어져야 함")
    @Test
    void ConfirmGiftOfGiftEventByOrderAmount() {
        //given
        Calendar calendar = new Calendar();
        Menu menu = new Menu();
        GiftEvent giftEvent = new GiftEvent(calendar);
        LocalDate date = LocalDate.of(2023, 12, 3);
        int orderAmount = 120000;
        LinkedHashMap<String, Integer> gift;

        //when
        giftEvent.getBenefit(date, menu, orderAmount);
        gift = giftEvent.getGift();

        //then
        assertThat(gift.size()).isEqualTo(1);
        assertThat(gift.containsKey("샴페인")).isTrue();
        assertThat(gift.get("샴페인")).isEqualTo(1);
    }

    @DisplayName("할인 전 총주문 금액이 12만원 이상일 때, 샴페인 1개에 대한 혜택금이 주어져야 함")
    @Test
    void ConfirmDiscountOfGiftEventByOrderAmount() {
        //given
        Calendar calendar = new Calendar();
        Menu menu = new Menu();
        GiftEvent giftEvent = new GiftEvent(calendar);
        LocalDate date = LocalDate.of(2023, 12, 3);
        int orderAmount = 120000;
        int GiftDiscountAmount;

        //when
        GiftDiscountAmount = giftEvent.getBenefit(date, menu, orderAmount);

        //then
        assertThat(GiftDiscountAmount).isEqualTo(25000);
    }

    @DisplayName("할인 전 총주문 금액이 12만원 미만일 때, 샴페인 0개가 주어져야 함")
    @Test
    void ConfirmGiftOfGiftEventByLowOrderAmount() {
        //given
        Calendar calendar = new Calendar();
        Menu menu = new Menu();
        GiftEvent giftEvent = new GiftEvent(calendar);
        LocalDate date = LocalDate.of(2023, 12, 3);
        int orderAmount = 119999;
        LinkedHashMap<String, Integer> gift;

        //when
        giftEvent.getBenefit(date, menu, orderAmount);
        gift = giftEvent.getGift();

        //then
        assertThat(gift.size()).isEqualTo(1);
        assertThat(gift.containsKey("샴페인")).isTrue();
        assertThat(gift.get("샴페인")).isEqualTo(0);
    }

    @DisplayName("할인 전 총주문 금액이 12만원 미만일 때, 증정품에 대한 혜택금은 주어지지 않아야 함")
    @Test
    void ConfirmDiscountOfGiftEventByLowOrderAmount() {
        //given
        Calendar calendar = new Calendar();
        Menu menu = new Menu();
        GiftEvent giftEvent = new GiftEvent(calendar);
        LocalDate date = LocalDate.of(2023, 12, 3);
        int orderAmount = 119999;
        int GiftDiscountAmount;

        //when
        GiftDiscountAmount = giftEvent.getBenefit(date, menu, orderAmount);

        //then
        assertThat(GiftDiscountAmount).isEqualTo(0);
    }

    @DisplayName("12월이 아닌 날의 할인 전 총주문 금액이 12만원 이상일 때, 샴페인 0개가 주어져야 함")
    @Test
    void ConfirmGiftOfGiftEventByNotDecember() {
        //given
        Calendar calendar = new Calendar();
        Menu menu = new Menu();
        GiftEvent giftEvent = new GiftEvent(calendar);
        LocalDate date = LocalDate.of(2023, 11, 3);
        int orderAmount = 120000;
        LinkedHashMap<String, Integer> gift;

        //when
        giftEvent.getBenefit(date, menu, orderAmount);
        gift = giftEvent.getGift();

        //then
        assertThat(gift.size()).isEqualTo(1);
        assertThat(gift.containsKey("샴페인")).isTrue();
        assertThat(gift.get("샴페인")).isEqualTo(0);
    }

    @DisplayName("12월이 아닌 날의 할인 전 총주문 금액이 12만원 이상일 때, 증정품에 대한 혜택금은 주어지지 않아야 함")
    @Test
    void ConfirmDiscountOfGiftEventByNotDecember() {
        //given
        Calendar calendar = new Calendar();
        Menu menu = new Menu();
        GiftEvent giftEvent = new GiftEvent(calendar);
        LocalDate date = LocalDate.of(2023, 11, 3);
        int orderAmount = 120000;
        int GiftDiscountAmount;

        //when
        GiftDiscountAmount = giftEvent.getBenefit(date, menu, orderAmount);

        //then
        assertThat(GiftDiscountAmount).isEqualTo(0);
    }

    @DisplayName("총혜택 금액이 5천원 미만일 때, 배지는 주어지지 않아야 함")
    @Test
    void ConfirmNoneBadgeOfEventBadgeEventByBenefitAmount() {
        //given
        Calendar calendar = new Calendar();
        EventBadge eventBadge = new EventBadge(calendar);
        LocalDate date = LocalDate.of(2023, 12, 3);
        int benefitAmount = 4999;
        Badge badge;

        //when
        badge = eventBadge.getBenefit(date, benefitAmount);

        //then
        assertThat(badge.getBadgeName()).isEqualTo("없음");
    }

    @DisplayName("총혜택 금액이 5천원 이상 1만원 미만일 때, 별 배지가 주어져야 함")
    @Test
    void ConfirmStarBadgeOfEventBadgeEventByBenefitAmount() {
        //given
        Calendar calendar = new Calendar();
        EventBadge eventBadge = new EventBadge(calendar);
        LocalDate date = LocalDate.of(2023, 12, 3);
        int benefitAmount = 9999;
        Badge badge;

        //when
        badge = eventBadge.getBenefit(date, benefitAmount);

        //then
        assertThat(badge.getBadgeName()).isEqualTo("별");
    }

    @DisplayName("총혜택 금액이 1만원 이상 2만원 미만일 때, 트리 배지가 주어져야 함")
    @Test
    void ConfirmTreeBadgeOfEventBadgeEventByBenefitAmount() {
        //given
        Calendar calendar = new Calendar();
        EventBadge eventBadge = new EventBadge(calendar);
        LocalDate date = LocalDate.of(2023, 12, 3);
        int benefitAmount = 19999;
        Badge badge;

        //when
        badge = eventBadge.getBenefit(date, benefitAmount);

        //then
        assertThat(badge.getBadgeName()).isEqualTo("트리");
    }

    @DisplayName("총혜택 금액이 2만원 이상일 때, 산타 배지가 주어져야 함")
    @Test
    void ConfirmSantaBadgeOfEventBadgeEventByBenefitAmount() {
        //given
        Calendar calendar = new Calendar();
        EventBadge eventBadge = new EventBadge(calendar);
        LocalDate date = LocalDate.of(2023, 12, 3);
        int benefitAmount = 20000;
        Badge badge;

        //when
        badge = eventBadge.getBenefit(date, benefitAmount);

        //then
        assertThat(badge.getBadgeName()).isEqualTo("산타");
    }

    @DisplayName("12월이 아닌 월의 총혜택 금액이 2만원 이상일 때, 배지는 주어지지 않아야 함")
    @Test
    void ConfirmEventBadgeEventByDateWithinDecember() {
        //given
        Calendar calendar = new Calendar();
        EventBadge eventBadge = new EventBadge(calendar);
        LocalDate date = LocalDate.of(2023, 11, 3);
        int benefitAmount = 20000;
        Badge badge;

        //when
        badge = eventBadge.getBenefit(date, benefitAmount);

        //then
        assertThat(badge.getBadgeName()).isEqualTo("없음");
    }
}
