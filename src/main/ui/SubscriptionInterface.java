package ui;

import java.util.Scanner;
import model.Subscription;
import model.ListOfSubscriptions;

// Console Application for subscription manager
public class SubscriptionInterface {
    private Scanner readInput = new Scanner(System.in);
    ListOfSubscriptions newList = new ListOfSubscriptions();

    public SubscriptionInterface() {
        runInterface();
    }

    private void runInterface() {
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
            } else if (input == 5) {
                doAddNotification(); // not yet coded
            } else {
                System.out.println("Invalid selection!");
            }
        }
        System.out.println("Thank you for using SubHub!");
    }

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

    private void doAddSubs() {
        String storeService;
        Double storeCost;
        Integer storeRenewalType;
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
        newList.addSub(storeService, storeCost, storeRenewalType);
    }

    private void doViewList() {
        int size = newList.size();
        if (size == 0) {
            System.out.println("There are no subscriptions!");
        } else {
            System.out.println("NAME\t\t COST ($)\t RENEWAL PERIOD\t");
            for (Integer ind = 0; ind < size; ind++) {
                System.out.println(newList.getSub(ind));
            }
        }
    }

    private void doCancelSub() {
        System.out.println("Enter the name of the subscription you wish to cancel: ");
        String cancelName = readInput.next();
        System.out.println(newList.cancelSub(cancelName));
    }

    private void doCheckRenewal() {
        System.out.println("write check renewal function! and change return type of this one!!");
    }

    private void doAddNotification() {
        System.out.println("write add notif function! and change return type of this one!!");
    }
}
