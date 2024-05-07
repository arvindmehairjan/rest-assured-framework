package products;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.RestUtils;

import java.util.HashMap;

public class ProductTests {
    @Test
    public void createProduct() {
        String endPoint = "https://dummyjson.com/products/add";
        String payload = "{" +
                " \"title\":\"BMW\"\n" + // Removed the unnecessary square bracket
                "}";
        Response response = RestUtils.performPost(endPoint, payload, new HashMap<>()); // Removed parentheses around parameters
        Assert.assertEquals(response.statusCode(), 200);
    }
}
