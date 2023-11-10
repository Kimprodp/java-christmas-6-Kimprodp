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

    public HashMap<String, Integer> convertMenu(String input) {
        validateEmpty(input);
        validateBlank(input);
        validateHyphen(input);

        HashMap<String, Integer> separatedByHyphen = new HashMap<>();
        separateHyphen(input, separatedByHyphen);

        return separatedByHyphen;
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

    private void validateHyphen(String input) {
        if (!input.contains(HYPHEN)) {
            throw new IllegalArgumentException(ErrorMessage.NOT_HYPHEN);
        }
    }

    private void separateHyphen(String input, HashMap<String, Integer> separatedByHyphen) {
        if (input.contains(COMMA)) {
            separateInputWithCommas(input, separatedByHyphen);
        }
        if (!input.contains(COMMA)) {
            separateInputWithoutCommas(input, separatedByHyphen);
        }
    }

    private void separateInputWithCommas(String input, HashMap<String, Integer> separatedByHyphen) {
        List<String> separatedByComma = Arrays.asList(input.split(COMMA));
        for (String value : separatedByComma) {
            List<String> separateValue = Arrays.asList(value.split(HYPHEN));
            int menuQuantity = validateInteger(separateValue.get(1));
            separatedByHyphen.put(separateValue.get(0), menuQuantity);
        }
    }

    private void separateInputWithoutCommas(String input, HashMap<String, Integer> separatedByHyphen) {
        List<String> separateValue = Arrays.asList(input.split(HYPHEN));
        int menuQuantity = validateInteger(separateValue.get(1));
        separatedByHyphen.put(separateValue.get(0), menuQuantity);
    }

    private int validateInteger(String input) {
        int menuQuantity;
        try {
            menuQuantity = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.NOT_MenuQuantity_Integer);
        }
        return menuQuantity;
    }










/*
    private List<String> validateComma(String input) {
        if (!input.contains(COMMA)) {
            throw new IllegalArgumentException(ErrorMessage.NOT_COMMA);
        }
        return Arrays.asList(input.split(COMMA));
    }


    private void validateHyphen2(String input, List<String> separatedByComma, HashMap<String, String> separatedByHyphen) {
        if (input.contains(COMMA)) {

        }




        try {
            for (String value : separatedByComma) {
                List<String> separateValue = Arrays.asList(value.split(HYPHEN));
                separatedByHyphen.put(separateValue.get(0), separateValue.get(1));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(ErrorMessage.NOT_HYPHEN);
        }
    }


    private void validateHyphen(List<String> separatedByComma) {
        for (String value : separatedByComma) {
            if (!value.contains(HYPHEN)) {
                throw new IllegalArgumentException(ErrorMessage.NOT_HYPHEN);
            }

        }
    }

     */

}
