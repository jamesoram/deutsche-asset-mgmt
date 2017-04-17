package com.db.am.bauhaus.project.pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebDriver;

/**
 * Created by ongshir on 05/10/2016.
 */
@DefaultUrl("/")
public class MainSearchPage extends PageObject {

    @FindBy(id = "search-query")
    WebElementFacade inputBox;

    @FindBy(css = ".btn.btn-orange.btn-append")
    WebElementFacade searchButton;

    @FindBy(id = "catnav-dropdown")
    WebElementFacade categoryDropdown;

    private final static String MENUBAR_BY_CONTENT =
            "id('catnav-menubar')/li[contains(., '%s') and not(contains(@class, 'display-none'))]";

    private final static String MENUBAR_SUBCATEGORY =
            "id('catnav-dropdown')//a[contains(., '%s')]";

    private final static String ICON_BY_CONTENT = "" +
            "//span[@class='vesta-hp-category-default']//span[contains(., '%s')]";

    public MainSearchPage(WebDriver driver) {
        super(driver);
    }

    public void searchFromInputBox(String searchText) {
        inputBox.waitUntilPresent().sendKeys(searchText);
        searchButton.click();
    }

    public void searchFromDropdownMenu(String generalSearchText, String subcategoryText) {
        // click on the general category in order to display drop-down
        getDriver().findElement(
                By.xpath(String.format(MENUBAR_BY_CONTENT, generalSearchText))).click();
        // wait for the submenu to appear
        waitFor(categoryDropdown);
        // proceed to choose a subcategory
        getDriver().findElement(
                By.xpath(String.format(MENUBAR_SUBCATEGORY, subcategoryText))).click();
    }

    public CategoryPage clickIconByContent(String content) {
        getDriver().findElement(By.xpath(String.format(ICON_BY_CONTENT, content))).click();
        return new CategoryPage(getDriver());
    }

    public String getTopCategoriesHeader() {
        return find(By.cssSelector("h4.pb-xs-1-5")).getText();
    }

    public String getAllCategoriesHeader() {
        return find(By.cssSelector("h1.conform-heading.display-inline")).getText();
    }
}
