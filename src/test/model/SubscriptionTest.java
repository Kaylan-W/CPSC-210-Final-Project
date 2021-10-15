package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubscriptionTest {
    private Subscription test;

    @BeforeEach
    void runBefore() {
        test = new Subscription("Netflix", 500.0, 2);
    }


    @Test
    void testCreateSubscription() {
        assertEquals("Netflix", test.getName());
        assertEquals(500.0, test.getCost());
        assertEquals("Monthly", test.getPeriodType());
    }

    @Test
    void testCreateSubscriptionInvalidPeriod() {
        Subscription huluSubscription = new Subscription("Hulu", 100.0, 4);
        assertEquals("Hulu", huluSubscription.getName());
        assertEquals("Error!", huluSubscription.getPeriodType());
    }

}