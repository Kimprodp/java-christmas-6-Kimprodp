package christmas.view;

public class InputValidation {

    public int validateNumber(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.DATE_ERROR);
        }
        return Integer.parseInt(input);
    }
}
