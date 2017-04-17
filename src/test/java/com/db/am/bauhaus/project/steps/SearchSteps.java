package com.db.am.bauhaus.project.steps;

import com.db.am.bauhaus.project.SearchFor;
import com.db.am.bauhaus.project.SearchTarget;
import com.db.am.bauhaus.project.SessionVar;
import com.db.am.bauhaus.project.pages.MainSearchPage;
import com.db.am.bauhaus.project.steplib.SearchUser;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.annotations.Steps;

import static net.serenitybdd.rest.SerenityRest.rest;
import static net.serenitybdd.rest.SerenityRest.then;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.containsText;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by ongshir on 05/10/2016.
 */
public class SearchSteps {

    private static final String URL = "https://www.etsy.com/uk";

    @Before
    public void before() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Steps
    SearchUser user;

    MainSearchPage mainSearchPage;

    @Given("^John is viewing the Etsy landing page$")
    public void goto_landing_page() {
        mainSearchPage.open();
    }

    @Given("^([^\\s]+) is viewing the Etsy landing page \\(screenplay\\)$")
    public void goto_landing_page_screenplay(String theUser) {
        theActorCalled(theUser).attemptsTo(Open.browserOn().the(mainSearchPage));
    }

    @Given("^an API client$")
    public void an_api_client() {
        // do nothing - specify that this is not a normal ui actor
    }

    @When("^he searches for a product from the input box$")
    public void search_from_input_box() {
        user.search_from_input_box();
    }

    @When("^he searches for a product from the drop-down menu$")
    public void search_from_dropdown_menu() {
        user.search_from_dropdown_menu();
    }

    @When("^he searches for a product from an icon$")
    public void search_from_icon() {
        user.search_from_icon();
    }

    @When("^he searches for a product from the input box \\(screenplay\\)$")
    public void search_from_input_box_screenplay() {
        theActorInTheSpotlight().attemptsTo(SearchFor.randomText());
    }

    @When("^it does a GET to /search with the parameter \"(.+)\"")
    public void do_a_get_to_search_with(String parameter) {
        rest().get(URL + "/search?q={param}", parameter);
    }

    @When("it does a GET to /search with the parameters \"(.+)\" and \"(.+)\"")
    public void do_a_get_with_search_with_2_params(String param1, String param2) {
        rest().get(URL + "/search?q={param1}&{param2}", param1, param2);
    }

    @Then("^the result should be displayed$")
    public void verify_search_result() {
        user.verify_result_for_top_categories();
        user.verify_result_for_all_categories();
    }

    @Then("^the chosen category should be displayed$")
    public void category_should_be_displayed() {
        user.verify_correct_category();
    }

    @Then("^the result should be displayed \\(screenplay\\)$")
    public void verify_search_result_screenplay() {
        String searchText = Serenity.sessionVariableCalled(SessionVar.SEARCH_TEXT).toString();
        theActorInTheSpotlight().should(
                seeThat("the top categories header ", the(SearchTarget.TOP_CATEGORIES_HEADER), containsText(searchText)),
                seeThat("the all categories header ", the(SearchTarget.ALL_CATEGORIES_HEADER), containsText(searchText))
        );
    }

    @Then("^the results contain the word \"(.+)\"$")
    public void the_results_contain(String parameter) {
        then().body(containsString(parameter));
    }

    @Then("^the results contain the words \"(.+)\" and \"(.+)\"$")
    public void the_results_contain(String param1, String param2) {
        the_results_contain(param1);
        the_results_contain(param2);
    }
}
