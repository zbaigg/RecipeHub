//Source: This class is adapted from the Writable interface in the JsonSerializationDemo file.

package persistence;

import org.json.JSONObject;

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
