package christmas.controller;

import christmas.view.ErrorMessage;
import java.util.Arrays;
import java.util.LinkedHashMap;
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

    public LinkedHashMap<String, Integer> convertMenu(String input) {
        validateEmpty(input);
        validateBlank(input);
        validateHyphen(input);

        LinkedHashMap<String, Integer> separatedByHyphen = new LinkedHashMap<>();
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
            throw new IllegalArgumentException(ErrorMessage.MENU_ERROR);
        }
    }

    private void separateHyphen(String input, LinkedHashMap<String, Integer> separatedByHyphen) {
        if (input.contains(COMMA)) {
            separateInputWithCommas(input, separatedByHyphen);
        }
        if (!input.contains(COMMA)) {
            separateInputWithoutCommas(input, separatedByHyphen);
        }
    }

    private void separateInputWithCommas(String input, LinkedHashMap<String, Integer> separatedByHyphen) {
        List<String> separatedByComma = Arrays.asList(input.split(COMMA));

        for (String value : separatedByComma) {
            List<String> separateValue = Arrays.asList(value.split(HYPHEN));
            String menuName = separateValue.get(0);
            int menuQuantity = validateNumberOfMenuQuantity(separateValue.get(1));
            separatedByHyphen.put(menuName, menuQuantity);
        }

        validateDuplicateOfMenuName(separatedByComma.size(), separatedByHyphen.size());
    }

    private void separateInputWithoutCommas(String input, LinkedHashMap<String, Integer> separatedByHyphen) {
        List<String> separateValue = Arrays.asList(input.split(HYPHEN));
        String menuName = separateValue.get(0);
        int menuQuantity = validateNumberOfMenuQuantity(separateValue.get(1));
        separatedByHyphen.put(menuName, menuQuantity);
    }

    private void validateDuplicateOfMenuName(int firstSize, int secondSize) {
        if (firstSize != secondSize) {
            throw new IllegalArgumentException(ErrorMessage.MENU_ERROR);
        }
    }

    private int validateNumberOfMenuQuantity(String input) {
        try {
            return  Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.MENU_ERROR);
        }
    }
}
