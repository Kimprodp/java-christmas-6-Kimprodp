package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String WELCOME_EVENT_PLANER = "안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.";
    private static final String ASK_DATE_OF_VISIT = "%d월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ASK_MENU = "주문하실 메뉴를 메뉴와 개수를 알려 주세요.";
    private static final String ASK_MENU_EG = " (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";


    public String readDate(int month) {
        System.out.println(String.format(WELCOME_EVENT_PLANER, month));
        System.out.println(String.format(ASK_DATE_OF_VISIT, month));
        return Console.readLine();
    }

    public String readMenu() {
        System.out.print(ASK_MENU);
        System.out.println(ASK_MENU_EG);
        return Console.readLine();
    }

    public String readAgain(String errorMessage) {
        System.out.println(errorMessage);
        return Console.readLine();
    }
}
