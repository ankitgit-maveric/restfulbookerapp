package RestUtil;

import ExtentReport.ExtentReportManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestUtil {


private static RequestSpecification postRequestSpecification(Object payload){
    return  RestAssured.given().log().all()
            .headers("Content-Type","application/json")
            .body(payload);
}

private static RequestSpecification getRequestSpecification(){
    return RestAssured.given().log().all()
            .header("Content-Type","application/json");

    }

    private static RequestSpecification getRequestWithQueryParamSpecification(Map<String, String> queryParam){
     return   RestAssured.given().log().all()
                .header("Content-Type","application/json")
                .queryParams(queryParam)
                .when();
    }

    private static RequestSpecification putAndpatchRequestWithQueryParamSpecification(String CookieToken, Object payload) {
        return RestAssured.given().log().all()
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .header("Cookie", "token=" +CookieToken)
                .body(payload);
    }
    public static Response postRequest(String endPoint, Object payload){
    RequestSpecification postRequest= postRequestSpecification(payload);
       // printRequestLoginReport(postRequest);
        return   postRequest.post(endPoint).then().log().all().assertThat().statusCode(200).extract().response();



    }



private static void printRequestLoginReport(RequestSpecification requestSpecification){
    QueryableRequestSpecification queryableRequestSpecification= SpecificationQuerier.query(requestSpecification);
    ExtentReportManager.logInfoDetail("Request Details are:");
    ExtentReportManager.logInfoDetail("Base URI:"+queryableRequestSpecification.getBaseUri());
    ExtentReportManager.logInfoDetail("Request Body:"+queryableRequestSpecification.getBody());
    ExtentReportManager.logInfoDetail("Content Type:"+queryableRequestSpecification.getContentType());
    ExtentReportManager.logInfoDetail("Query param are"+queryableRequestSpecification.getQueryParams());
}


public static void printResponseLoginReport(Response response){

    ExtentReportManager.logInfoDetail("Response Detail");
    ExtentReportManager.logInfoDetail("Response Body:"+response.getBody());
    ExtentReportManager.logInfoDetail("Response Status Code:"+response.statusCode());

}
public static Response getRequest(String endPoint){
    RequestSpecification getRequest= getRequestSpecification();
  //  printRequestLoginReport(getRequest);
   return getRequest.get(endPoint).then().log().all().assertThat().statusCode(200).extract().response();

}
public static Response getRequestQueryParam(String endPoint, Map<String, String> queryParam){
    RequestSpecification getRequestQueryParam= getRequestWithQueryParamSpecification(queryParam);
    //printRequestLoginReport(getRequestQueryParam);
    return getRequestQueryParam
            .get(endPoint)
            .then().log().all().assertThat().statusCode(200).extract().response();

}


public static Response putRequest(String endPoint, String CookieToken,Object payload){

    RequestSpecification putRequestSpecification= putAndpatchRequestWithQueryParamSpecification(CookieToken,payload);
   // printRequestLoginReport(putRequestSpecification);
    return putRequestSpecification
            .when()
            .put(endPoint)
            .then().log().all().assertThat().statusCode(200).extract().response();
}
    public static Response patchRequest(String endPoint, String CookieToken,Object payload){
        RequestSpecification patchRequestSpecification= putAndpatchRequestWithQueryParamSpecification(CookieToken,payload);
      //  printRequestLoginReport(patchRequestSpecification);
        return patchRequestSpecification
                .when()
                .patch(endPoint)
                .then().log().all().assertThat().statusCode(200).extract().response();
    }

    public static Response deleteRequest(String endPoint, String CookieToken){

    return  RestAssured.given().log().all()
            .header("Content-Type","application/json")
            .header("accept","application/json")
            .header("Cookie","token="+CookieToken)
            .when()
            .delete(endPoint)
            .then().log().all().assertThat().statusCode(201).extract().response();
    }


}
