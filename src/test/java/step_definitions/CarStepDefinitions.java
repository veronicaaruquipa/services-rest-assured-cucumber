package step_definitions;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.junit.Assertions;
import org.junit.Assert;
import services.ServiceRequest;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * @project rest-assured-cucumber
 * @user veronica.aruquipa
 * @date 03-06-17 06:43 PM
 */
public class CarStepDefinitions {
    ServiceRequest serviceRequest;
    JsonObject brandsJsonObject;
    JsonObject modelsJsonObject;
    JsonObject modelYearsJsonObject;
    String locale;
    String brand;
    String model;

    @Before
    public void setUp() throws Exception {
        serviceRequest = new ServiceRequest();
        locale = "";
        brand = "";
        model = "";
    }

    @Given("the local:(.*) information.")
    public void the_local_information(String locale) throws IOException {
        brandsJsonObject = serviceRequest.getBrandsTest(locale);
        Assert.assertNotNull(brandsJsonObject);
    }

    @Then("all brands associated to (.*) are available for user.")
    public void user_can_see_all_brands_available(String locale){
        System.out.println("Cars Brand Available Associated to "+locale+"!");
        System.out.println("-----------------------------------------------------");

        int i = 1;

        Set<Map.Entry<String, JsonElement>> brands = brandsJsonObject.entrySet();
        for (Map.Entry<String, JsonElement> brand : brands) {
            System.out.println(i +": "+brand.getValue());
            i++;
        }
    }

    @Given("the brand:(.*) and local:(.*) information.")
    public void the_brand_and_local_information(String locale, String brand) throws IOException {
        String brandCode = serviceRequest.getBrandCode(locale, brand);
        modelsJsonObject = serviceRequest.getModelsTest(locale, brandCode);
        Assert.assertNotNull(modelsJsonObject);
    }

    @Then("all models associated to (.*) and (.*) are available for user.")
    public void user_can_see_all_brands_and_models_available(String locale, String carBrand){
        System.out.println("Cars Model Available associated to "+locale+" and "+carBrand+"!");
        System.out.println("-----------------------------------------------------");

        int i = 1;

        Set<Map.Entry<String, JsonElement>> models = modelsJsonObject.entrySet();
        for (Map.Entry<String, JsonElement> model : models) {
            System.out.println(i +": "+model.getValue());
            i++;
        }
    }

    @Given("the model:(.*), brand:(.*) and local:(.*) information.")
    public void the_local_brand_and_model_information(String locale, String brand, String model) throws IOException {
        String brandCode = serviceRequest.getBrandCode(locale, brand);
        modelYearsJsonObject = serviceRequest.getModelYearsTest(locale, brandCode, model);
        Assert.assertNotNull(modelYearsJsonObject);
    }

    @Then("all model years associated to (.*), (.*) and (.*) are available for user.")
    public void user_can_see_all_brands_models_and_years_available(String locale, String brand, String model) throws IOException {
        System.out.println("Cars Model Years Available Associated to "+locale+", "+brand+" and "+model+"!");
        System.out.println("-----------------------------------------------------");

        int i = 1;

        Set<Map.Entry<String, JsonElement>> modelYears = modelYearsJsonObject.entrySet();
        for (Map.Entry<String, JsonElement> year : modelYears) {
            System.out.println(i +": "+year.getValue());
            i++;
        }
    }

    @Given ("the manufacturer/brands are filtered by locale -> (.*)")
    public void the_manufactures_are_available_by_locale(String locale) throws IOException {
        this.locale = locale;
        Assert.assertTrue(!locale.equals(""));
    }
    @When("the brand -> (.*) is selected by user")
    public void the_brand_is_selected_by_user(String brand){
        this.brand = brand;
        Assert.assertTrue(!locale.equals(""));
    }

    @And("the model -> (.*) is selected as well")
    public void the_model_is_selecteed_as_well(String model){
        this.model = model;
        modelYearsJsonObject = serviceRequest.getModelYearsTest(locale, brand, model);
        Assert.assertNotNull(modelYearsJsonObject);
    }

    @Then ("all model years are available in order to user select the specific car.")
    public void all_model_years_are_available_for_user_got(){
        int i = 1;
        Set<Map.Entry<String, JsonElement>> modelYears = modelYearsJsonObject.entrySet();
        for (Map.Entry<String, JsonElement> year : modelYears) {
            System.out.println(i +": "+year.getValue());
            i++;
        }
    }
}
