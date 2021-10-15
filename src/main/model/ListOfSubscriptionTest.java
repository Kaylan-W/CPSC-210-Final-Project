package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfSubscriptionTest {
    private ListOfSubscriptions testList;
    Subscription sub1 = new Subscription("Disney", 50.0, 2, "10-01-2021");
    Subscription sub2 = new Subscription("NatGeo", 260.0, 3, "12-02-2021");
    Subscription sub3 = new Subscription("Symbolab", 2.00, 1, "18-09-2021");
    Subscription sub4 = new Subscription("Hulu", 10.00, 2, "21-11-2021");

    public ListOfSubscriptionTest() throws ParseException {
    }


    @BeforeEach
    public void runBefore() throws ParseException {
        testList = new ListOfSubscriptions();
        testList.addSub("Disney", 50.0, 2, "10-01-2021");
        testList.addSub("NatGeo", 260.0, 3, "12-02-2021");
        testList.addSub("Symbolab", 2.00, 1, "18-09-2021");
    }

    @Test
    void testAddSub() throws ParseException {
        testList.addSub("Hulu", 10.00, 2, "21-11-2021");
        assertEquals(4, testList.size());
        assertTrue(testList.containsSub(sub1));
        assertTrue(testList.containsSub(sub2));
        assertTrue(testList.containsSub(sub3));
        assertTrue(testList.containsSub(sub4));

    }

    @Test
    void testContainsSubFalse() {
        assertFalse(testList.containsSub(sub4));
    }

    @Test
    void testSearchForSubFirstElement() throws ParseException {
        testList.addSub("Steam", 150.0, 2, "28-06-2021");
        assertEquals(3, testList.searchForIndex("Steam"));
    }

    @Test
    void testGetSubStringExists() {
        String returnString = testList.getSubString(1);
        assertEquals("NatGeo" + "\t\t  " + "260.0" + "\t\t\t" + "Yearly", returnString);
    }

    @Test
    void testGetSubStringDoesNotExist() {
        String returnString = testList.getSubString(5);
        assertEquals("Subscription does not exist!", returnString);
    }

    @Test
    void testGetSubExists() {
        Subscription returnSub = testList.getSub(1);
        assertEquals(sub2.getName(), returnSub.getName());
    }

    @Test
    void testGetSubDoesNotExist() {
        Subscription returnSub = testList.getSub(5);
        assertEquals(null, returnSub);
    }

    @Test
    void testSearchForSubNotFirstElement() {
        assertEquals(1, testList.searchForIndex("NatGeo"));
    }

    @Test
    void testSearchForSubDoesNotExist() {
        assertEquals(-1, testList.searchForIndex("Hulu"));
    }

    @Test
    void testCancelSubFirstElement() {
        String output = testList.cancelSub("Disney");
        assertEquals("Subscription successfully removed!", output);
        assertEquals(2, testList.size());
    }

    @Test
    void testCancelSubNotFirstElement() {
        String output = testList.cancelSub("NatGeo");
        assertEquals("Subscription successfully removed!", output);
        assertEquals(2, testList.size());
    }

    @Test
    void testCancelSubDoesNotExist() {
        String output = testList.cancelSub("Hulu");
        assertEquals("Error! Subscription does not exist!", output);
        assertEquals(3, testList.size());
    }
}
