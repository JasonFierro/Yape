package seleniumgluecode;

import cucumber.api.java.After;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Hooks {

    public static AndroidDriver driver;
    private static int numberOfCase = 0;

//    @Before
    public void SetUp() throws Exception {
    numberOfCase++;
    System.out.println("Se está ejecutando el escenario #" + numberOfCase);
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    System.out.println("Fecha y hora de ejecución: "+dtf.format(LocalDateTime.now()));

    try{
        DesiredCapabilities cap = new DesiredCapabilities();
        File applicationParentDirectoryPath = new File("src/test");
        File app = new File(applicationParentDirectoryPath, "booking-com-32-9.apk");

        cap.setCapability(MobileCapabilityType.DEVICE_NAME,"Pixel 2");
        cap.setCapability(MobileCapabilityType.UDID,"emulator-5554");
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME,"ANDROID");
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION,"11");
//        cap.setCapability(MobileCapabilityType.DEVICE_NAME,"Xiaomi");
//        cap.setCapability(MobileCapabilityType.UDID,"d8222bbb");
//        cap.setCapability(MobileCapabilityType.PLATFORM_NAME,"ANDROID");
//        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION,"12");
        cap.setCapability(MobileCapabilityType.APP,app.getAbsolutePath());
        cap.setCapability("noReset",false);
        cap.setCapability("appPackage","com.booking");
        cap.setCapability("appActivity","com.booking.startup.HomeActivity");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
    }catch (Exception e){
        e.getMessage();
        Assert.fail("No entró a capa");
    }
    Thread.sleep(5000);
        System.out.println("******************************************************");
        System.out.println("*********** Abrio la aplicación Booking **************");
        System.out.println("******************************************************");
    }

//    @After
    public void TearDown() {
        driver.quit();
    }

    public static WebDriver getDriver(){
        return driver;
    }
}

