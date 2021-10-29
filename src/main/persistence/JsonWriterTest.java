package persistence;

import model.ListOfSubscriptions;
import model.Subscription;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {
    @Test
    void testWriterNoFile() {
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
    void testWriterPopulatedList() {
        try {
            ListOfSubscriptions los = new ListOfSubscriptions();
            Subscription sub1 = new Subscription("Disney", 50.0, 2, "10-01-2021");
            Subscription sub2 = new Subscription("NatGeo", 260.0, 3, "12-02-2021");
            Subscription sub3 = new Subscription("Netflix", 2.00, 1, "18-09-2021");
            los.addSub(sub1);
            los.addSub(sub2);
            los.addSub(sub3);
            JsonWriter write = new JsonWriter("./data/testWriterPopulatedList");
            write.open();
            write.write(los);
            write.close();
//
//            JsonReader scan = new JsonReader("./data/testWriterPopulatedList");
//            los = scan.readList();
//            assertEquals(3, los.size());
//            assertTrue(los.containsSub(sub1));
//            assertTrue(los.containsSub(sub2));
//            assertTrue(los.containsSub(sub3));
        } catch (IOException i) {
            fail("Exception thrown when not expected to!!");
        }
    }
}
