package products;
import org.json.JSONObject;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.RestUtils;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import static org.testng.Assert.assertEquals;

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
        String endPoint = jsonObject.getString("createProduct");

        // Payload for the POST request
        String payload = "{" +
                " \"title\":\"BMW\"\n" +
                "}";

        // Perform POST request
        Response response = RestUtils.performPost(endPoint, payload, new HashMap<>());

        // Assert response status code
        assertEquals(response.getStatusCode(), 200);

        // Parse response body to JSON object
        JSONObject responseBody = new JSONObject(response.getBody().asString());

        // Retrieve value associated with "title" key
        String title = responseBody.getString("title");

        // Assert that the title is "BMW"
        assertEquals(title, "BMW", "Title is BWM");

    }
    @Test
    public void getProduct() {
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
        String endPoint = jsonObject.getString("readProductDetails");

        // Perform POST request
        Response response = RestUtils.performGet(endPoint, new HashMap<>());

        // Assert response status code
        assertEquals(response.getStatusCode(), 200);

        // Parse response body to JSON object
        JSONObject responseBody = new JSONObject(response.getBody().asString());

        // Retrieve value associated with "title" key
        String title = responseBody.getString("title");
        Integer price = responseBody.getInt("price");
        double discountPercentage = responseBody.getDouble("discountPercentage");

        // Assert that the title is "BMW"
        assertEquals(title, "iPhone 9", "Title is iPhone 9");
        assertEquals(price, 549, "Price is 549 euro");
        assertEquals(discountPercentage, 12.96, "Discount is 12.96");
    }
}
