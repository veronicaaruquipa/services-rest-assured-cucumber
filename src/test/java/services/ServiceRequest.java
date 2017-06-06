package services;

import com.google.gson.*;
import support.Constants;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.Set;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;

/**
 * @project rest-assured-cucumber
 * @user veronica.aruquipa
 * @date 03-06-17 06:40 PM
 */
public class ServiceRequest {
    Constants constants = Constants.instance();
    Gson gson;
    JsonParser gsonPaser;

    public ServiceRequest() throws IOException {
        gson = new GsonBuilder().setPrettyPrinting().create();
        gsonPaser = new JsonParser();
    }

    public JsonObject getBrandsTest(String locale) throws IOException {
        Object response;
        response =
                given().
                        queryParameters(constants.requestParam1, locale).
                        queryParameters(constants.requestAuth, constants.requestAuthValue).
                when().
                        get(constants.baseHost + constants.service1BasePath).
                then().
                        assertThat().
                        statusCode(200).
                        contentType(JSON).
                        extract().path("wkda");
        return (JsonObject) gsonPaser.parse(new StringReader(gson.toJson(response)));
    }

    public JsonObject getModelsTest(String locale, String manufacturerCode) {
        Object response;
        response =
                given().
                        queryParameters(constants.requestParam1, locale).
                        queryParameters(constants.requestParam2, manufacturerCode).
                        queryParameters(constants.requestAuth, constants.requestAuthValue).
                when().
                        get(constants.baseHost + constants.service2BasePath).
                then().
                        assertThat().
                        statusCode(200).
                        contentType(JSON).
                        extract().path("wkda");
        return (JsonObject) gsonPaser.parse(new StringReader(gson.toJson(response)));
    }

    public JsonObject getModelYearsTest(String locale, String manufacturerCode, String mainTypeCode) {
        Object response;
        response =
                given().
                        queryParameters(constants.requestParam1, locale).
                        queryParameters(constants.requestParam2, manufacturerCode).
                        queryParameters(constants.requestParam3, mainTypeCode).
                        queryParameters(constants.requestAuth, constants.requestAuthValue).
                when().
                        get(constants.baseHost + constants.service3BasePath).
                then().
                        assertThat().
                        statusCode(200).
                        contentType(JSON).
                        extract().path("wkda");
        return (JsonObject) gsonPaser.parse(new StringReader(gson.toJson(response)));
    }

    public String getBrandCode(String locale, String brandName) throws IOException {
        String brandCode = "";
        JsonObject brandsJsonObj = getBrandsTest(locale);
        Set<Map.Entry<String, JsonElement>> brands = brandsJsonObj.entrySet();
        for (Map.Entry<String, JsonElement> brand : brands) {
            if (brand.getValue().toString().equals(brandName)){
                return brand.getKey().toString();
            }
        }
        return brandCode;
    }
}
