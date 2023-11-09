package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String ASK_DATE_OF_VISIT = "%d월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";

    public String readDate(int month) {
        System.out.println(String.format(ASK_DATE_OF_VISIT, month));
        return Console.readLine();
    }

    public String readAgain(String errorMessage) {
        System.out.println(errorMessage);
        return Console.readLine();
    }
}
