package pom;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public class selectHotelPage extends BasePage{

    public String getValue_payment() {
        return value_payment;
    }
    public void setValue_payment(String value_payment) {
        this.value_payment = value_payment;
    }
    String value_payment;
    By label_payment = By.xpath("//*[@resource-id='com.booking:id/price_view_price' and contains(@text,'US$2,468')]");
    By label_subtotal_payment = By.id("com.booking:id/recommended_block_subtotal_price_view");
    By button_select_rooms = By.id("com.booking:id/select_room_cta");
    By button_reserve = By.id("com.booking:id/recommended_block_reserve_button");



    public void seleccionarHotel(String texto) throws Exception {
        Thread.sleep(2000);
        scrollDownMobile(texto);
        Thread.sleep(2000);
        value_payment = getText(label_payment);
        setValue_payment(value_payment);
        Thread.sleep(2000);
        click(label_payment);
        System.out.println("Prueba:***** "+getValue_payment());
    }

    public void seleccionarHabitacion(String texto) throws Exception {
        Thread.sleep(2000);
        scrollDownMobile(texto);
        textEquals(getValue_payment(),label_payment);
        click(button_select_rooms);
        Thread.sleep(2000);
    }

    public void reservar() throws Exception {
        Thread.sleep(2000);
        textEquals(getValue_payment(),label_subtotal_payment);
        click(button_reserve);
        Thread.sleep(3000);
    }


}
