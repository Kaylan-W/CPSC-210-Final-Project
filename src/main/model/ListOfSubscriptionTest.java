package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfSubscriptionTest {
    private ListOfSubscriptions testList;
    Subscription sub1 = new Subscription("Disney", 50.0, 2);
    Subscription sub2 = new Subscription("NatGeo", 260.0, 3);
    Subscription sub3 = new Subscription("Symbolab", 2.00, 1);

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
        assertTrue(testList.containsSub(sub1));
        assertTrue(testList.containsSub(sub2));
        assertTrue(testList.containsSub(sub3));
    }


    @Test
    void testSearchForSubFirstElement() {
        testList.addSub("Disney", 50.0, 2);
        assertEquals(0, testList.searchForIndex("Disney"));
    }


    @Test
    void testSearchForSubNotFirstElement() {
        testList.addSub("Disney", 50.0, 2);
        testList.addSub("NatGeo", 260.0, 3);
        testList.addSub("Symbolab", 2.00, 1);
        assertEquals(1, testList.searchForIndex("NatGeo"));
    }

    @Test
    void testSearchForSubDoesNotExist() {
        testList.addSub("Disney", 50.0, 2);
        testList.addSub("NatGeo", 260.0, 3);
        testList.addSub("Symbolab", 2.00, 1);
        assertEquals(-1, testList.searchForIndex("Hulu"));
    }

    @Test
    void testCancelSubFirstElement() {
        testList.addSub("Disney", 50.0, 2);
        testList.addSub("NatGeo", 260.0, 3);
        testList.addSub("Symbolab", 2.00, 1);
        String output = testList.cancelSub("Disney");
        assertEquals("Subscription successfully removed!", output);
        assertEquals(2, testList.size());
    }

    @Test
    void testCancelSubNotFirstElement() {
        testList.addSub("Disney", 50.0, 2);
        testList.addSub("NatGeo", 260.0, 3);
        testList.addSub("Symbolab", 2.00, 1);
        String output = testList.cancelSub("NatGeo");
        assertEquals("Subscription successfully removed!", output);
        assertEquals(2, testList.size());
    }

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
