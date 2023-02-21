package RestApi;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ResponseBodyExtractionOptions;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;


public class auth {

    String authCredential;
    String GetBookingIds;
    String GetBooking;
    String CreateBooking;
    String updateBooking;
    String partialUpdateBooking;
    String deleteBooking;
    String ping;

    public String getBookingid() {
        return bookingid;
    }

    public void setBookingid(String bookingid) {
        this.bookingid = bookingid;
    }

    private String bookingid;
    Properties loadProperty = new Properties();
    String token;
    ValidatableResponse ResponsePing;
    Response idGetBooking;
    Response ResponseGetBooking;
    String bodyAuth = "{\n" +
            "    \"username\" : \"admin\",\n" +
            "    \"password\" : \"password123\"\n" +
            "}";
    String bodyCreate = "{\n" +
            "    \"firstname\" : \"Jason\",\n" +
            "    \"lastname\" : \"Fierro\",\n" +
            "    \"totalprice\" : 2905,\n" +
            "    \"depositpaid\" : true,\n" +
            "    \"bookingdates\" : {\n" +
            "        \"checkin\" : \"2023-02-16\",\n" +
            "        \"checkout\" : \"2023-02-17\"\n" +
            "    },\n" +
            "    \"additionalneeds\" : \"Breakfast\"\n" +
            "}";
    String bodyUpdate = "{\n" +
            "    \"firstname\" : \"Jason\",\n" +
            "    \"lastname\" : \"Fierroooo\",\n" +
            "    \"totalprice\" : 2905,\n" +
            "    \"depositpaid\" : true,\n" +
            "    \"bookingdates\" : {\n" +
            "        \"checkin\" : \"2023-02-16\",\n" +
            "        \"checkout\" : \"2023-02-17\"\n" +
            "    },\n" +
            "    \"additionalneeds\" : \"Breakfast\"\n" +
            "}";

    String bodyPartialUpdate = "{\n" +
            "    \"firstname\" : \"Jason\",\n" +
            "    \"lastname\" : \"Fierro\"\n" +
            "    \"lastname\" : \"Fierroooo\",\n" +
            "    \"totalprice\" : 2905\n" +
            "}";

    @Test
    public void consumirCreateBooking() throws IOException {
        loadProperty.load(new FileReader("./params.properties"));
        CreateBooking = loadProperty.getProperty("CreateBooking");
        GetBooking = loadProperty.getProperty("GetBooking");
        int statusCreateBooking = given().header("Accept", "application/json")
                .and().header("Content-Type", "application/json")
                .body(bodyCreate).when().post(CreateBooking).statusCode();
        System.out.println(statusCreateBooking);
        bookingid = given().header("Content-Type", "application/json")
                .and().header("Accept", "application/json")
                .param("firstname", "Jason")
                .and().param("lastname", "Fierro")
                .and().param("totalprice", is(equalTo(2905)))
                .when().get(CreateBooking).then().log().all().extract().path("bookingid").toString().replace("[", "").replace("]", "");
        System.out.println("setBookingid: ");
        setBookingid(bookingid);
        System.out.println(bookingid);
        consumirGetBooking();
        System.out.println("********************************************");
        System.out.println("************* Creo el objeto ***************");
        System.out.println("********************************************");
    }

    @Test(priority = 1)
    public void consumirUpdateBooking() throws IOException {
        loadProperty.load(new FileReader("./params.properties"));
        updateBooking = loadProperty.getProperty("UpdateBooking");
        System.out.println("Book ID: " + getBookingid());
//        consumirCreateBooking();
        int statusUpdateBooking = given().header("Accept", "application/json")
                .and().header("Content-Type", "application/json").and().header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .pathParam("id", getBookingid()).body(bodyUpdate).when().put(updateBooking).statusCode();
        System.out.println(statusUpdateBooking);
        ResponseGetBooking = given().header("Accept", "application/json").pathParam("id", bookingid)
                .when().get(GetBooking).then().log().all().body("firstname", containsString("Jason"))
                .and().body("totalprice", is(equalTo(2905))).extract().response();
        System.out.println("*************************************************");
        System.out.println("************* Actualiza el objeto ***************");
        System.out.println("*************************************************");
    }

