package products;
import org.json.JSONObject;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.RestUtils;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class ProductTests {
    @Test
    public void createProduct() {
        // Read JSON file into a string
        String jsonContent = null;
        try {
            jsonContent = new String(Files.readAllBytes(Paths.get("src/test/resources/dev/dummyData.json")));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to read JSON file.");
        }

        // Parse JSON string to JSONObject
        JSONObject jsonObject = new JSONObject(jsonContent);

        // Get endpoint from JSON
        String endPoint = jsonObject.getString("createDummyDataEndpoint");

        // Payload for the POST request
        String payload = "{" +
                " \"title\":\"BMW\"\n" +
                "}";

        // Perform POST request
        Response response = RestUtils.performPost(endPoint, payload, new HashMap<>());

        // Assert response status code
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
