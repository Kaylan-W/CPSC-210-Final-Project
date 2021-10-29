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
//    void testWriterInvalidFile() {
//        try {
//            WorkRoom wr = new WorkRoom("My work room");
//            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
//            writer.open();
//            fail("IOException was expected");
//        } catch (IOException e) {
//            // pass
//        }
//    }


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
//    @Test
//    void testWriterEmptyWorkroom() {
//        try {
//            WorkRoom wr = new WorkRoom("My work room");
//            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
//            writer.open();
//            writer.write(wr);
//            writer.close();
//
//            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
//            wr = reader.read();
//            assertEquals("My work room", wr.getName());
//            assertEquals(0, wr.numThingies());
//        } catch (IOException e) {
//            fail("Exception should not have been thrown");
//        }
//    }

    @Test
    void testWriterPopulatedList() {
        try {
            ListOfSubscriptions los = new ListOfSubscriptions();
            Subscription sub1 = new Subscription("Disney", 50.0, 2, "10-01-2021");
            Subscription sub2 = new Subscription("NatGeo", 260.0, 3, "12-02-2021");
            Subscription sub3 = new Subscription("Symbolab", 2.00, 1, "18-09-2021");
            los.addSub(sub1);
            los.addSub(sub2);
            los.addSub(sub3);
            JsonWriter write = new JsonWriter("./data/testWriterPopulatedList");
            write.open();
            write.write(los);

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
//    @Test
//    void testWriterGeneralWorkroom() {
//        try {
//            WorkRoom wr = new WorkRoom("My work room");
//            wr.addThingy(new Thingy("saw", Category.METALWORK));
//            wr.addThingy(new Thingy("needle", Category.STITCHING));
//            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
//            writer.open();
//            writer.write(wr);
//            writer.close();
//
//            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
//            wr = reader.read();
//            assertEquals("My work room", wr.getName());
//            List<Thingy> thingies = wr.getThingies();
//            assertEquals(2, thingies.size());
//            checkThingy("saw", Category.METALWORK, thingies.get(0));
//            checkThingy("needle", Category.STITCHING, thingies.get(1));
//
//        } catch (IOException e) {
//            fail("Exception should not have been thrown");
//        }
//    }
}
