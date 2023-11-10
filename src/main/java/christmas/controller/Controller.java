package christmas.controller;

import christmas.domain.Calendar;
import christmas.domain.Reservation;
import christmas.view.InputView;

public class Controller {

    private InputView inputView;
    private InputProcessor inputProcessor;
    private Calendar calendar;
    private Reservation reservation;

    public Controller() {
        inputView = new InputView();
        inputProcessor = new InputProcessor();
        calendar = new Calendar();
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

    /*
    private void registerMenu(String input) {
        try {

        } catch (IllegalArgumentException e) {
            registerMenu(inputView.readAgain(e.getMessage()));
        }
    }
     */
}
