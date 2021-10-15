package model;

public class Subscription {
    private int id;                   // subscription ID
    private static int nextId = 1;    // increments ID
    private String name;              // the name of the service subscribed to
    private Double amount;            // the cost of the subscription per period
    private String periodType;           // how often the subscription renews


    /* EFFECTS: The service for the subscription is set to name;
     *          Each subscription has a unique ID;
     *          The purchase date and renewal date are each represented as 3 individual integers,
     *          one for day, one for month, and one for year. */
    public Subscription(String service, Double cost, Integer renewalType) {
        id = nextId++;
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

    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Double getCost() {
        return amount;
    }

    public String getPeriodType() {
        return periodType;
    }

}



