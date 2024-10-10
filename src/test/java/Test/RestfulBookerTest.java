package Test;

import Pojo.CreateBooking;
import Pojo.GetBookingID;
import Pojo.TokenCeredentials;
import Pojo.BookingDates;
import Pojo.PartialUpdate;
import RestUtil.RestUtil;
import SpecBuilder.Payload;
import com.aventstack.extentreports.ExtentTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.nullValue;

import PropertyReader.*;
//import JsonBuilderClass.*;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RestfulBookerTest {
   private String user;
    private String pass;
    public  String tokenGlobal;

    public int BookingID;
    String baseURIAuth;
    readProperty readPropertyObj= new readProperty();

    TokenCeredentials tokenObj= new TokenCeredentials();

    CreateBooking createBookingObj= new CreateBooking();

    BookingDates dateObj= new BookingDates();


    private final String sheetName = "Payload";

    private final String testName = "RestfulBooker";
    //RestfulBookerSpecBuilder restfulBookerObj= new RestfulBookerSpecBuilder();

    public  static final Logger logger = LogManager.getLogger(RestfulBookerTest.class);
  @BeforeMethod
public void setup() throws ParseException {
        baseURIAuth= readPropertyObj.readPropertyFile("baseURI");
        RestAssured.baseURI=baseURIAuth;
      //setupAuthentication();
}

    @Test(groups = {"Regression" }, priority = 0,description = "Test Case is used to Authenticate")
    public void setupAuthentication() throws NullPointerException{
      logger.info("Inside Authentication API");
        TokenCeredentials objTokenPayload= Payload.AuthorizationPayload();
        Response response= RestUtil.postRequest("/auth",objTokenPayload);
        tokenGlobal= response.jsonPath().get("token");
       // RestUtil.printResponseLoginReport(response);
    }


    @Test(groups = {"Regression" }, priority = 1,description = "Test Case is used to create Booking")
    public void createBooking() throws ParseException {
        logger.info("Inside Create Booking API");
        CreateBooking createNewBooking= Payload.createBookingPayload();
        Response responseCreateBooking=   RestUtil.postRequest("booking",createNewBooking);
        BookingID= responseCreateBooking.jsonPath().get("bookingid");
        //RestUtil.printResponseLoginReport(responseCreateBooking);

    }


    @Test(groups = {"Regression" }, priority = 2,description = "Test Case is used fetch all bookings made so far")
public void GetAllBookingID() {
        RestUtil.getRequest("booking");
        //RestUtil.printResponseLoginReport(RestUtil.getRequest("booking"));
    }



    @Test(groups = {"Regression" }, priority = 3,description = "Test Case is used to fetch Booking ID By filtering with Name")
    public void GetBookingIDByName(){
      Map<String, String> queryParamNames= Payload.returnFirstNameLastName();
        RestUtil.getRequestQueryParam("booking",queryParamNames);
        //RestUtil.printResponseLoginReport(RestUtil.getRequestQueryParam("booking",queryParamNames));

    }

    @Test(groups = {"Regression" }, priority = 4,description = "Test Case is used to fetch Booking id by check in check out date")
    public void GetBookingIDByCheckInCheckOUTDate(){
        Map<String, String> queryParamDates= Payload.returnCheckInCheckOut();
        RestUtil.getRequestQueryParam("booking",queryParamDates);
        //RestUtil.printResponseLoginReport(RestUtil.getRequestQueryParam("booking",queryParamDates));

    }


    @Test(groups = {"Regression" }, priority = 5,description = "Test Case is used to fetch Booking detail By ID")
    //Get Booking By filtering with ID
    public void GetBookingDetailFilterByID(){
        RestUtil.getRequest("booking"+"/"+BookingID);
        //RestUtil.printResponseLoginReport(RestUtil.getRequest("booking"+"/"+BookingID));
  }

    @Test(groups = {"Regression" }, priority = 6,description = "Test Case is used to update booking based on input ID")
    public void updateBooking(){
      CreateBooking updateBookingObj= Payload.updateBookingPayload();
        RestUtil.putRequest("booking"+"/"+BookingID,tokenGlobal,updateBookingObj);
        //RestUtil.printResponseLoginReport(RestUtil.putRequest("booking"+"/"+BookingID,tokenGlobal,updateBookingObj));
    }

    @Test(groups = {"Regression" }, priority = 7,description = "Test Case is used to partially update booking based on input ID")
    public void partialUpdateBooking(){
        PartialUpdate partialUpdateObj= Payload.partialUpdatePayload();
        //RestUtil.printResponseLoginReport(RestUtil.patchRequest("booking" + "/" + BookingID,tokenGlobal,partialUpdateObj));
        RestUtil.patchRequest("booking" + "/" + BookingID,tokenGlobal,partialUpdateObj);

    }

    @Test(groups = {"Regression" }, priority = 8,description = "Test Case is used to delete booking based on input ID")
    public void deleteBooking(){
        RestUtil.deleteRequest("booking"+"/"+BookingID,tokenGlobal);
        //RestUtil.printResponseLoginReport(RestUtil.deleteRequest("booking"+"/"+BookingID,tokenGlobal));


    }
}
