package christmas.controller;

import christmas.domain.Calendar;
import christmas.domain.Menu;
import christmas.domain.Reservation;
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

    public Controller() {
        inputView = new InputView();
        outputView = new OutputView();
        inputProcessor = new InputProcessor();
        calendar = new Calendar();
        menu = new Menu();
        reservation = new Reservation();
    }

    public void run() {
        String inputDate = inputView.readDate(calendar.getMonth());
        registerVisitDate(inputDate);

        String inputMenu = inputView.readMenu();
        registerMenu(inputMenu);

        showOrderMenu();

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
}
