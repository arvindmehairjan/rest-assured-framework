# REST Assured Project Setup with Maven in IntelliJ

This README provides instructions for setting up a REST Assured project using Maven in IntelliJ IDEA.

## Prerequisites

Make sure you have the following installed on your system:

- Java Development Kit (JDK) 8 or higher
- Maven
- IntelliJ IDEA

## Getting Started

Follow these steps to set up the project in IntelliJ IDEA:

1. **Clone the Repository:**
   ```
   git clone <repository_url>
   ```

2. **Open IntelliJ IDEA:**
   Launch IntelliJ IDEA.

3. **Import Project:**
   - Click on `File` > `Open`.
   - Navigate to the directory where you cloned the repository and select the `pom.xml` file.
   - Click `Open`.

4. **Enable Auto-Import (Optional):**
   If prompted, enable auto-import for Maven projects. IntelliJ IDEA will automatically download the required dependencies.

## Project Structure

The project structure should look like this:

```
project-root
│   pom.xml
│
└───src
    └───test
        └───java
            │   TestClass.java
```

## Writing Test Cases

You can now start writing your REST Assured test cases in the `TestClass.java` file under the `src/test/java` directory.

```java
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestClass {

    @Test
    public void exampleTest() {
        Response response = RestAssured.get("https://api.example.com/resource");
        assertEquals(200, response.getStatusCode());
        // Add more assertions as needed
    }
}
```

## Running Tests

To run your tests, right-click on the test class or individual test method in IntelliJ IDEA and select `Run 'TestClass'` or `Run 'exampleTest()'`.

## Additional Configuration

- **Logging:**
  To enable logging in REST Assured, you can add the following code to your test class:
  ```java
  import io.restassured.RestAssured;
  import io.restassured.config.LogConfig;
  import org.junit.jupiter.api.BeforeAll;
  
  public class TestClass {
  
      @BeforeAll
      public static void setup() {
          RestAssured.config = RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails());
      }
  }
  ```

- **Base URI:**
  If you have a common base URI for your API endpoints, you can set it as follows:
  ```java
  import io.restassured.RestAssured;
  import org.junit.jupiter.api.BeforeAll;
  
  public class TestClass {
  
      @BeforeAll
      public static void setup() {
          RestAssured.baseURI = "https://api.example.com";
      }
  }
  ```
