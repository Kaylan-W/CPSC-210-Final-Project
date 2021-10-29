package persistence;

import model.ListOfSubscriptions;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    void testReaderNoFile() {
        JsonReader scan = new JsonReader("./data/nonExistentFile.json");
        try {
            ListOfSubscriptions los = scan.readList();
            fail("IOException was not thrown");
        } catch (IOException e) {
            // do nothing, test passes
        }
    }
//    @Test
//    void testReaderNonExistentFile() {
//        JsonReader reader = new JsonReader("./data/noSuchFile.json");
//        try {
//            WorkRoom wr = reader.read();
//            fail("IOException expected");
//        } catch (IOException e) {
//            // pass
//        }
//    }

    @Test
    void testReaderEmptyList() {
        JsonReader scan = new JsonReader("./data/testReaderEmptyList.json");
        try {
            ListOfSubscriptions los = scan.readList();
            assertEquals(0, los.size());
        } catch (IOException e) {
            fail("Unable to read from file!!");
        }
    }
//    @Test
//    void testReaderEmptyWorkRoom() {
//        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
//        try {
//            WorkRoom wr = reader.read();
//            assertEquals("My work room", wr.getName());
//            assertEquals(0, wr.numThingies());
//        } catch (IOException e) {
//            fail("Couldn't read from file");
//        }
//    }


    @Test
    void testReaderPopulatedList() {
        JsonReader scan = new JsonReader("./data/testReaderPopulatedList.json");
        try {
            ListOfSubscriptions los = scan.readList();
            assertEquals(3, los.size());
            assertEquals("Netflix", (los.getSub(0)).getName());
            assertEquals("Disney+", (los.getSub(1)).getName());
            assertEquals("Amazon", (los.getSub(2)).getName());
        } catch (IOException e) {
            fail("Unable to read from file!!");
        }
    }
//    @Test
//    void testReaderGeneralWorkRoom() {
//        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
//        try {
//            WorkRoom wr = reader.read();
//            assertEquals("My work room", wr.getName());
//            List<Thingy> thingies = wr.getThingies();
//            assertEquals(2, thingies.size());
//            checkThingy("needle", Category.STITCHING, thingies.get(0));
//            checkThingy("saw", Category.WOODWORK, thingies.get(1));
//        } catch (IOException e) {
//            fail("Couldn't read from file");
//        }
//    }
}
