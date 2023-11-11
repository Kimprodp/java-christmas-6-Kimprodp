package christmas.domain.event;

import java.time.LocalDate;

public interface Event {

    public int getBenefit(LocalDate date);
}