    @Test(priority = 2)
    public void consumirPartialUpdateBooking() throws IOException {
        loadProperty.load(new FileReader("./params.properties"));
        partialUpdateBooking = loadProperty.getProperty("PartialUpdateBooking");
//        consumirCreateBooking();
        int statusUpdateBooking = given().header("Accept", "application/json")
                .and().header("Content-Type", "application/json").and().header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .pathParam("id", getBookingid()).body(bodyPartialUpdate).when().patch(partialUpdateBooking).statusCode();
        System.out.println(statusUpdateBooking);
        consumirGetBooking();
        System.out.println("*************************************************");
        System.out.println("********* Actualiza Parcial el objeto ***********");
        System.out.println("*************************************************");
    }

    @Test(priority = 3)
    public void consumirDeleteBooking() throws IOException {
        loadProperty.load(new FileReader("./params.properties"));
        deleteBooking = loadProperty.getProperty("DeleteBooking");
        System.out.println("Book ID: " + bookingid);
//        consumirCreateBooking();
        ValidatableResponse statusUpdateBooking = given().header("Accept", "application/json")
                .and().header("Content-Type", "application/json").and().header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .pathParam("id", getBookingid()).when().delete(deleteBooking).then().log().ifValidationFails().statusCode(201);
        System.out.println(statusUpdateBooking);
        System.out.println("*************************************************");
        System.out.println("************** Elimina el objeto ****************");
        System.out.println("*************************************************");
    }

    @Test
    public void HealthCheck() throws IOException {
        loadProperty.load(new FileReader("./params.properties"));
        ping = loadProperty.getProperty("HealthCheck");
        ValidatableResponse statusPing = given().when().get(ping).then().statusCode(201).body(containsString("Created"));
        System.out.println(statusPing);
        ResponsePing = given().when().get(ping).then().log().ifValidationFails().statusCode(201);
        System.out.println(ResponsePing);
        System.out.println("********************************************");
        System.out.println("************* Creo el Ping *****************");
        System.out.println("********************************************");
    }

    @Test
    public void consumirAuth() throws IOException {
        loadProperty.load(new FileReader("./params.properties"));
        authCredential = loadProperty.getProperty("CreateToken");
        int statusCodeAuth = given().header("Content-Type", "application/json").body(bodyAuth).when().post(authCredential).statusCode();
        System.out.println(statusCodeAuth);
        token = given().header("Content-Type", "application/json").body(bodyAuth).when().post(authCredential).then().log().body().extract().body().path("token");
        System.out.println(token);
    }

        @Test
    public void consumirGetBookingIds() throws IOException {
        loadProperty.load(new FileReader("./params.properties"));
        GetBookingIds = loadProperty.getProperty("GetBookingIds");
        int statusGetBookingIds = given().header("Content-Type", "application/json").when().get(GetBookingIds).statusCode();
        System.out.println(statusGetBookingIds);
        idGetBooking = given().header("Content-Type", "application/json").param("firstname", "Jason")
                .and().param("lastname", "Fierro")
                .when().get(GetBookingIds).then().log().all().extract().response();
        System.out.println(idGetBooking);
    }

        @Test
    public void consumirGetBooking() throws IOException {
        loadProperty.load(new FileReader("./params.properties"));
        GetBooking = loadProperty.getProperty("GetBooking");
        int statusGetBooking = given().header("Accept", "application/json").pathParam("id", bookingid.replace("[", "")).when().get(GetBooking).statusCode();
        System.out.println(statusGetBooking);
        ResponseGetBooking = given().header("Accept", "application/json").pathParam("id", bookingid.replace("[", ""))
                .when().get(GetBooking).then().log().all().body("firstname", containsString("Jason"))
                .and().body("totalprice", is(equalTo(2905))).extract().response();
        System.out.println(GetBooking);
        System.out.println("*************************************************");
        System.out.println("******* Consume GetBooking del objeto ***********");
        System.out.println("*************************************************");
    }


}
