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

        public static Badge getBadgeType(int benefitAmount) {
            return Arrays.stream(values())
                    .filter(badge -> benefitAmount >= badge.conditionAmount)
                    .findFirst()
                    .orElse(NONE);
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
        if (isEventAvailable(date)) {
            badge = Badge.getBadgeType(benefitAmount);
        }
        if (!isEventAvailable(date)) {
            badge = Badge.NONE;
        }
    }
}
