package pom;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class searchPage extends BasePage {

    AndroidDriver androidDriver;

    By label_message = By.id("com.booking:id/message");
    By label_message_ok = By.id("com.booking:id/button_positive");
    By input_close_signIn = By.xpath("//*[@content-desc='Navigate up']");
    By button_start_searching = By.xpath("//*[@text='Start searching']");
    By input_search_city = By.xpath("//*[@resource-id='com.booking:id/facet_search_box_accommodation_destination']");
    By input_search_destination = By.xpath("//*[@resource-id='com.booking:id/facet_with_bui_free_search_booking_header_toolbar_content']");
    By label_city_cusco = By.xpath("//*[@text='City in Cusco, Peru']");
    By input_search_date = By.xpath("//*[@resource-id='com.booking:id/facet_search_box_accommodation_dates']");
    By label_day_going = By.xpath("//*[@content-desc='23 February 2023']");
    By label_day_lap = By.xpath("//*[@content-desc='28 February 2023']");
    By button_selected_dates = By.xpath("//*[@resource-id='com.booking:id/facet_date_picker_confirm']");
    By input_search_persons = By.xpath("//*[@resource-id='com.booking:id/facet_search_box_accommodation_occupancy']");
    By button_plus_children = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.view.ViewGroup[3]/android.widget.LinearLayout/android.widget.Button[2]");
    By label_age_child = By.xpath("(//android.widget.Button)[2]");
    By label_fiveYeardOld_child = By.xpath("//*[@text='5 years old']");
    By label_text_fiveYeardOld_child = By.xpath("//*[@text='5 years old']");
    By label_ok = By.xpath("//*[@text='OK']");
    By button_apply = By.xpath("//*[@resource-id='com.booking:id/group_config_apply_button']");
    By button_search = By.xpath("//*[@resource-id='com.booking:id/facet_search_box_cta']");

    public void buscarCiudad(String ciudad) throws Exception {
        try {
            isDisplayed(input_close_signIn);
            click(input_close_signIn);
            waitImplicit(button_start_searching);
            click(button_start_searching);
            waitImplicit(input_search_city);
            if (ciudad.isEmpty()) {
                click(button_search);
                Thread.sleep(2000);
                textEquals("Please enter your destination", label_message);
                click(label_message_ok);
            } else {
                click(input_search_city);
                Thread.sleep(2000);
                input(ciudad, input_search_destination);
                Thread.sleep(2000);
                click(label_city_cusco);
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            waitImplicit(input_search_city);
            click(input_search_city);
            Thread.sleep(2000);
            input(ciudad, input_search_destination);
            Thread.sleep(2000);
            click(label_city_cusco);
            Thread.sleep(2000);
        }
    }

    public void fechasIdaVuelta() throws Exception {
        try {
            Thread.sleep(2000);
            click(label_day_going);
            Thread.sleep(2000);
            click(label_day_lap);
            Thread.sleep(2000);
            click(button_selected_dates);
            Thread.sleep(2000);
        } catch (Exception e) {
            isDisplayed(input_search_date);
            click(input_search_date);
            Thread.sleep(2000);
            click(label_day_going);
            Thread.sleep(2000);
            click(label_day_lap);
            Thread.sleep(2000);
            click(button_selected_dates);
            Thread.sleep(2000);
        }

    }

    public void seleccionarHabitacion() throws Exception {
        click(input_search_persons);
        Thread.sleep(2000);
        click(button_plus_children);
        try {
            for (int i = 0; i < 6; i++) {
                click(label_age_child);
            }
            isDisplayed(label_fiveYeardOld_child);
            Thread.sleep(2000);
            click(label_fiveYeardOld_child);
            click(label_ok);
            Thread.sleep(2000);
            textEquals(getText(label_fiveYeardOld_child), label_text_fiveYeardOld_child);
            click(button_apply);
            Thread.sleep(2000);
            click(button_search);
            Thread.sleep(2000);
        } catch (Exception e) {
            throw new Exception("No se selecciono niño de 5 años");
        }
    }
}
