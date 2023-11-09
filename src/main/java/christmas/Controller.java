package christmas;

import christmas.view.InputValidation;
import christmas.view.InputView;

public class Controller {

    private InputView inputView;
    private InputValidation inputValidation;
    private Calendar calendar;
    private Reservation reservation;

    public Controller() {
        inputView = new InputView();
        inputValidation = new InputValidation();
        calendar = new Calendar();
        reservation = new Reservation();
    }

    public void run() {
        String input = inputView.readDate(calendar.getMonth());
        registerVisitDate(input);


    }

    private void registerVisitDate(String input) {
        try {
            int date = inputValidation.validateNumber(input);
            reservation.registerVisitDate(calendar, date);
        } catch (IllegalArgumentException e) {
            registerVisitDate(inputView.readAgain(e.getMessage()));
        }
    }
}
