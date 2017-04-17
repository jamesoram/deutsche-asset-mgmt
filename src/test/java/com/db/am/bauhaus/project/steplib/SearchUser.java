package com.db.am.bauhaus.project.steplib;

import com.db.am.bauhaus.project.pages.SubCategoryPage;
import com.db.am.bauhaus.project.pages.MainSearchPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by ongshir on 05/10/2016.
 */
public class SearchUser extends ScenarioSteps {

    MainSearchPage mainSearchPage;
    SubCategoryPage subCategoryPage;

    // Not sure why the search terms are here - I would have had them inside the test as test data
    private String generalSearchText = "Craft";
    private String subcategoryText = "Sewing & Fiber";
    private String specificSearchText = "Needlecraft";

    @Step
    public void search_from_input_box() {
        mainSearchPage.searchFromInputBox(generalSearchText);
    }

    @Step
    public void search_from_dropdown_menu() {
        mainSearchPage.searchFromDropdownMenu(generalSearchText, subcategoryText);
    }

    @Step
    public void search_from_icon() {
        mainSearchPage.clickIconByContent(generalSearchText).clickSubcategoryByName(subcategoryText);
    }

    @Step
    public void verify_result_for_top_categories() {
        assertThat(mainSearchPage.getTopCategoriesHeader(), containsString(generalSearchText));
    }

    @Step
    public void verify_result_for_all_categories() {
        assertThat(mainSearchPage.getAllCategoriesHeader(), containsString(generalSearchText));
    }

    @Step
    public void verify_correct_category() {
        assertThat(subCategoryPage.getTitleHeader(), containsString(subcategoryText));
    }
}
