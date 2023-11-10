package christmas.controller;

import christmas.domain.Calendar;
import christmas.domain.Menu;
import christmas.domain.Reservation;
import christmas.view.InputView;
import java.util.HashMap;

public class Controller {

    private InputView inputView;
    private InputProcessor inputProcessor;
    private Calendar calendar;
    private Menu menu;
    private Reservation reservation;

    public Controller() {
        inputView = new InputView();
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
            HashMap<String, Integer> orderMenu = inputProcessor.convertMenu(input);
            reservation.registerOrderMenu(menu, orderMenu);
        } catch (IllegalArgumentException e) {
            registerMenu(inputView.readAgain(e.getMessage()));
        }
    }
}
