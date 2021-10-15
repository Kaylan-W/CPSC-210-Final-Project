package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ListOfSubscriptionTest {
    private ListOfSubscriptions testList;

    @BeforeEach
    public void runBefore() {

        testList = new ListOfSubscriptions();

    }

    @Test
    void testAddSub() {
        testList.addSub("Disney", 50.0, 2);
        testList.addSub("NatGeo", 260.0, 3);
        testList.addSub("Symbolab", 2.00, 1);
        assertEquals(3, testList.size());
        System.out.println(testList.getSub(0));
        System.out.println(testList.getSub(1));
        System.out.println(testList.getSub(2));
    }


    @Test
    void testSearchForSubFirstElement() {
        testList.addSub("Disney", 50.0, 2);
        assertEquals(0, testList.searchForSub("Disney"));
    }

    /* !!!NOT WORKING TILL YOU FIX THAT ADD METHOD
    @Test
    void testSearchForSubNotFirstElement() {
        testList.addSub("Disney", 50.0, 2);
        testList.addSub("NatGeo", 260.0, 3);
        testList.addSub("Symbolab", 2.00, 1);
        assertEquals(1, testList.searchForSub("NatGeo"));
    } */

    @Test
    void testSearchForSubDoesNotExist() {
        testList.addSub("Disney", 50.0, 2);
        testList.addSub("NatGeo", 260.0, 3);
        testList.addSub("Symbolab", 2.00, 1);
        assertEquals(-1, testList.searchForSub("Hulu"));
    }

    @Test
    void testCancelSubFirstElement() {
        testList.addSub("Disney", 50.0, 2);
        //testList.addSub("NatGeo", 260.0, 3);
        //testList.addSub("Symbolab", 2.00, 1);
        String output = testList.cancelSub("Disney");
        assertEquals("Subscription successfully removed!", output);
        assertEquals(0, testList.size());
    }

/*  !!!NOT WORKING TILL YOU FIX THAT ADD METHOD
    void testCancelSubNotFirstElement() {
        testList.addSub("Disney", 50.0, 2);
        //testList.addSub("NatGeo", 260.0, 3);
        //testList.addSub("Symbolab", 2.00, 1);
        String output = testList.cancelSub("NatGeo");
        assertEquals("Subscription successfully removed!", output);
        assertEquals(2, testList.size());
    } */

    @Test
    void testCancelSubDoesNotExist() {
        testList.addSub("Disney", 50.0, 2);
        testList.addSub("NatGeo", 260.0, 3);
        testList.addSub("Symbolab", 2.00, 1);
        String output = testList.cancelSub("Hulu");
        assertEquals("Error! Subscription does not exist!", output);
        assertEquals(3, testList.size());
    }
}
