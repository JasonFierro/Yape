package seleniumgluecode;

//import cucumber.api.PendingException;
//import cucumber.api.java.en.And;
//import cucumber.api.java.en.Given;
//import cucumber.api.java.en.Then;

import io.cucumber.java.en.*;

public class infoDefinition extends TestBase{

    @And("^Ingresar informacion \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void ingresarInformacion(String texto, String direccion, String zipCode, String infoCiudad, String infoTelefono) throws Throwable {
        infoPage.ingresarInformacion(texto,direccion, zipCode, infoCiudad, infoTelefono);
        infoPage.confirmarDatosCompra(texto);
    }

    @Then("^Ingresar tarjeta de credito \"([^\"]*)\" fecha de expiracion y validar errores \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void ingresarTarjetaDeCreditoFechaDeExpiracionYValidarErrores(String tc, String te, String texto, String mensajeErrorTarjeta, String mensajeErrorTCExpiracion) throws Throwable {
        infoPage.finalizarBookingValidacionErrores(texto,tc,te,mensajeErrorTarjeta,mensajeErrorTCExpiracion);
    }

    @Then("^Ingresar tarjeta de credito \"([^\"]*)\" fecha de expiracion \"([^\"]*)\" \"([^\"]*)\"$")
    public void ingresarTarjetaDeCreditoFechaDeExpiracion(String tc, String te, String texto) throws Throwable {
        infoPage.finalizarBooking(texto,tc,te);
    }
}
