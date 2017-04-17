package com.db.am.bauhaus.project.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebDriver;

public class SubCategoryPage extends PageObject {

    public SubCategoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "h1")
    WebElementFacade titleHeader;

    public String getTitleHeader() {
        return titleHeader.getText();
    }
}
