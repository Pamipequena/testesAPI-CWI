package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.suites.E2e;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.DeleteBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;

public class DeleteBookingTest extends BaseTest {

    GetBookingRequest getBookingRequest = new GetBookingRequest();
    DeleteBookingRequest deleteBookingRequest = new DeleteBookingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Excluir uma reserva")
    public void deleteReservation() throws Exception{
        int id = getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");
        deleteBookingRequest.deleteReservationToken(id, Utils.validPayloadBooking()).then()
                .statusCode(201)
                .time(lessThan(3L), TimeUnit.SECONDS);

        System.out.println("Reserva de ID "  + id + (", \n Dados:\n ") + Utils.validPayloadBooking() +  " \n excluida com sucesso!");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Tentar excluir um reserva que não existe")
    public void deleteNonExistentReservation() throws Exception{
        deleteBookingRequest.deleteReservationToken(99999, Utils.validPayloadBooking()).then()
                .statusCode(405)
                .time(lessThan(2L), TimeUnit.SECONDS);
        System.out.println("ERRO 405, esta reserva não existe!");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Tentar excluir um reserva sem autorização")
    public void deleteAnUnauthorizedReservation() throws Exception{
        deleteBookingRequest.deleteReservationNoToken(5, Utils.validPayloadBooking()).then()
                .statusCode(403)
                .time(lessThan(6L), TimeUnit.SECONDS);
        System.out.println("ERRO 403, você não possui autorização para excluir esta reserva!");
    }

}
