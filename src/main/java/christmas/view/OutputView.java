package christmas.view;

import java.util.HashMap;

public class OutputView {

    private static final String EVENT_PREVIEW = "12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU = "<주문 메뉴>";
    private static final String ORDER_QUANTITY = "%s %d개";

    public void printEventPreview() {
        System.out.println(EVENT_PREVIEW);
        System.out.println();
    }

    public void printMenu(HashMap<String, Integer> orderMenu) {
        System.out.println(ORDER_MENU);
        orderMenu.forEach((name, quantity) -> {
            System.out.println(String.format(ORDER_QUANTITY, name, quantity));
        });
    }
}
