package org.prog.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class RozetkaPage {
    public final WebDriver driver;

    private final static By PAGINATION_AREA = By.xpath("//div[@class='pagination ng-star-inserted']");
    private final static By PAGES = By.xpath("//a[@class='pagination__link ng-star-inserted']");

    public RozetkaPage(WebDriver driver) {
        this.driver = driver;
    }

    public void loadPage() {
        driver.get("https://rozetka.com.ua/network-equipment/c80111/");
    }

    public WebElement findPaginationArea() {
        return new WebDriverWait(driver, Duration.ofSeconds(100))
                .until(ExpectedConditions.presenceOfElementLocated(PAGINATION_AREA));
    }


    public void goToPageByNumber (String pageNumber) {
        new WebDriverWait(driver, Duration.ofSeconds(40))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PAGES));
        List<WebElement> pages = driver.findElements(PAGES);
        for (int i = 0; i < pages.size(); i++) {
            if (pages.get(i).getText().equals(pageNumber)) {
                pages.get(i).click();
                break;
            }
        }
    }
}