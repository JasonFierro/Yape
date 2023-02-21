package seleniumgluecode;

import org.openqa.selenium.WebDriver;
import pom.*;
public class TestBase {

    protected WebDriver driver = Hooks.getDriver();
    protected Hooks hooks = new Hooks();
    protected searchPage searchPage = new searchPage();
    protected selectHotelPage selectHotelPage = new selectHotelPage();
    protected infoPage infoPage = new infoPage();

}