package SpecBuilder;
import ExtentReport.ExtentReportManager;
import PropertyReader.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.matcher.DetailedCookieMatcher;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.*;
import org.hamcrest.Matcher;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class RestfulBookerSpecBuilder {

    String baseURIAuth;
    readProperty readPropertyObj = new readProperty();

    public RequestSpecification RequestAuthorization() {
        //baseURIAuth = readPropertyObj.readPropertyFile("baseURI");
        RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(baseURIAuth).addHeader("Content-Type", "application/json").build();
        return requestSpecification;
    }

    public ResponseSpecification ResponseSpecification() {
        ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        return responseSpecification;

    }

    private static void printRequestLoginReport(RequestSpecification requestSpecification){

    }
}

