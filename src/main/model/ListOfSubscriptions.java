package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Convert;

import java.util.ArrayList;
import java.util.List;

// Represents the list of all subscriptions
public class ListOfSubscriptions implements Convert {

    private final List<Subscription> subsList;

    // EFFECTS: creates an array list to store all subscriptions.
    public ListOfSubscriptions() {
        subsList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: Creates a new subscription given the data and adds it to the existing list of subscriptions.
    public void addSub(String service, Double cost, Integer renewalType, String pdate) {
        Subscription s = new Subscription(service, cost, renewalType, pdate);
        subsList.add(s);
    }

    // MODIFIES: this
    // EFFECTS: Adds a given subscription to the existing list of subscriptions.
    public void addSub(Subscription s) {
        subsList.add(s);
    }

    // EFFECTS: returns the size of the list
    public int size() {
        return subsList.size();
    }

    // EFFECTS: returns information of a specified subscription in the list as a string
    public String getSubString(Integer i) {
        if (i < subsList.size()) {
            Subscription s = subsList.get(i);
            return (s.getName() + "\t\t" + s.getCost() + "\t\t\t" + s.getPeriodType() + "\t\t\t" + s.getPurchaseDate());
        } else {
            return ("Subscription does not exist!");
        }
    }

    // EFFECTS: returns the subscription found at a particular index in the list
    public Subscription getSub(Integer i) {
        Subscription s;
        if (i < subsList.size()) {
            s = subsList.get(i);
            return s;
        } else {
            return null;
        }
    }

   // EFFECTS: checks to see if a subscription exists in the current list of subscription
    public boolean containsSub(Subscription s) {
        String containsName = s.getName();
        int searchResult = searchForIndex(containsName);
        return (searchResult != -1);
    }

    // REQUIRES: there are no duplicate subscriptions in the list
    //  EFFECTS: searches the list of subscriptions for a given subscription by its name, returns its index in
    //          the list if found, or -1 if it does not exist.
    public Integer searchForIndex(String searchName) {
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

    // MODIFIES: this
    // EFFECTS: removes a subscription from the list of subscription, or produces an error if the
    //          subscription does not exist
    public String cancelSub(String cancelName) {
        Integer i = searchForIndex(cancelName);
        if (i == -1) {
            return "Error! Subscription does not exist!";
        } else {
            Subscription s = subsList.get(i);
            subsList.remove(s);
            return "Subscription successfully removed!";
        }
    }

    // EFFECTS: Returns a list of subscriptions in JSON format
    @Override
    public JSONArray jsonConvertor() {
        JSONArray jsonA = new JSONArray();
        jsonA = this.losToJson();
        return jsonA;
    }

    // EFFECTS: Returns a list of subscriptions as a JSON array format
    public JSONArray losToJson() {
        JSONArray array = new JSONArray();
        for (Subscription s : subsList) {
            array.put(s.jsonSubConvertor());
        }
        return array;
    }
}

