package seleniumgluecode;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

import java.io.IOException;

public class apisBookDefinition extends TestBase{

    @Given("^Ejecutar el servicio CreateBooking y validar el codigo estado \"([^\"]*)\"$")
    public void ejecutarElServicioCreateBookingYValidarElCodigoEstado(String statusCode) throws Throwable {
        auth.consumirCreateBooking(statusCode);
    }

    @Given("^Ejecutar el servicio UpdateBooking y validar el codigo estado \"([^\"]*)\"$")
    public void ejecutarElServicioUpdateBookingYValidarElCodigoEstado(String statusCode) throws Throwable {
        auth.consumirUpdateBooking(statusCode);
    }

    @Given("^Ejecutar el servicio PartialUpdateBooking y validar el codigo estado \"([^\"]*)\"$")
    public void ejecutarElServicioPartialUpdateBookingYValidarElCodigoEstado(String statusCode) throws Throwable {
        auth.consumirPartialUpdateBooking(statusCode);
    }

    @Given("^Ejecutar el servicio DeleteBooking y validar el codigo estado \"([^\"]*)\" \"([^\"]*)\"$")
    public void ejecutarElServicioDeleteBookingYValidarElCodigoEstado(String statusCode, String responseBody) throws Throwable {
        auth.consumirDeleteBooking(statusCode,responseBody);
    }

    @Given("^Ejecutar el servicio HealthCheck y validar el codigo estado \"([^\"]*)\" \"([^\"]*)\"$")
    public void ejecutarElServicioHealthCheckYValidarElCodigoEstado(String statusCode, String responseBody) throws Throwable {
//        auth.HealthCheck(statusCode,responseBody);
    }

    @Given("^Ejecutar el servicio Auth y validar el codigo estado \"([^\"]*)\"$")
    public void ejecutarElServicioAuthYValidarElCodigoEstado(String statusCode) throws Throwable {
//        auth.consumirAuth(statusCode);
    }
}
