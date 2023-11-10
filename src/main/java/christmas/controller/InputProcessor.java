package christmas.controller;

import christmas.view.ErrorMessage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class InputProcessor {

    private static final String BLANK = " ";
    private static final String COMMA = ",";
    private static final String HYPHEN = "-";

    public int convertDate(String input) {
        validateEmpty(input);
        validateBlank(input);
        validateNumber(input);
        return Integer.parseInt(input);
    }

    private void validateEmpty(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_EMPTY);
        }
    }

    private void validateBlank(String input) {
        if (input.contains(BLANK)) {
            throw new IllegalArgumentException(ErrorMessage.CONTAIN_BLANK);
        }
    }

    private void validateNumber(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.DATE_ERROR);
        }
    }
}
