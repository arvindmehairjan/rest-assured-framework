package products;

import org.json.JSONObject;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.RestUtils;
import utils.TestLogger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import static org.testng.Assert.assertEquals;

public class ProductTests {

    private JSONObject jsonObject;

    @BeforeClass
    public void setUp() {
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
        Response response = RestUtils.performPost(endPoint, "{\"title\":\"BMW\"}", new HashMap<>());
        assertResponseStatus(response, 200);
        assertTitle(response, "BMW");
        TestLogger.logInfo("createProduct test completed successfully");
    }

    @Test
    public void getProduct() {
        TestLogger.logInfo("Starting getProduct test");
        String endPoint = jsonObject.getString("readProductDetails");
        Response response = RestUtils.performGet(endPoint, new HashMap<>());
        assertResponseStatus(response, 200);
        assertTitle(response, "iPhone 9");
        assertPrice(response, 549);
        assertDiscountPercentage(response, 12.96);
        TestLogger.logInfo("getProduct test completed successfully");
    }

    private void assertResponseStatus(Response response, int expectedStatusCode) {
        assertEquals(response.getStatusCode(), expectedStatusCode);
    }

    private void assertTitle(Response response, String expectedTitle) {
        JSONObject responseBody = new JSONObject(response.getBody().asString());
        assertEquals(responseBody.getString("title"), expectedTitle, "Title is not as expected");
    }

    private void assertPrice(Response response, int expectedPrice) {
        JSONObject responseBody = new JSONObject(response.getBody().asString());
        assertEquals(responseBody.getInt("price"), expectedPrice, "Price is not as expected");
    }

    private void assertDiscountPercentage(Response response, double expectedDiscount) {
        JSONObject responseBody = new JSONObject(response.getBody().asString());
        assertEquals(responseBody.getDouble("discountPercentage"), expectedDiscount, "Discount is not as expected");
    }
}
