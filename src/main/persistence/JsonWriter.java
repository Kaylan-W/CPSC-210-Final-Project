package persistence;

import model.ListOfSubscriptions;
import org.json.JSONArray;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// This class references code from the repo "JsonSerializationDemo"
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a writer that outputs
// // JSON representation of workroom to file
public class JsonWriter {
    private static final int TAB = 10;
    private PrintWriter output;
    private String position;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.position = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens a writer or throws FileNotFoundException if file cannot be opened for writing
    public void open() throws FileNotFoundException {
        output = new PrintWriter(new File(position));
    }

    // MODIFIES: this
    // EFFECTS: outputs the JSON representation of a list of subscriptions to file
    public void write(ListOfSubscriptions los) {
        JSONArray json = los.losToJson();
        save(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        output.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void save(String json) {
        output.print(json);
    }
}