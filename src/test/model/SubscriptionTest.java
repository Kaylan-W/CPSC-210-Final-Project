package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class SubscriptionTest {
    private Subscription test;

    @BeforeEach
    void runBefore() throws ParseException {
        test = new Subscription("Netflix", 500.0, 2, "12-04-2021");
    }

    @Test
    void testCreateSubscription() {
        assertEquals("Netflix", test.getName());
        assertEquals(500.0, test.getCost());
        assertEquals("Monthly", test.getPeriodType());
    }

    @Test
    void testCreateSubscriptionInvalidPeriod() throws ParseException {
        Subscription huluSubscription = new Subscription("Hulu", 100.0, 4, "04-04-2021");
        assertEquals("Hulu", huluSubscription.getName());
        assertEquals("Error!", huluSubscription.getPeriodType());
    }

    @Test
    void testCalculateRenewalDate() {
        LocalDate returnDate = test.calculateRenewalDate();
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
        LocalDate testDate = LocalDate.parse("12-05-2021", formatter1);
        assertTrue (returnDate.isEqual(testDate));  
    }

}