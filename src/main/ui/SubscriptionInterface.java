package ui;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Scanner;
import model.ListOfSubscriptions;
import model.Subscription;

// Console Application for subscription manager
public class SubscriptionInterface {
    private Scanner readInput = new Scanner(System.in);
    ListOfSubscriptions newList = new ListOfSubscriptions();

    // EFFECTS: runs the interface
    public SubscriptionInterface() throws ParseException {
        runInterface();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runInterface() throws ParseException {
        int input;
        boolean loop = true;

        while (loop) {
            displayMenu();
            input = readInput.nextInt();

            if (input == 0) {
                loop = false;
            } else if (input == 1) {
                doAddSubs();
            } else if (input == 2) {
                doViewList();
            } else if (input == 3) {
                doCancelSub();
            } else if (input == 4) {
                doCheckRenewal(); // not yet coded
            } else {
                System.out.println("Invalid selection!");
            }
        }
        System.out.println("Thank you for using SubHub!");
    }

    // EFFECTS: outputs the list of application functionalities and executes methods based on user input
    private void displayMenu() {
        System.out.println("Welcome to SubHub!");
        System.out.println("\n\n\n**********\tManage Subscriptions\t**********");
        System.out.println("\n\nAdd a new subscription ............... 1");
        System.out.println("\nView existing subscriptions .......... 2");
        System.out.println("\nCancel a subscription ................ 3");
        System.out.println("\n\n\n**********\tSubscription Renewals\t**********");
        System.out.println("\n\nCheck renewal dates .................. 4");
        System.out.println("\nAdd renewal reminder ................. 5");
        System.out.println("\n\n\n**********\tApplication Controls \t**********");
        System.out.println("\n\nExit application ..................... 0");
    }

    // MODIFIES: this
    // EFFECTS: Creates a new subscription and adds it to the list
    private void doAddSubs() throws ParseException {
        String storeService;
        Double storeCost;
        Integer storeRenewalType;
        String storePDate;
        System.out.println("Name of service: ");
        storeService = readInput.next();
        System.out.println("\nHow often does the service need to be renewed?");
        System.out.println("Enter 1 for Weekly");
        System.out.println("Enter 2 for Monthly");
        System.out.println("Enter 3 for Yearly");
        System.out.println("\nSelection: ");
        storeRenewalType = readInput.nextInt();
        System.out.println("\nCost of service per period: $");
        storeCost = readInput.nextDouble();
        System.out.println("\nPurchase Date (dd-mm-yyyy) :");
        storePDate = readInput.next();
        newList.addSub(storeService, storeCost, storeRenewalType, storePDate);
    }

    // EFFECTS: Outputs a list of all subscriptions in the list
    private void doViewList() {
        int size = newList.size();
        if (size == 0) {
            System.out.println("There are no subscriptions!");
        } else {
            System.out.println("NAME\t\t COST ($)\t RENEWAL PERIOD\t\t\t PURCHASE DATE");
            for (Integer ind = 0; ind < size; ind++) {
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
            LocalDate rdate = s.calculateRenewalDate();
            System.out.println("This subscription will be renewed on " + rdate);
        }
    }
}
