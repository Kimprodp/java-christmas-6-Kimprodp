package christmas.controller;

import christmas.domain.Calendar;
import christmas.domain.Menu;
import christmas.domain.Reservation;
import christmas.service.EventService;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.LinkedHashMap;

public class Controller {

    private final InputView inputView;
    private final OutputView outputView;
    private final InputProcessor inputProcessor;
    private final Calendar calendar;
    private final Menu menu;
    private final Reservation reservation;
    private final EventService eventService;

    public Controller() {
        inputView = new InputView();
        outputView = new OutputView();
        inputProcessor = new InputProcessor();
        calendar = new Calendar();
        menu = new Menu();
        reservation = new Reservation();
        eventService = new EventService(calendar);
    }

    public void run() {
        String inputDate = inputView.readDate(calendar.getMonth());
        registerVisitDate(inputDate);

        String inputMenu = inputView.readMenu();
        registerMenu(inputMenu);

        showOrderMenu();
        runEventService();
        showEventBenefits();
    }

    private void registerVisitDate(String input) {
        try {
            int date = inputProcessor.convertDate(input);
            reservation.registerVisitDate(calendar, date);
        } catch (IllegalArgumentException e) {
            registerVisitDate(inputView.readAgain(e.getMessage()));
        }
    }

    private void registerMenu(String input) {
        try {
            LinkedHashMap<String, Integer> orderMenu = inputProcessor.convertMenu(input);
            reservation.registerOrderMenu(menu, orderMenu);
        } catch (IllegalArgumentException e) {
            registerMenu(inputView.readAgain(e.getMessage()));
        }
    }

    private void showOrderMenu() {
        outputView.printEventPreview();
        outputView.printMenu(reservation.getOrderMenu());
    }

    private void runEventService() {
        reservation.calculateOrderAmount(menu);
        eventService.setReservationInfo(reservation);
        reservation.applyEvent(eventService, menu);
    }

    private void showEventBenefits() {
        outputView.printOrderAmount(reservation.getOrderAmount());
        outputView.printGiftMenu(reservation.getGiftItem());
        outputView.printBenefitDetails(reservation.getBenefitDetails());
        outputView.printBenefitAmount(reservation.getBenefitAmount());
        outputView.printExpectedPaymentAmount(reservation.getExpectedPaymentAmount());
        outputView.printEventBadge(reservation.getEventBadge());
    }
}
