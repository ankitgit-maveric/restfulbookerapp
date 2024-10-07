package SpecBuilder;

import Pojo.BookingDates;
import Pojo.CreateBooking;
import Pojo.PartialUpdate;
import Pojo.TokenCeredentials;
import PropertyReader.readProperty;

import java.util.HashMap;
import java.util.Map;

import static Utils.ConfigExcelIO.reader;

public class Payload {
    private static final String sheetName = "Payload";



    static HashMap<String, String> testData = new HashMap<String, String>();
    static BookingDates dateObj= new BookingDates();

    static readProperty readPropertyObj= new readProperty();
    public static CreateBooking createBookingPayload(){

        testData = reader.getRowTestData(sheetName, "RestfulBooker");
        CreateBooking payloadObj= new CreateBooking();
        payloadObj.setFirstname(testData.get("firstname"));
        payloadObj.setLastname(testData.get("lastname"));
        try {
            payloadObj.setTotalprice(Integer.parseInt(testData.get("totalprice")));
        }catch(NumberFormatException e){

        }
        payloadObj.setDepositpaid(Boolean.parseBoolean(testData.get("depositpaid")));
        dateObj.setCheckin(testData.get("checkin"));
        dateObj.setCheckout(testData.get("checkout"));
        payloadObj.setBookingdates(dateObj);
        payloadObj.setAdditionalneeds(testData.get("additionalneeds"));

        return payloadObj;
    }

    public static CreateBooking updateBookingPayload(){
        testData = reader.getRowTestData(sheetName, "RestfulBookerUpdateBooking");
        CreateBooking updateBookingPayloadObj= new CreateBooking();
        updateBookingPayloadObj.setFirstname(testData.get("firstname"));
        updateBookingPayloadObj.setLastname(testData.get("lastname"));
        try {
            updateBookingPayloadObj.setTotalprice(Integer.parseInt(testData.get("totalprice")));
        }catch(NumberFormatException e){

        }
        updateBookingPayloadObj.setDepositpaid(Boolean.parseBoolean(testData.get("depositpaid")));
        dateObj.setCheckin(testData.get("checkin"));
        dateObj.setCheckout(testData.get("checkout"));
        updateBookingPayloadObj.setBookingdates(dateObj);
        updateBookingPayloadObj.setAdditionalneeds(testData.get("additionalneeds"));
        return updateBookingPayloadObj;
    }

    public static PartialUpdate partialUpdatePayload(){
        testData = reader.getRowTestData(sheetName, "PartialUpdate");
        PartialUpdate partialUpdatePayloadObj= new PartialUpdate();
        partialUpdatePayloadObj.setFirstname(testData.get("firstname"));
        partialUpdatePayloadObj.setLastname(testData.get("lastname"));
        return partialUpdatePayloadObj;
    }

    public static TokenCeredentials AuthorizationPayload(){
        TokenCeredentials tokenPayloadObj= new TokenCeredentials();
      String  user=readPropertyObj.readPropertyFile("username");
      String  pass= readPropertyObj.readPropertyFile("password");
      tokenPayloadObj.setUsername(user);
      tokenPayloadObj.setPassword(pass);
      return tokenPayloadObj;

    }

    public static Map<String, String> returnFirstNameLastName(){
        Map<String, String> mapName= new HashMap<String, String>();
        testData=reader.getRowTestData(sheetName,"BookingIDByName");
       String firstname= testData.get("firstname");
        String lastname= testData.get("lastname");
        mapName.put("firstname",firstname);
        mapName.put("lastname",lastname);
        return mapName;

    }

    public static Map<String, String> returnCheckInCheckOut(){
        Map<String, String> mapCheckINCheckOut= new HashMap<String, String>();
        testData=reader.getRowTestData(sheetName,"BookingIDByDate");
        String checkinDate= testData.get("checkin");
        String checkoutDate= testData.get("checkout");
        mapCheckINCheckOut.put("checkin",checkinDate);
        mapCheckINCheckOut.put("checkout",checkoutDate);
        return mapCheckINCheckOut;

    }
}
