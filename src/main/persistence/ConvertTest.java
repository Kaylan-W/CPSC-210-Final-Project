package persistence;

import model.Subscription;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConvertTest {

    // EFFECTS: Checks if a JSON object matches a subscription
    protected void checkConversion(String name, double cost, String periodType, Subscription s) {
        assertEquals(name, s.getName());
        assertEquals(cost, s.getCost());
        assertEquals(periodType, s.getPeriodType());
    }
}
