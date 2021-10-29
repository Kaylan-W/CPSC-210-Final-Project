package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.*;

class SubscriptionTest {
    private Subscription test;

    @BeforeEach
    void runBefore() {
        test = new Subscription("Netflix", 500.0, 2, "12-04-2021");
    }

    @Test
    void testCreateSubscription() {
        assertEquals("Netflix", test.getName());
        assertEquals(500.0, test.getCost());
        assertEquals("Monthly", test.getPeriodType());
    }

    @Test
    void testCalculateRenewalDateWeekly() {
        Subscription testWeekly = new Subscription("Disney", 250.0, 1, "10-01-2021");
        LocalDate returnDate = testWeekly.calculateRenewalDate();
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
        LocalDate testDate = LocalDate.parse("17-01-2021", formatter1);
        assertTrue(returnDate.isEqual(testDate));
    }

    @Test
    void testCalculateRenewalDateMonthly() {
        LocalDate returnDate = test.calculateRenewalDate();
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
        LocalDate testDate = LocalDate.parse("12-05-2021", formatter1);
        assertTrue (returnDate.isEqual(testDate));
    }

    @Test
    void testCalculateRenewalDateYearly() {
        Subscription testYearly = new Subscription("Apple TV", 100.0, 3, "01-04-2021");
        LocalDate returnDate = testYearly.calculateRenewalDate();
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
        LocalDate testDate = LocalDate.parse("01-04-2022", formatter1);
        assertTrue(returnDate.isEqual(testDate));
    }
}