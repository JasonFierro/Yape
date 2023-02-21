package seleniumgluecode;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;

public class selectHotelDefinition extends TestBase{


    @And("^Seleccionar hotel \"([^\"]*)\"$")
    public void seleccionarHotel(String texto) throws Exception {
        selectHotelPage.seleccionarHotel(texto);
    }

    @And("^Seleccionar habitaciones \"([^\"]*)\"$")
    public void seleccionarHabitaciones(String texto) throws Exception {
        selectHotelPage.seleccionarHabitacion(texto);
    }

    @When("^Reservar paquete$")
    public void reservarPaquete() throws Exception {
        selectHotelPage.reservar();
    }
}
