package seleniumgluecode;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

public class searchdefinition extends TestBase {

    @Given("^Ingresar al sitio Booking")
    public void ingresarAlSitioBooking() throws Exception {
        hooks.SetUp();
    }

    @And("^Buscar la ciudad \"([^\"]*)\"$")
    public void buscarLaCiudad(String ciudad) throws Throwable {
        searchPage.buscarCiudad(ciudad);
    }

    @And("^Agregar fechas ida y vuelta$")
    public void agregarFechasIdaYVuelta() throws Exception {
        searchPage.fechasIdaVuelta();
    }

    @And("^Seleccionar habitacion niño de cinco anios$")
    public void seleccionarHabitacionNiñoDeCincoAnios() throws Exception {
        searchPage.seleccionarHabitacion();
    }

}
