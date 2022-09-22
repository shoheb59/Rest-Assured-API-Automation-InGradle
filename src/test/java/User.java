import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.junit.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class User {


    Properties properties = new Properties();
    FileInputStream fileInputStream;
    {
        try {
            fileInputStream = new FileInputStream("./src/test/resources/config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void loginApi() throws ConfigurationException, IOException {

        properties.load(fileInputStream);
        RestAssured.baseURI = properties.getProperty("baseUrl");

        Response response =
                given().contentType("application/json")
                        .body(
                                "{\n" +
                                        "    \"email\":\"salman@grr.la\",\n" +
                                        "    \"password\":\"1234\"\n" +
                                        "}"
                        ).
                        when()
                        .post("/user/login")
                        .then()
                        .assertThat().statusCode(200).extract().response();

        JsonPath jsonPath = response.jsonPath();
        String token = jsonPath.get("token");
        System.out.println(token);
        MyUtils.setEnvironmentVariable("token",token);










        //JSONObject requestParams = new JSONObject();
    }
    public void GetApi() throws IOException {
        properties.load(fileInputStream);
        RestAssured.baseURI = properties.getProperty("baseUrl");

        Response response = given()
                .contentType("application/json")
                .header("Authorization",properties.getProperty("token"))
                .when()
                .get("/user/list")
                .then()
                .assertThat()
                .statusCode(200).extract().response();

        JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath.get("users[1]").toString());
        Assert.assertEquals(jsonPath.get("users[1].id").toString(),"47");
        //System.out.println(jsonPath.get("users[1].id").toString()); //[if you want to show only id- or other element just input it after the dot]
        //System.out.println(response.asPrettyString());


    }



}
