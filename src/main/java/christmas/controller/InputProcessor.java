package christmas.controller;

import christmas.view.ErrorMessage;

public class InputProcessor {

    public int convertDate(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.DATE_ERROR);
        }
        return Integer.parseInt(input);
    }
}
