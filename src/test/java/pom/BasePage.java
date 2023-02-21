package pom;

import io.appium.java_client.AppiumBy;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import seleniumgluecode.Hooks;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BasePage extends Hooks {

//    public WebDriver driver;
//
//
//    public BasePage(WebDriver driver) {
//        this.driver = driver;
//    }
//
//    protected WebElement findElement(By locator) {
//        return driver.findElement(locator);
//    }

    public void guardarDatos(By element) throws Exception {
        ArrayList<String> inter = new ArrayList<>();
        ArrayList<WebElement> p = (ArrayList<WebElement>) driver.findElements(element);
//        for(WebElement ele: p){
        scrollDown(element);
        Thread.sleep(2000);
        for (int i = 0; i < p.size(); i++) {
            String numer = p.get(i).getText();
            inter.add(numer+"\n");
        }
        System.out.println("******************** Lista de datos guardados *********************");
        System.out.println(inter);
        System.out.println("*******************************************************************");
    }

    public void posicionDatoGuardado(By element, String textArray){
        ArrayList<String> inter = new ArrayList<>();
        ArrayList<WebElement> p = (ArrayList<WebElement>) driver.findElements(element);
//        for(WebElement ele: p){
        for (int i = 0; i < p.size(); i++) {
            String numer = p.get(i).getText();
            inter.add(numer);
        }
        System.out.println("Size: "+p.size());
        System.out.println("******************** Posición del dato solicitado *********************");
        System.out.println("Posición :" + inter.indexOf(textArray));
        System.out.println("Existe el campo :" + inter.contains(textArray));
        System.out.println("***********************************************************************");
    }

    public void validarDatosGuardados(By element, int position, String containText) throws Exception {
        ArrayList<String> inter = new ArrayList<>();
        ArrayList<WebElement> p = (ArrayList<WebElement>) driver.findElements(element);
//        for(WebElement ele: p){
        for (int i = 0; i < p.size(); i++) {
            String numer = p.get(i).getText();
            inter.add(numer);
        }
        System.out.println("******************** Validación del dato solicitado *********************");
        if (inter.get(position).contains(containText)) {
            System.out.println("El campo obtenido es: "+ p.get(position).getText());
            System.out.println("El campo esperado es: "+ p.get(position).getText());
            System.out.println("Los datos coinciden para el campo: "+ p.get(position).getText());
        } else {
            System.out.println("Los datos no coinciden para el campo: "+ p.get(position).getText());
            driver.quit();
        }
        System.out.println("***********************************************************************");
        System.out.println("\n");
    }




    /**
     * Hace click sobre el elemento ingresado
     *
     * @param element es el localizador o campo al cual le vamos a relizar la acción
     * @throws Exception
     */
    public void click(By element) throws Exception {
        try {
            driver.findElement(element).click();
        } catch (Exception e) {
            throw new Exception("No se pudo realizar clic sobre el elemento " + element);
        }
    }

    public void listSize(By element) throws Exception {
        try {
            ArrayList<String> elem = new ArrayList<>();
            for (String elemento : elem) {
                System.out.println(elemento);
            }
            elem.add(driver.findElement(element).getText());

        } catch (Exception e) {
            throw new Exception("No se pudo obtener listado de elementos " + element);
        }
    }

    /**
     * Verifica si un determinado elemento está presente y se muestra. Si se muestra el elemento,
     * el valor devuelto es verdadero. De lo contrario, el valor devuelto es falso.
     *
     * @param element es el localizador o campo al cual le vamos a relizar la acción
     * @return
     * @throws Exception
     */
    public boolean isDisplayed(By element) throws Exception {
        try {
            return driver.findElement(element).isDisplayed();

        } catch (Exception e) {
            throw new Exception("No se pudo encontrar el elemento " + element);
        }
    }

    /**
     * Obtiene el texto dentro del elemento seleccionado, para asi poderlo comparar con un texto esperado
     *
     * @param element es el localizador o campo al cual le vamos a relizar la acción
     * @return
     * @throws Exception
     */
    public String getText(By element) throws Exception {
        try {
            System.out.println(driver.findElement(element).getText());
            return driver.findElement(element).getText();
        } catch (Exception e) {
            throw new Exception("No se pudo encontrar el texto " + element);
        }
    }

    /**
     * Obitene el titulo del elemento seleccionado
     *
     * @return
     * @throws Exception
     */
    public String getTittle() throws Exception {
        try {
            return driver.getTitle();
        } catch (Exception e) {
            throw new Exception("No se pudo encontrar el titulo de la pagina ");
        }
    }

    /**
     * Realiza un click en el elemento, despues de eso le da click a la tecla enter para poder ingresar
     *
     * @param inputText es para enviar un texto que se desee ingresar en el campo
     * @param locator   es el localizador o campo al cual le vamos a relizar la acción
     * @throws Exception
     */
    protected void input_Enter(String inputText, By locator) throws Exception {
        try {
            driver.findElement(locator).sendKeys(inputText, Keys.ENTER);
        } catch (Exception e) {
            throw new Exception("No se pudo hacer click en el elemento y dar enter " + locator);
        }
    }

    /**
     * Cuando existen unos campos que se borran pero vuelven a aparecer y desean editarlos, esta acción funciona
     * para eliminar uno a uno de los caracteres que se encuentran, la función de esta acción es moverse una vez hacia
     * la izquierda y luego da click en suprimir con el fin de borrar el movimiento que realizo a la izquierda.
     * Esta acción se puede realizar N° cantidad de veces utilizando el for debido a que existen campos con mas de 10
     * caracteres para eliminar.
     *
     * @param locator es el localizador o campo al cual le vamos a relizar la acción
     * @throws Exception
     */
    protected void delete(By locator) throws Exception {
        for (int i = 0; i < 10; ++i) {
            driver.findElement(locator).sendKeys(Keys.ARROW_LEFT);
            driver.findElement(locator).sendKeys(Keys.DELETE);
        }
    }

    /**
     * Esta acción realiza un refresh o F5, en la pagina que se encuentra actualmente
     *
     * @throws Exception
     */
    protected void update() throws Exception {
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("location.reload()");
        } catch (Exception e) {
            throw new Exception("No se pudo actualizar la pagina");
        }
    }

    /**
     * Esta acción reliza una tabulación en el localizador seleccionado.
     *
     * @param locator es el localizador o campo al cual le vamos a relizar la acción
     * @throws Exception
     */
    protected void tab(By locator) throws Exception {
        try {
            driver.findElement(locator).sendKeys(Keys.TAB);
        } catch (Exception e) {
            throw new Exception("No se pudo tabular en el elemento " + locator);
        }
    }

    /**
     * Esta acción permite enviar un texto en un localizador INPUT el cual pueda ingresar texto libre.
     *
     * @param inputText es para enviar un texto que se desee ingresar en el campo
     * @param locator   es el localizador o campo al cual le vamos a relizar la acción
     * @throws Exception
     */
    protected void input(String inputText, By locator) throws Exception {
        try {
            if (inputText != null) {
                driver.findElement(locator).clear();
                driver.findElement(locator).sendKeys(inputText);
            } else {
                driver.findElement(locator).sendKeys(inputText);
            }
        } catch (Exception e) {
            throw new Exception("No se pudo escribir en el elemento " + locator);
        }
    }

    /**
     * Esta acción permite confirmar si un texto tipo String es igual a otro texto o objeto esperado convertido
     * en String, tienen la misma información
     *
     * @param textExpected se envia un texto o objeto tipo String para ser validado contra el localizador
     * @param locator      es el localizador o campo al cual le vamos a relizar la acción
     */
    protected void textEquals(String textExpected, By locator) throws Exception {
        try {
            String getText = driver.findElement(locator).getText();
            Assert.assertEquals(textExpected, getText);
            System.out.println("El texto esperado es: " + textExpected);
            System.out.println("El texto actual es: " + getText);
        }catch (Exception e){
            String getText = driver.findElement(locator).getText();
            System.out.println("El texto esperado es: " + textExpected);
            System.out.println("El texto actual es: " + getText);
            throw new Exception("No concuerda el mismo texto esperado");
        }

    }

    /**
     * Esta acción permite limpiar el campo seleccionado por el localizador
     *
     * @param locator es el localizador o campo al cual le vamos a relizar la acción
     */
    protected void clear(By locator) {
        driver.findElement(locator).clear();
    }

    /**
     * Esta acción permite localizar un objeto en dado caso cuando la automatización no lo logre vizualizar, esta función
     * muestra el localizador en la pantalla y puede seguir realizando las acciones futuras.
     * va a hacer el conteo de las paginas y interactuamos con la ultima que abrimos.
     *
     * @param locator es el localizador o campo al cual le vamos a relizar la acción
     */
    protected void locateObject(By locator) {
        try {
            int cont = 0;
            int tamano = 0;
            Set<String> handles = driver.getWindowHandles();
            tamano = handles.toArray().length;
            //System.out.println("la cantidad de pestañas es :"+tamaño);
            if (tamano > 1) {
                //System.out.println("la cantidad de pestañas son :" + tamaño);
                for (String actual : handles) {
                    cont++;
                    // System.out.println("corriendo pestaña : " + cont + " su id pestaña es :" + actual);
                    if (cont == tamano) {
                        driver.switchTo().window(actual);
                        //  System.out.println("entro a la pestaña con id  :" + actual);
                    }
                }
            }
            WebElement element = driver.findElement(locator);
            Actions actions = new Actions(driver);
            actions.moveToElement(element);
            actions.perform();
        } catch (Exception e) {
            System.out.print("No se encontro el objeto: " + locator);
            driver.quit();
        }
    }

    /**
     * Esta acción permite bajar la barra espaciadora hasta el elemento seleccionado
     *
     * @param locator es el localizador o campo al cual le vamos a relizar la acción
     */
    protected void scrollDown(By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollBy(0,1000);", element);

    }

    protected void scrollDownMobile(String text) throws Exception {
        try {
            driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""+text+"\"))"));
        }catch (Exception e){
            throw new Exception("No se realizo el scroll al texto, o no se encontro el texto");
        }
    }

    protected void scrollDownMobileFinish(String text) throws Exception {
        try {
            driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""+text+"\"))"));
        }catch (Exception e){
            throw new Exception("No se realizo el scroll al texto, o no se encontro el texto");
        }
    }

    protected void scrollDownManual(int pixeles) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollBy(0," + pixeles + ");");

    }

    /**
     * Esta acción permite subir la barra espaciadora hasta el elemento seleccionado
     *
     * @param locator es el localizador o campo al cual le vamos a relizar la acción
     */
    protected void scrollUp(By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollBy(0,-1000)", element);
    }

    protected void scrollUpManual() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollBy(0,-500)");
    }

    /**
     * Esta acción permite abrir una nueva ventana e ingresar una URL nueva dentro de nuestra pagina en blanco
     * con el fin de seguir nuestras pruebas automatizadas
     *
     * @param url se debe enviar en un tipo String la nueva URL a la cual se requiere ingresar y seguir con la automatización
     */

    protected void openNewTab(String url) {
        String mainTab = driver.getWindowHandle();
        String newTab = "";

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.open()");
        Set<String> handles = driver.getWindowHandles();
        for (String actual : handles) {
            if (!actual.equalsIgnoreCase(mainTab)) {
                driver.switchTo().window(actual);
                newTab = actual;
            }
        }
        visit(url);
    }

    /**
     * Esta acción funciona para ingresar dentro de los IFRAME del HTML, debido a que existen proyectos con validación
     * "iframe", con la automatización normal no funcionaría ya que existen campos para selecionar dentro del iframe,
     * con esta acción podemos obtener los elementos y acceder a sus respectivas funciones para seguir con la automatización
     */
    protected void validateIframe() {
        String mainTab = driver.getWindowHandle();
        String newTab = "";

        /**
         * Utilizar en dado caso que habran muchas pestañas, dependiendo de la pestaña que tienen el iframe se cambia en la linea
         * @Params driver.switchTo().window(tabs2.get ( 3)), en el numero 3 se coloca el numero de la pestaña como se corresponde,
         * cabe mencionar que esto se maneja con arrays en dado caso la pestaña inicial es la numero 0.

        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(3));
        WebElement iFrame = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iFrame);
         */

        ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
        for (String actual : tab) {
            if (!actual.equalsIgnoreCase(actual) || actual.equalsIgnoreCase(mainTab)) {
                WebElement iFrame = driver.findElement(By.tagName("iframe"));
                driver.switchTo().frame(iFrame);
            }
        }
    }

    /**
     * Esta acción permite bajar la barra espaciadora hasta el elemento seleccionado dentro del iframe
     *
     * @param locator es el localizador o campo al cual le vamos a relizar la acción
     */
    protected void scrollDownIframe(By locator) {
        String mainTab = driver.getWindowHandle();
        WebElement element = driver.findElement(locator);
        ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
        for (String actual : tab) {
            if (!actual.equalsIgnoreCase(actual) || actual.equalsIgnoreCase(mainTab)) {
                WebElement iFrame = driver.findElement(By.tagName("iframe"));
                driver.switchTo().frame(iFrame);
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("window.scrollBy(0,1000);", element);
            }
        }
    }

    /**
     * Esta acción aplica solo cuando deseamos salir de los elementos dentro del "iframe", ya que existen tambien objetos
     * por fuera de esta etiqueta "iframe", cuando estamos dentro del iframe no podemos obtener elementos que estan
     * afuera del iframe, por ese motivo la acción "validateBody" podemos salir del iframe actual y obtener los
     * objetos correspondientes para seguir con la automatización
     */
    protected void validateBody() {
        String mainTab = driver.getWindowHandle();
        String newTab = "";

        ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
        for (String actual : tab) {
            if (!actual.equalsIgnoreCase(actual) || actual.equalsIgnoreCase(mainTab)) {
                driver.switchTo().defaultContent();
            }
        }
    }

    /**
     * Esta acción permite visitar una URL ingresada como tipo String
     *
     * @param url se debe enviar en un tipo String la nueva URL a la cual se requiere ingresar y seguir con la automatización
     */
    protected void visit(String url) {
        driver.get(url);
    }

    /**
     * Esta acción permite realizar una espera a nuestros localizadores mientras termina de cargar la pagina, si el localizador
     * no es encontrado entre el N° de segundos establecidos, finalizara la prueba indicando que no encontro el localizador
     *
     * @param locator es el localizador o campo al cual le vamos a relizar la acción
     */
    protected void waitImplicit(By locator) {
        try {
            WebDriverWait myWaitVar = new WebDriverWait(driver, Duration.ofSeconds(20));
//            WebDriverWait myWaitVar = new WebDriverWait(driver, 20);
            myWaitVar.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            System.out.print("No se encontro el localizador: " + locator);
            driver.quit();
        }
    }

    /**
     * Esta acción permite seleccionar de una lista desplegable el texto solicitado
     *
     * @param texto   se debe enviar en tipo String el texto que se solicita de la lista desplegable
     * @param locator es el localizador o campo al cual le vamos a relizar la acción
     */
    protected void selectElement(String texto, By locator) {
        try {
            Select select = new Select(driver.findElement(locator));
            select.selectByVisibleText(texto);
        } catch (Exception e) {
            System.out.print("No se selecciono el localizador: " + locator);
            driver.quit();
        }
    }

    /**
     * Esta acción permite posicionar el mouse sobre el menu, seguido se utiliza la acción click para poder ingresar
     *
     * @param locator es el localizador o campo al cual le vamos a relizar la acción
     */
    protected void mouseHoverMenu(By locator) {
        try {
            Actions actions = new Actions(driver);
            WebElement menuOption = driver.findElement(locator);
            actions.moveToElement(menuOption).perform();
            System.out.println("Movio el Mouse al localizador: " + locator);
        } catch (Exception e) {
            System.out.print("No se encontro el localizador: " + locator);
            driver.quit();
        }
    }

    /**
     * Esta acción permite posicionar el mouse sobre el submenu, seguido se utiliza la acción click para poder ingresar
     *
     * @param locator es el localizador o campo al cual le vamos a relizar la acción
     */
    protected void mouseHoverSubMenu(By locator) {
        try {
            Actions actions = new Actions(driver);
            WebElement menuOption = driver.findElement(locator);
            actions.moveToElement(menuOption).click().perform();
            System.out.println("Movio el Mouse al localizador: " + locator);
        } catch (Exception e) {
            System.out.print("No se encontro el localizador: " + locator);
            driver.quit();
        }
    }

    /**
     * Esta acción permite realizar una captura de pantalla completa para tenerla como evidencia de las pruebas automatizadas
     * esta función permite guardar las imagenes dentro de una carpeta establecida por el usuario.
     *
     * @param nombre se debe enviar el nombre tipo String de la captura de imagen, este quedara en un formato .png
     * @throws Exception
     */
    protected void takeScreenShot(String nombre) throws Exception {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String imageFileDir = System.getProperty("user.dir") + "\\imagenes\\test";
        if (imageFileDir == null)
            imageFileDir = System.getProperty("user.dir") + "\\imagenes\\test";
        FileUtils.copyFile(scrFile, new File(imageFileDir, nombre + ".png"));
    }


}
