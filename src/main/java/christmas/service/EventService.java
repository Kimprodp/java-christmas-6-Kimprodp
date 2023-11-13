package christmas.service;

import christmas.domain.Calendar;
import christmas.domain.Foods;
import christmas.domain.Menu;
import christmas.domain.Reservation;
import christmas.domain.event.ChristmasDiscount;
import christmas.domain.event.EventBadge;
import christmas.domain.event.EventBadge.Badge;
import christmas.domain.event.GiftEvent;
import christmas.domain.event.SpecialDiscount;
import christmas.domain.event.WeekdayDiscount;
import christmas.domain.event.WeekendDiscount;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class EventService {

    private final ChristmasDiscount christmasDiscount;
    private final WeekdayDiscount weekdayDiscount;
    private final WeekendDiscount weekendDiscount;
    private final SpecialDiscount specialDiscount;
    private final GiftEvent giftEvent;
    private final EventBadge eventBadge;

    LinkedHashMap<String, Integer> benefitDetails = new LinkedHashMap<>();
    private LocalDate visitDate;
    private int mainQuantity;
    private int dessertQuantity;
    private int orderAmount;

    public EventService(Calendar calendar) {
        christmasDiscount = new ChristmasDiscount(calendar);
        weekdayDiscount = new WeekdayDiscount(calendar);
        weekendDiscount = new WeekendDiscount(calendar);
        specialDiscount = new SpecialDiscount(calendar);
        giftEvent = new GiftEvent(calendar);
        eventBadge = new EventBadge(calendar);
    }

    public void setReservationInfo(Reservation reservation) {
        setVisitDate(reservation);
        setMainQuantity(reservation);
        setDessertQuantity(reservation);
        setOrderAmount(reservation);
    }

    public LinkedHashMap<String, Integer> applyBenefit(Menu menu) {
        benefitDetails.put(christmasDiscount.getEventName(), christmasDiscount.getBenefit(visitDate));
        benefitDetails.put(weekdayDiscount.getEventName(), weekdayDiscount.getBenefit(visitDate, dessertQuantity));
        benefitDetails.put(weekendDiscount.getEventName(), weekendDiscount.getBenefit(visitDate, mainQuantity));
        benefitDetails.put(specialDiscount.getEventName(), specialDiscount.getBenefit(visitDate));
        benefitDetails.put(giftEvent.getEventName(),giftEvent.getBenefit(visitDate, menu, orderAmount));
        return benefitDetails;
    }

    public int applyDiscount() {
        return benefitDetails.entrySet()
                .stream()
                .filter(name -> !name.getKey().equals(giftEvent.getEventName()))
                .mapToInt(Map.Entry::getValue)
                .sum();
    }


    public LinkedHashMap<String, Integer> applyGiftEvents() {
        return giftEvent.getGift();
    }

    public Badge applyEventBadge(int benefitAmount) {
        return eventBadge.getBenefit(visitDate, benefitAmount);
    }

    private void setVisitDate(Reservation reservation) {
        visitDate = reservation.getVisitDate();
    }

    private void setMainQuantity(Reservation reservation) {
        mainQuantity = reservation.getOrderMenu().entrySet().stream()
                .filter(entry -> Foods.MAIN.isContain(entry.getKey()))
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    private void setDessertQuantity(Reservation reservation) {
        dessertQuantity = reservation.getOrderMenu().entrySet().stream()
                .filter(entry -> Foods.DESSERT.isContain(entry.getKey()))
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    private void setOrderAmount(Reservation reservation) {
        orderAmount = reservation.getOrderAmount();
    }
}
