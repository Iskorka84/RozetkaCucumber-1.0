package org.prog.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.prog.pages.RozetkaPage;

import java.time.Duration;

public class WebSteps {

    public static WebDriver driver;
    private RozetkaPage rozetkaPage;

    @Given("I start my browser")
    public void startBrowser() {
        if (driver != null) {
            System.out.println("Driver already running");
        } else {
            driver = new ChromeDriver();
        }
    }

    @And("I load network equipment page")
    public void iLoadNetworkEquipmentPage() {
        if (rozetkaPage == null) {
            rozetkaPage = new RozetkaPage(driver);
        }
        rozetkaPage.loadPage();
        driver.manage().window().fullscreen();
    }


    @And("I scroll down to pagination area")
    public void iScrollDownToPaginationArea() {
        WebElement pagination = rozetkaPage.findPaginationArea();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", pagination);
    }

    @When("I click on link with {string}")
    public void iClickOnPageNumber(String page_number) {
        if (driver.getCurrentUrl().contains("https://rozetka.com.ua/ua/network-equipment/c80111/")) {
            rozetkaPage.goToPageByNumber(page_number);
        }
    }
    @Then("Page number {string} is loaded")
    public void pageWithSelectedNumberIsLoaded(String page_number) {
        WebElement activePage = new WebDriverWait(driver, Duration.ofSeconds(40))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='pagination__link ng-star-inserted pagination__link--active']")));
        String currentPageNumber = activePage.getText();
        Assertions.assertTrue(currentPageNumber.equals(page_number),"The wrong page has loaded");
    }

}
