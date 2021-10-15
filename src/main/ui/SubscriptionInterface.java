package ui;

import java.util.Scanner;
import model.Subscription;
import model.ListOfSubscriptions;

// Console Application for subscription manager
public class SubscriptionInterface {
    private Scanner readInput = new Scanner(System.in);

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
                System.out.println("Do addSubs");
            } else if (input == 2) {
                System.out.println("Do viewList");
            } else if (input == 3) {
                System.out.println("Do cancelSub");
            } else if (input == 4) {
                System.out.println("Do checkRenewal");
            } else if (input == 5) {
                System.out.println("Do addNotif");
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
}
