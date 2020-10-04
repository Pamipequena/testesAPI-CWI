package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.suites.Contract;
import br.com.restassuredapitesting.suites.E2e;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

@Feature("Reservas")
public class GetBookingTest extends BaseTest {

    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs das Reservas")
    public void validateBookingIds() throws Exception{
        getBookingRequest.allBookings().then()
                .statusCode(200)
                .time(lessThan(8L), TimeUnit.SECONDS)
                .body("size()",greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar uma Reserva especifica")
    public void validateSpecificBookings() throws Exception{
        int idBookings = getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");

        getBookingRequest.specificBooking(idBookings).then()
                .statusCode(200)
                .time(lessThan(8L), TimeUnit.SECONDS);
        System.out.println("Reserva de ID: " + idBookings);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro firstname")
    public void bookingByFirstName() throws Exception{

        getBookingRequest.filtered("?firstname=Jim").then()
                .statusCode(200)
                .time(lessThan(6L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro lastname")
    public void bookingByLastName() throws Exception{

        getBookingRequest.filtered("?lastname=Brown").then()
                .statusCode(200)
                .time(lessThan(6L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro checkin")
    public void bookingByCheckIn() throws Exception{

        getBookingRequest.filtered("?checkin=2018-01-01").then()
                .statusCode(200)
                .time(lessThan(6L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro checkOut")
    public void bookingByCheckOut() throws Exception{

        getBookingRequest.filtered("?chekout=2019-01-01").then()
                .statusCode(200)
                .time(lessThan(5L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar os IDs de reservas utilizando o filtro checkIn and checkOut")
    public void bookingByCheckInCheckOut() throws Exception{

        getBookingRequest.filtered("?chekin=2018-01-01?chekout=2019-01-01").then()
                .statusCode(200)
                .time(lessThan(5L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro name, checkin and checkout date")
    public void bookingByNameCheckInCheckOut() throws Exception{

        getBookingRequest.filtered("?fistname=Ronaldo?lastname=Fenomeno?checkin=2018-01-01?checkout=2019-01-01").then()
                .statusCode(200)
                .time(lessThan(6L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(Contract.class)
    @DisplayName("Garantir o contrato do retorno da lista de reservas")
    public void contractBookings() throws Exception{
            getBookingRequest.allBookings().then()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchema(
                        new File
                                (Utils.getContractsBasePath("booking", "bookings"))));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(Contract.class)
    @DisplayName("Garantir o contrato do retorno de uma reserva especifica")
    public void contractBooking() throws Exception{
        int idBooking = getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");

        getBookingRequest.specificBooking(idBooking).then()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchema(
                        new File
                                (Utils.getContractsBasePath("booking", "booking"))));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Visualizar erro de servidor 500 quando enviar filtro mal formatado")
    public void invalidFilter() throws Exception{
        getBookingRequest.filtered("?checkin=01924879").then()
                .statusCode(500)
                .time(lessThan(6L), TimeUnit.SECONDS);
        System.out.println("ERRO 500");
    }

}
