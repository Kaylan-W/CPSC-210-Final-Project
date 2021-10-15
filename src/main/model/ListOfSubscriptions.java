package model;

import java.util.ArrayList;
import java.util.List;

public class ListOfSubscriptions {

    private List<Subscription> subsList;

    public ListOfSubscriptions() {
        subsList = new ArrayList<>();
    }

    // EFFECTS: Creates a new subscription and adds it to the existing list of subscriptions.
    public void addSub(String service, Double cost, Integer renewalType) {
        Subscription s = new Subscription(service, cost, renewalType);
        subsList.add(s);
    }

    public int size() {
        return subsList.size();
    }

    // REQUIRES: subscription exists in the list
    // EFFECTS: returns information of a specified subscription in the list
    public String getSub(Integer i) {
        Subscription s = subsList.get(i);
        return ("Name:" + s.getName() + " , ID:" + s.getId());
    }

   /* // EFFECTS: checks to see if a subscription exists in the current list of subscription
    public boolean containsSub(Subscription s) {
        return subsList.contains(s);
    } */

    // REQUIRES: there are no duplicate subscriptions in the list
    //  EFFECTS: searches the list of subscriptions for a given subscription by its name, returns its index in
    //          the list if found, or -1 if it does not exist.
    public Integer searchForSub(String searchName) {
        int index = -1;
        for (int i = 0; i < subsList.size(); i++) {
            Subscription s = subsList.get(i);
            String subName = s.getName();
            if (searchName.equals(subName)) {
                index = i;
            }
        }
        return index;
    }

    // EFFECTS: removes a subscription from the list of subscription, or produces an error if the
    //          subscription does not exist
    // MODIFIES: ListOfSubscription, removes the subscription from the list
    public String cancelSub(String cancelName) {
        Integer i = searchForSub(cancelName);
        if (i == -1) {
            return "Error! Subscription does not exist!";
        } else {
            Subscription s = subsList.get(i);
            subsList.remove(s);
            return "Subscription successfully removed!";
        }
    }

}

