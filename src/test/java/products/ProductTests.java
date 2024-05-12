package products;

import org.json.JSONObject;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.RestUtils;
import utils.TestLogger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import static org.testng.Assert.assertEquals;

public class ProductTests {

    private JSONObject jsonObject;

    public ProductTests() {
        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get("src/test/resources/dev/dummyData.json")));
            jsonObject = new JSONObject(jsonContent);
        } catch (Exception e) {
            TestLogger.logError("Failed to read JSON file", e);
        }
    }

    @Test
    public void createProduct() {
        TestLogger.logInfo("Starting createProduct test");
        String endPoint = jsonObject.getString("createProduct");
        String payload = "{\"title\":\"BMW\"}";
        Response response = RestUtils.performPost(endPoint, payload, new HashMap<>());
        assertEquals(response.getStatusCode(), 200);
        JSONObject responseBody = new JSONObject(response.getBody().asString());
        String title = responseBody.getString("title");
        assertEquals(title, "BMW", "Title is not BMW");
        TestLogger.logInfo("createProduct test completed successfully");
    }

    @Test
    public void getProduct() {
        TestLogger.logInfo("Starting getProduct test");
        String endPoint = jsonObject.getString("readProductDetails");
        Response response = RestUtils.performGet(endPoint, new HashMap<>());
        assertEquals(response.getStatusCode(), 200);
        JSONObject responseBody = new JSONObject(response.getBody().asString());
        String title = responseBody.getString("title");
        assertEquals(title, "iPhone 9", "Title is not iPhone 9");
        int price = responseBody.getInt("price");
        assertEquals(price, 549, "Price is not 549 euro");
        double discountPercentage = responseBody.getDouble("discountPercentage");
        assertEquals(discountPercentage, 12.96, "Discount is not 12.96");
        TestLogger.logInfo("getProduct test completed successfully");
    }
}
