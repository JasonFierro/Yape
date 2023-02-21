package seleniumgluecode;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

import java.io.IOException;

public class crearBookDefinition extends TestBase{

    @Given("^Ejecutar el servicio CreateBooking y validar el codigo estado \"([^\"]*)\"$")
    public void ejecutarElServicioCreateBookingYValidarElCodigoEstado(String statusCode) throws Throwable {
        auth.consumirCreateBooking(statusCode);
    }
}
