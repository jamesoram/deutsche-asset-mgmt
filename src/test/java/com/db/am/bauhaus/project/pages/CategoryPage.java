package com.db.am.bauhaus.project.pages;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CategoryPage extends PageObject {

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    private static final String SUBCATEGORY_BY_NAME =
            "//div[contains(@class, 'with-flex-height')]//a[contains(., '%s')]";

    public SubCategoryPage clickSubcategoryByName(String name) {
        getDriver().findElement(By.xpath(String.format(SUBCATEGORY_BY_NAME, name))).click();
        return new SubCategoryPage(getDriver());
    }
}
