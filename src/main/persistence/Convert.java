package persistence;

import org.json.JSONArray;
import org.json.JSONObject;

// EFFECTS: Converts the current object to JSON format
public interface Convert {
    JSONArray jsonConvertor();
}
