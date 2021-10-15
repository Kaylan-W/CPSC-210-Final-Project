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
        assertTrue(test.getId() > 0);
        assertEquals("Monthly", test.getPeriodType());
    }

    @Test
    void testCreateSubscriptionInvalidPeriod() {
        Subscription huluSubscription = new Subscription("Hulu", 100.0, 4);
        assertEquals("Hulu", huluSubscription.getName());
        assertTrue(huluSubscription.getId() > 0);
        assertEquals("Error!", huluSubscription.getPeriodType());
    }

}