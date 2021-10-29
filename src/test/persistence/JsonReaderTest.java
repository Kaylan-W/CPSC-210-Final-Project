package persistence;

import model.ListOfSubscriptions;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends ConvertTest {

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


    @Test
    void testReaderPopulatedList() {
        JsonReader scan = new JsonReader("./data/testReaderPopulatedList.json");
        try {
            ListOfSubscriptions los = scan.readList();
            assertEquals(3, los.size());
            checkConversion("Disney", 50, 2, los.getSub(0));
            checkConversion("NatGeo", 260, 3, los.getSub(1));
            checkConversion("Netflix", 2, 1, los.getSub(2));
        } catch (IOException e) {
            fail("Unable to read from file!!");
        }
    }
}
