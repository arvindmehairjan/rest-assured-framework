package products;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTests {
    @Test
    public  void createProduct(){
        Response response = RestAssured.given().log().all()
                .baseUri("https://dummyjson.com/products/add")
                .contentType(ContentType.JSON)
                .body("{" +
                        "   \"title\":\"BMW \"\n" +
                        "}")
                .post()
                .then().log().all().extract().response();
        Assert.assertEquals(response.statusCode(), 200);
    }
}
