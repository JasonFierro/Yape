package pom;

//import cucumber.api.java.en.Then;
import io.appium.java_client.AppiumBy;
import jdk.nashorn.internal.runtime.Context;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class infoPage extends BasePage {

    By input_address = By.xpath("//*[@resource-id='com.booking:id/bstage1_contact_address_value']//*[@resource-id='com.booking:id/bui_input_container_content']");
    By input_zipCode = By.xpath("//*[@resource-id='com.booking:id/bstage1_contact_zipcode_value']//*[@resource-id='com.booking:id/bui_input_container_content']");
    By input_city = By.xpath("//*[@resource-id='com.booking:id/bstage1_contact_city_value']//*[@resource-id='com.booking:id/bui_input_container_content']");
    By input_telephone = By.xpath("//*[@resource-id='com.booking:id/bstage1_contact_telephone_value']//*[@resource-id='com.booking:id/bui_input_container_content']");
    By check_box_save = By.id("com.booking:id/promo_item_checkbox");
    By circle_leisure = By.id("com.booking:id/business_purpose_leisure");
    By label_price = By.id("com.booking:id/title");
    By button_add_details = By.id("com.booking:id/action_button");
    By label_priceSubTotal = By.xpath("//*[@resource-id='com.booking:id/informative_cta_view_price_container']//*[@resource-id='com.booking:id/title']");
    By input_card = By.id("com.booking:id/new_credit_card_number_edit");
    By input_expiration_card = By.id("com.booking:id/new_credit_card_expiry_date_edit");
    By label_priceTotal = By.id("com.booking:id/title");
    By error_expiry_date = By.xpath("//*[@resource-id='com.booking:id/new_credit_card_expiry_date_input_layout']//*[@resource-id='com.booking:id/textinput_error']");
    By error_card = By.xpath("//*[@resource-id='com.booking:id/new_credit_card_number_input_layout']//*[@resource-id='com.booking:id/textinput_error']");

    public String getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(String priceTotal) {
        this.priceTotal = priceTotal;
    }

    String priceTotal;

    public void ingresarInformacion(String texto, String direccion, String zipCode, String infoCiudad, String infoTelefono) throws Exception {
        waitImplicit(input_address);
        click(input_address);
        Thread.sleep(1000);
        input(direccion, input_address);
        Thread.sleep(2000);
        scrollDownMobile("City *");
        input(zipCode, input_zipCode);
        Thread.sleep(3000);
        input(infoCiudad, input_city);
        click(input_city);
        Thread.sleep(2000);
        scrollDownMobile("US$2,468");
        Thread.sleep(2000);
        input(infoTelefono, input_telephone);
        Thread.sleep(2000);
        click(check_box_save);
        Thread.sleep(2000);
        click(circle_leisure);
        Thread.sleep(2000);
        textEquals(texto, label_price);
        priceTotal = getText(label_price);
        click(button_add_details);
    }

    public void confirmarDatosCompra(String texto) throws Exception {
        Thread.sleep(2000);
        scrollDownMobile(texto);
        Thread.sleep(2000);
        textEquals(getPriceTotal(), label_priceSubTotal);
        Thread.sleep(2000);
        click(button_add_details);
        Thread.sleep(2000);
    }

    public void finalizarBooking(String texto, String tc, String te) throws Exception {

        waitImplicit(input_card);
        input(tc, input_card);
        click(input_expiration_card);
        Thread.sleep(2000);
        input(te, input_expiration_card);
        Thread.sleep(2000);
        textEquals(getPriceTotal(), label_priceTotal);
        Thread.sleep(2000);
    }

    public void finalizarBookingValidacionErrores(String texto, String tc, String te, String mensajeErrorTarjeta, String mensajeErrorTCExpiracion) throws Exception {
        waitImplicit(input_card);
        input(tc, input_card);
        click(input_expiration_card);
        driver.findElement(input_expiration_card).sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        click(input_card);
        Thread.sleep(1000);
        click(input_expiration_card);
        if (isDisplayed(error_card) && getText(error_card).equals(mensajeErrorTarjeta)) {
            textEquals(mensajeErrorTarjeta, error_card);
        } else {
            Thread.sleep(2000);
            textEquals(getPriceTotal(), label_priceTotal);
            Thread.sleep(2000);
        }
        Thread.sleep(2000);
        click(input_expiration_card);
        input(te, input_expiration_card);
        click(input_card);
        Thread.sleep(2000);
//        textEquals(getPriceTotal(), label_priceTotal);
        Thread.sleep(2000);
    }
}
