package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ListOfSubscriptionTest {


    @Test
    void testAddSub() {
        ListOfSubscriptions test = new ListOfSubscriptions();
        test.addSub("Disney", 50.0, 2);
        test.addSub("NatGeo", 260.0, 3);
        Subscription sub1 = new Subscription("Disney", 50.0, 2);
        Subscription sub2 = new Subscription("NatGeo", 260.0, 3);
        assertTrue(test.containsSub(sub1));
        assertTrue(test.containsSub(sub2));
    }

    /*
    @Test
    void testGetSub() {
        return null;
    }

    @Test
    void testContainsSub() {
        return null;
    }

    @Test
    void testSearchForSub() {
        return null;
    }

    @Test
    void testCancelSub() {
        return null;
    }

     */
}
