package products;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.json.JSONObject;
import io.restassured.response.Response;
import org.testng.Assert;
import utils.RestUtils;
import utils.TestLogger; // Import the TestLogger class

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class ProductTests {

    private JSONObject jsonObject;
    private Response response;
    private JSONObject responseBody;

    @Before
    public void loadProductData() {
        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get("src/test/resources/dev/dummyData.json")));
            jsonObject = new JSONObject(jsonContent);
        } catch (IOException e) {
            TestLogger.logError("Failed to read JSON file", e); // Log the error
            throw new RuntimeException("Failed to read JSON file", e);
        }
    }

    @When("I make a POST request to create the product")
    public void createProduct() {
        String baseURI = jsonObject.getString("baseURI");
        String endPoint = baseURI + jsonObject.getString("createProduct");
        response = RestUtils.performPost(endPoint, "{\"title\":\"BMW\"}", new HashMap<>());
        responseBody = new JSONObject(response.getBody().asString());
    }

    @When("I make a GET request to retrieve the product details")
    public void getProductDetails() {
        String baseURI = jsonObject.getString("baseURI");
        String endPoint = baseURI + jsonObject.getString("readProductDetails");
        response = RestUtils.performGet(endPoint, new HashMap<>());
        responseBody = new JSONObject(response.getBody().asString());
    }

    @Then("the response status code should be {int}")
    public void verifyStatusCode(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(actualStatusCode, expectedStatusCode, "Response status code is not as expected");
    }

    @Then("the product title should be {string}")
    public void verifyProductTitle(String expectedTitle) {
        String actualTitle = responseBody.getString("title");
        Assert.assertEquals(actualTitle, expectedTitle, "Product title is not as expected");
    }

    @Then("the product price should be {int}")
    public void verifyProductPrice(int expectedPrice) {
        int actualPrice = responseBody.getInt("price");
        Assert.assertEquals(actualPrice, expectedPrice, "Product price is not as expected");
    }

    @Then("the product discount percentage should be {double}")
    public void verifyDiscountPercentage(double expectedDiscount) {
        double actualDiscount = responseBody.getDouble("discountPercentage");
        Assert.assertEquals(actualDiscount, expectedDiscount, "Product discount percentage is not as expected");
    }

    @When("I make a PUT request to update the product title to {string}")
    public void updateProductTitle(String newTitle) {
        String baseURI = jsonObject.getString("baseURI");
        String endPoint = baseURI + jsonObject.getString("updateProductTitle");
        String payload = "{\"title\":\"" + newTitle + "\"}";
        response = RestUtils.performPut(endPoint, payload, new HashMap<>());
        responseBody = new JSONObject(response.getBody().asString());
    }

    @When("I make a DELETE request to delete the product")
    public void deleteProduct() {
        String baseURI = jsonObject.getString("baseURI");
        String endPoint = baseURI + jsonObject.getString("deleteProduct");
        response = RestUtils.performDelete(endPoint, new HashMap<>());
        responseBody = new JSONObject(response.getBody().asString());
    }

    @Then("the product is deleted")
    public void productIsDeleted() {
        deleteProduct();
        boolean isDeleted = responseBody.getBoolean("isDeleted");
        Assert.assertTrue(isDeleted, "Product should be deleted");
    }
}
