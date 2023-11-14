package christmas.view;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class OutputView {

    private static final String EVENT_PREVIEW = "12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU = "<주문 메뉴>";
    private static final String ORDER_MENU_DETAIL = "%s %d개";
    private static final String ORDER_AMOUNT = "<할인 전 총주문 금액>%n%,d원";
    private static final String GIFT_MENU = "<증정 메뉴>";
    private static final String GIFT_ITEM_DETAIL = "%s %d개";
    private static final String BENEFIT = "<혜택 내역>";
    private static final String BENEFIT_DETAIL = "%s: -%,d원";
    private static final String BENEFIT_AMOUNT_ZERO = "<총혜택 금액>%n%,d원";
    private static final String BENEFIT_AMOUNT = "<총혜택 금액>%n-%,d원";
    private static final String PAYMENT_AMOUNT = "<할인 후 예상 결제 금액>%n%,d원";
    private static final String EVENT_BADGE = "<12월 이벤트 배지>%n%s";
    private static final String NONE = "없음";

    public void printEventPreview() {
        System.out.println(EVENT_PREVIEW);
        System.out.println();
    }

    public void printMenu(LinkedHashMap<String, Integer> orderMenu) {
        System.out.println(ORDER_MENU);
        orderMenu.forEach((name, quantity) -> {
            System.out.println(String.format(ORDER_MENU_DETAIL, name, quantity));
        });
        System.out.println();
    }

    public void printOrderAmount(int amount) {
        System.out.println(String.format(ORDER_AMOUNT, amount));
        System.out.println();
    }

    public void printGiftMenu(HashMap<String, Integer> giftMenu) {
        System.out.println(GIFT_MENU);
        giftMenu.forEach((name, quantity) -> {
            if (quantity != 0) {
                System.out.println(String.format(GIFT_ITEM_DETAIL, name, quantity));
            }
            if (quantity == 0) {
                System.out.println(NONE);
            }
        });

        System.out.println();
    }

    public void printBenefitDetails(LinkedHashMap<String, Integer> benefitDetails) {
        System.out.println(BENEFIT);

        if (benefitDetails.values().stream().allMatch(amount -> amount == 0)) {
            System.out.println(NONE);
        }
        if (benefitDetails.values().stream().anyMatch(amount -> amount != 0)) {
            benefitDetails.forEach((name, amount) -> {
                if (amount != 0) {
                    System.out.println(String.format(BENEFIT_DETAIL, name, amount));
                }
            });
        }
        System.out.println();
    }

    public void printBenefitAmount(int amount) {
        if (amount != 0) {
            System.out.println(String.format(BENEFIT_AMOUNT, amount));
        }
        if (amount == 0) {
            System.out.println(String.format(BENEFIT_AMOUNT_ZERO, amount));
        }
        System.out.println();
    }

    public void printExpectedPaymentAmount(int amount) {
        System.out.println(String.format(PAYMENT_AMOUNT, amount));
        System.out.println();
    }

    public void printEventBadge(String badgeName) {
        System.out.println(String.format(EVENT_BADGE, badgeName));
        System.out.println();
    }
}
