package model;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Subscription {
    private String name;              // the name of the service subscribed to
    private Double amount;            // the cost of the subscription per period
    private String periodType;           // how often the subscription renews
    private LocalDate purchaseDate;

    /* EFFECTS: The service for the subscription is set to name;
     *          Each subscription has a unique ID;
     *          The purchase date and renewal date are each represented as 3 individual integers,
     *          one for day, one for month, and one for year. */
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


