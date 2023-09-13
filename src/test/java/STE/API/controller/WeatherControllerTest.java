package STE.API.controller;


import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


class WeatherControllerTest {


    @Test
    void getWeatherWorksCorrectly() {
        //givenWhenThen
        given().get("https://api.openweathermap.org/data/2.5/weather?lat=51.5156177&lon=-0.0919983&appid=0698b586077ea70d12342dafe84f3958&units=metric")
                .then()
                .statusCode(200)
                .body("sys.country", equalTo("GB"));

    }


}