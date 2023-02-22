package seleniumgluecode;

//import cucumber.api.PendingException;
//import cucumber.api.java.en.And;
//import cucumber.api.java.en.Given;

import io.cucumber.java.en.*;

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

    @Then("Validar la funcionalidad sort y volver")
    public void validarLaFuncionalidadSortYVolver() throws Exception {
        searchPage.filtroOrdenar();
    }

    @Then("Validar la funcionalidad filter y volver")
    public void validarLaFuncionalidadFilterYVolver() throws Exception {
        searchPage.filtro_filtrar();
    }

    @Then("Validar la funcionalidad map y volver")
    public void validarLaFuncionalidadMapYVolver() throws Exception {
        searchPage.filtroMap();
    }

    @Then("Hacer click en favorito")
    public void hacerClickEnFavorito() throws Exception {
        searchPage.favorito();
    }

    @Then("Hacer click en share validar la informacion y volver")
    public void hacerClickEnShareValidarLaInformacionYVolver() throws Exception {
        searchPage.compartirHotel();
    }

    @Then("Hacer click en mas opciones validar la informacion y volver")
    public void hacerClickEnMasOpcionesValidarLaInformacionYVolver() throws Exception {
        searchPage.masOpciones();
    }
}
