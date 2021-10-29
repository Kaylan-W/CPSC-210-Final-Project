package persistence;

import model.ListOfSubscriptions;
import model.Subscription;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// This class references code from the repo "JsonSerializationDemo"
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a reader that converts JSON data in a file to a list of subscription
public class JsonReader {
    private String input;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String info) {
        this.input = info;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }
        return contentBuilder.toString();
    }

    // EFFECTS: Returns the list of subscriptions read from file
    public ListOfSubscriptions readList() throws IOException {
        String line = readFile(input);
        JSONObject item = new JSONObject(line);
        return parseLOS(item);
    }

    // EFFECTS: Returns JSON object from file as a list of subscriptions
    private ListOfSubscriptions parseLOS(JSONObject item) {
        ListOfSubscriptions los = new ListOfSubscriptions();
        addParsedList(los, item);
        return los;
    }

    // MODIFIES: los
    // EFFECTS: adds all parsed subscriptions to the list of subscription
    private void addParsedList(ListOfSubscriptions los, JSONObject item) {
        JSONArray tempArray = item.getJSONArray("list");
        for (Object json : tempArray) {
            JSONObject nextSub = (JSONObject) json;
            addParsedSub(los, nextSub);
        }
    }

    // MODIFIES: los
    // EFFECTS: adds a single parsed subscription to the list of subscription
    private void addParsedSub(ListOfSubscriptions los, JSONObject nextSub) {
        String name = nextSub.getString("service");
        double amount = nextSub.getDouble("cost");
        int periodType = nextSub.getInt("period");
        String purchaseDate = nextSub.getString("purchase");
        Subscription s = new Subscription(name, amount, periodType, purchaseDate);
        los.addSub(s);
        //los.addSub(name, amount, periodType, purchaseDate);
    }
}


