package persistence;

import org.json.JSONObject;

// EFFECTS: Converts the current object to JSON format
public interface Convert {
    JSONObject jsonConvertor();
}
