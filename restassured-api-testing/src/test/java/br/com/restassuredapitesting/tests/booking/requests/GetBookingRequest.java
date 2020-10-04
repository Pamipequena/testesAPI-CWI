package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetBookingRequest {

    @Step("Buscar todas as reservas")
    public Response allBookings(){
        return given()
                .header( "Content-Type", "application/json")
                .when()
                .get("booking");
    }

    @Step("Buscar uma reserva especifica")
    public Response specificBooking(int id){
        return given()
                .header( "Content-Type", "application/json")
                .when()
                .get("booking/" + id) ;
    }

    @Step("Validar lista por filtro")
    public Response filtered(String filter){
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .get("booking" + filterNormalizer(filter));
    }

    private String filterNormalizer(String filter) {
        if (filter.startsWith("?")) {
            return filter;
        } else {
            return   "?" + filter;
        }
    }

}
