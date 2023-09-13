package STE.service;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

class LocalizationServiceTest {


    @Test
    void localizationApiWorksCorrectly(){
        //givenWhenThen
        given().get("http://api.openweathermap.org/geo/1.0/direct?q=London&limit=5&appid=0698b586077ea70d12342dafe84f3958")
                .then()
                .statusCode(200)
                .body("name[0]",equalTo("London"));
    }


}