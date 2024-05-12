package products;

import org.json.JSONObject;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.RestUtils;
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
            e.printStackTrace();
        }
    }

    @Test
    public void createProduct() {
        String endPoint = jsonObject.getString("createProduct");
        String payload = "{\"title\":\"BMW\"}";
        Response response = RestUtils.performPost(endPoint, payload, new HashMap<>());
        assertEquals(response.getStatusCode(), 200);
        JSONObject responseBody = new JSONObject(response.getBody().asString());
        assertEquals(responseBody.getString("title"), "BMW", "Title is not BMW");
    }

    @Test
    public void getProduct() {
        String endPoint = jsonObject.getString("readProductDetails");
        Response response = RestUtils.performGet(endPoint, new HashMap<>());
        assertEquals(response.getStatusCode(), 200);
        JSONObject responseBody = new JSONObject(response.getBody().asString());
        assertEquals(responseBody.getString("title"), "iPhone 9", "Title is not iPhone 9");
        assertEquals(responseBody.getInt("price"), 549, "Price is not 549 euro");
        assertEquals(responseBody.getDouble("discountPercentage"), 12.96, "Discount is not 12.96");
    }
}
