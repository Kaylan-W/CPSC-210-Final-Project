package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ListOfSubscriptionTest {
    private ListOfSubscriptions testList;
    Subscription sub1 = new Subscription("Disney", 50.0, 2, "2021-01-10");
    Subscription sub2 = new Subscription("NatGeo", 260.0, 3, "2021-02-12");
    Subscription sub3 = new Subscription("Symbolab", 2.00, 1, "2021-09-18");
    Subscription sub4 = new Subscription("Hulu", 10.00, 2, "2021-11-21");


    @BeforeEach
    public void runBefore() {
        testList = new ListOfSubscriptions();
        testList.addSub(sub1);
        testList.addSub(sub2);
        testList.addSub(sub3);
    }

    @Test
    void testAddSub() {
        testList.addSub("Hulu", 10.00, 2, "2021-11-21");
        assertEquals(4, testList.size());
        assertTrue(testList.containsSub(sub1));
        assertTrue(testList.containsSub(sub2));
        assertTrue(testList.containsSub(sub3));
        assertTrue(testList.containsSub(sub4));
    }

    @Test
    void testAddSubBySub()  {
        ListOfSubscriptions los = new ListOfSubscriptions();
        los.addSub(sub1);
        los.addSub(sub2);
        assertEquals(2, los.size());
        assertTrue(los.containsSub(sub1));
        assertTrue(los.containsSub(sub2));
    }

    @Test
    void testContainsSubFalse() {
        assertFalse(testList.containsSub(sub4));
    }

    @Test
    void testSearchForSubFirstElement() {
        testList.addSub("Steam", 150.0, 2, "2021-06-28");
        assertEquals(3, testList.searchForIndex("Steam"));
    }

    @Test
    void testGetSubStringExists() {
        String returnString = testList.getSubString(1);
        assertEquals("NatGeo" + "\t\t" + "260.0" + "\t\t\t" + "Yearly" + "\t\t\t2021-02-12", returnString);
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
        assertNull(returnSub);
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
