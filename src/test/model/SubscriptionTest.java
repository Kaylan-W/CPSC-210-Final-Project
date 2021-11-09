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
        test = new Subscription("Netflix", 500.0, 2, "2021-04-12");
    }

    private LocalDate testParse(String s) {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        return LocalDate.parse(s, formatter1);
    }


    @Test
    void testCreateSubscription() {
        assertEquals("Netflix", test.getName());
        assertEquals(500.0, test.getCost());
        assertEquals("Monthly", test.getPeriodType());
    }

    @Test
    void testCalculateRenewalDateWeekly() {
        Subscription testWeekly = new Subscription("Disney", 250.0, 1, "2021-01-10");
        LocalDate returnDate = testWeekly.calculateRenewalDate();
        LocalDate testDate = testParse("2021-01-17");
        assertTrue(returnDate.isEqual(testDate));
    }

    @Test
    void testCalculateRenewalDateMonthly() {
        LocalDate returnDate = test.calculateRenewalDate();
        LocalDate testDate = testParse("2021-05-12");
        assertTrue (returnDate.isEqual(testDate));
    }

    @Test
    void testCalculateRenewalDateYearly() {
        Subscription testYearly = new Subscription("Apple TV", 100.0, 3, "2021-04-01");
        LocalDate returnDate = testYearly.calculateRenewalDate();
        LocalDate testDate = testParse("2022-04-01");
        assertTrue(returnDate.isEqual(testDate));
    }
}