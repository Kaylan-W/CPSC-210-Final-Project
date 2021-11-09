package persistence;

import model.ListOfSubscriptions;
import model.Subscription;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {
    @Test
    void testWriterNoFileExceptionThrown() {
        try {
            ListOfSubscriptions los = new ListOfSubscriptions();
            JsonWriter write = new JsonWriter(".data/nonExistentFile.json");
            write.open();
            fail("IOException was not thrown");
        } catch (IOException i) {
            // do nothing, test passes
        }
    }

    @Test
    void testWriterNoFileExceptionNotThrown() {
        try {
            ListOfSubscriptions los = new ListOfSubscriptions();
            JsonWriter write = new JsonWriter(".data/testWriterEmptyList.json");
            write.open();
            fail("IOException was not thrown");
        } catch (IOException i) {
            // do nothing, test passes
        }
    }

    @Test
    void testWriterEmptyList() {
        try {
            ListOfSubscriptions los = new ListOfSubscriptions();
            JsonWriter write = new JsonWriter("./data/testWriterEmptyList.json");
            write.open();
            write.write(los);
            write.close();
        } catch (IOException i) {
            fail("Exception thrown when not expected to!!");
        }
    }

    @Test
    void testWriterEmptyListExceptionTrown() {
        try {
            ListOfSubscriptions los = new ListOfSubscriptions();
            JsonWriter write = new JsonWriter("./data\testWriterEmptyList.json");
            write.open();
            write.write(los);
            write.close();
        } catch (IOException i) {
            fail("Exception thrown when not expected to!!");
        }
    }

    @Test
    void testWriterPopulatedList() {
        try {
            ListOfSubscriptions los = new ListOfSubscriptions();
            Subscription sub1 = new Subscription("Disney", 50.0, 2, "2021-01-10");
            Subscription sub2 = new Subscription("NatGeo", 260.0, 3, "2021-02-12");
            Subscription sub3 = new Subscription("Netflix", 2.00, 1, "2021-09-18");
            los.addSub(sub1);
            los.addSub(sub2);
            los.addSub(sub3);
            JsonWriter write = new JsonWriter("./data/testWriterPopulatedList");
            write.open();
            write.write(los);
            write.close();
            JsonReader scan = new JsonReader("./data/testWriterPopulatedList");
            los = scan.readList();
            assertEquals(3, los.size());
            assertTrue(los.containsSub(sub1));
            assertTrue(los.containsSub(sub2));
            assertTrue(los.containsSub(sub3));
        } catch (IOException i) {
            fail("Exception thrown when not expected to!!");
        }
    }

    @Test
    void testWriterPopulatedListExceptionThrown() {
        try {
            ListOfSubscriptions los = new ListOfSubscriptions();
            Subscription sub1 = new Subscription("Disney", 50.0, 2, "2021-01-10");
            Subscription sub2 = new Subscription("NatGeo", 260.0, 3, "2021-02-12");
            Subscription sub3 = new Subscription("Netflix", 2.00, 1, "2021-09-18");
            los.addSub(sub1);
            los.addSub(sub2);
            los.addSub(sub3);
            JsonWriter write = new JsonWriter("./data/ff\ftestWriterPopulatedList");
            write.open();
            write.write(los);
            write.close();
            JsonReader scan = new JsonReader("./data/testWriterPopulatedList");
            los = scan.readList();
            assertEquals(3, los.size());
            assertTrue(los.containsSub(sub1));
            assertTrue(los.containsSub(sub2));
            assertTrue(los.containsSub(sub3));
        } catch (IOException i) {
            fail("Exception thrown when not expected to!!");
        }
    }
}
