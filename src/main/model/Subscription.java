package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

// Represents a subscription with a name, cost, renewal period and purchase date.
public class Subscription {
    private final String name;              // the name of the service subscribed to
    private final Double amount;            // the cost of the subscription per period
    private final String periodType;           // how often the subscription renews
    private final LocalDate purchaseDate;


    /* REQUIRES: name has a non-zero length
     * EFFECTS: The service name for the subscription is set to name; The cost per period is saved as double;
     *          The renewal period is represented as an integer then interpreted by the code to be either
     *          weekly, monthly or yearly; The purchase date is converted to be in the format dd-mm-yyyy. */
    public Subscription(String service, Double cost, Integer renewalType, String pdate) {
        name = service;
        amount = cost;
        if (renewalType == 1) {
            periodType = "Weekly";
        } else if (renewalType == 2) {
            periodType = "Monthly";
        } else if (renewalType == 3) {
            periodType = "Yearly";
        } else {
            periodType = "Error!";
        }
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
        purchaseDate = LocalDate.parse(pdate, formatter1);
    }

    public String getName() {
        return name;
    }

    public Double getCost() {
        return amount;
    }

    public String getPeriodType() {
        return periodType;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    // EFFECTS: calculates the renewal date of a subscription by adding the time specified as the renewal period to
    //          the purchase date.
    public LocalDate calculateRenewalDate() {
        LocalDate pdate = this.getPurchaseDate();
        LocalDate rdate;
        String renewalType = this.getPeriodType();
        if (renewalType.equals("Weekly")) {
            rdate = pdate.plusWeeks(1);
        } else if (renewalType.equals("Monthly")) {
            rdate = pdate.plusMonths(1);
        } else {
            rdate = pdate.plusYears(1);
        }
        return rdate;
    }
}


