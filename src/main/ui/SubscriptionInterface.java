package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import model.ListOfSubscriptions;
import model.Subscription;
import persistence.JsonReader;
import persistence.JsonWriter;

// Console Application for subscription manager
public class SubscriptionInterface {
    private static final String JSON_STORE = "./data/workroom.json";
    private Scanner readInput = new Scanner(System.in);
    ListOfSubscriptions newList = new ListOfSubscriptions();
    private JsonWriter output;
    private JsonReader input;

    // EFFECTS: runs the interface
    public SubscriptionInterface() {
        output = new JsonWriter(JSON_STORE);
        input = new JsonReader(JSON_STORE);
        runInterface();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runInterface() {
        boolean loop = true;
        while (loop) {
            displayMenu();
            int input = readInput.nextInt();
            if (input == 0) {
                doSaveList(); // saves the progress on the list to the file before quitting
                loop = false;
            } else if (input == 1) {
                doAddSubs();
            } else if (input == 2) {
                doViewList();
            } else if (input == 3) {
                doCancelSub();
            } else if (input == 4) {
                doCheckRenewal();
            } else if (input == 5) {
                doSaveList();
            } else if (input == 6) {
                doOpenList();
            } else {
                System.out.println("Invalid selection!");
            }
        }
    }

    // EFFECTS: outputs the list of application functionalities and executes methods based on user input
    private void displayMenu() {
        System.out.println("\nWelcome to SubHub!");
        System.out.println("\n\n**********\tManage Subscriptions\t**********");
        System.out.println("\n\nAdd a new subscription ............... 1");
        System.out.println("\nView existing subscriptions .......... 2");
        System.out.println("\nCancel a subscription ................ 3");
        System.out.println("\n\n**********\tSubscription Renewals\t**********");
        System.out.println("\n\nCheck renewal dates .................. 4");
        System.out.println("\n\n**********\tApplication Controls \t**********");
        System.out.println("\n\nSave subscriptions ................... 5");
        System.out.println("\n\nOpen saved list ...................... 6");
        System.out.println("\n\nExit application ..................... 0");
    }

    // MODIFIES: this
    // EFFECTS: Creates a new subscription and adds it to the list
    private void doAddSubs() {
        String storeService;
        double storeCost;
        int storeRenewalType;
        String storePDate;
        System.out.println("Name of service: ");
        storeService = readInput.next();
        storeRenewalType = getRenewalType();
        System.out.println("\nCost of service per period: $");
        storeCost = readInput.nextDouble();
        System.out.println("\nPurchase Date (yyyy-mm-dd) :");
        storePDate = readInput.next();
        newList.addSub(storeService, storeCost, storeRenewalType, storePDate);
    }

    private int getRenewalType() {
        int storeRenewalType;
        System.out.println("\nHow often does the service need to be renewed?");
        System.out.println("Enter 1 for Weekly");
        System.out.println("Enter 2 for Monthly");
        System.out.println("Enter 3 for Yearly");
        System.out.println("\nSelection: "); // ERROR CATCHING TO ENSURE CORRECT PERIOD!!
        storeRenewalType = readInput.nextInt();
        while (storeRenewalType < 1 || storeRenewalType > 3) {
            System.out.println("\nInvalid selection!! Please try again.");
            System.out.println("\nHow often does the service need to be renewed?");
            System.out.println("Enter 1 for Weekly");
            System.out.println("Enter 2 for Monthly");
            System.out.println("Enter 3 for Yearly");
            System.out.println("\nSelection: ");
            storeRenewalType = readInput.nextInt();
        }
        return storeRenewalType;
    }

    // EFFECTS: Outputs a list of all subscriptions in the list
    private void doViewList() {
        int size = newList.size();
        if (size == 0) {
            System.out.println("There are no subscriptions!");
        } else {
            System.out.println("NAME\t\t COST ($)\t RENEWAL PERIOD\t\t\t PURCHASE DATE");
            for (int ind = 0; ind < size; ind++) {
                System.out.println(newList.getSubString(ind));
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Deletes a subscription from the list
    private void doCancelSub() {
        System.out.println("Enter the name of the subscription you wish to cancel: ");
        String cancelName = readInput.next();
        System.out.println(newList.cancelSub(cancelName));
    }

    // EFFECTS: returns the renewal date of a given subscription
    private void doCheckRenewal() {
        System.out.println("Enter the name of the subscription to check renewal date for: ");
        String searchName = readInput.next();
        Integer searchIndex = newList.searchForIndex(searchName);
        if (searchIndex == -1) {
            System.out.println("Subscription does not exist!");
        } else {
            Subscription s = newList.getSub(searchIndex);
            LocalDate date = s.calculateRenewalDate();
            System.out.println("This subscription will be renewed on " + date);
        }
    }

    // EFFECTS: saves the current list of subscriptions to file
    private void doSaveList() {
        try {
            output.open();
            output.write(newList);
            output.close();
            System.out.println("Saved subscriptions!");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to read from file!!");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void doOpenList() {
        try {
            newList = input.readList();
            System.out.println("List loaded from file");
        } catch (IOException i) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
