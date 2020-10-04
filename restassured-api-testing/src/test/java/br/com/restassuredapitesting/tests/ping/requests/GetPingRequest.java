package br.com.restassuredapitesting.tests.ping.requests;

import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class GetPingRequest extends BaseTest {


    @Step("Verifica se API está online")
    public Response ping(){
        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("ping")
                .prettyPeek();
    }

}

