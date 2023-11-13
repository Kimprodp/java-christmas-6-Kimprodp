package christmas.domain.event;

import christmas.domain.Calendar;
import java.time.LocalDate;
import java.util.Arrays;

public class EventBadge {

    public enum Badge {
        NONE("없음", 0),
        STAR("별", 5_000),
        TREE("트리", 10_000),
        SANTA("산타", 20_000);

        private final String badgeName;
        private final int conditionAmount;

        Badge(String badgeName, int conditionAmount) {
            this.badgeName = badgeName;
            this.conditionAmount = conditionAmount;
        }

        public String getBadgeName() {
            return badgeName;
        }

        public int getConditionAmount() {
            return conditionAmount;
        }
    }

    private final Calendar calendar;
    private Badge badge;

    public EventBadge(Calendar calendar) {
        this.calendar = calendar;
    }

    public String getBadgeName(Badge badge) {
        return badge.getBadgeName();
    }

    public Badge getBenefit(LocalDate date, int benefitAmount) {
        applyBadge(date, benefitAmount);
        return badge;
    }

    private boolean isEventAvailable(LocalDate date) {
        return calendar.isDecember(date);
    }

    private void applyBadge(LocalDate date, int benefitAmount) {
        if (benefitAmount < Badge.STAR.getConditionAmount() || !isEventAvailable(date)) {
            badge = Badge.NONE;
        }
        if (benefitAmount >= Badge.STAR.getConditionAmount() && benefitAmount < Badge.TREE.getConditionAmount()) {
            badge = Badge.STAR;
        }
        if (benefitAmount >= Badge.TREE.getConditionAmount() && benefitAmount < Badge.SANTA.getConditionAmount()) {
            badge = Badge.TREE;
        }
        if (benefitAmount >= Badge.SANTA.getConditionAmount()) {
            badge = Badge.SANTA;
        }
    }
}
