package com.podium.pages.common.resources;

import com.podium.listeners.TestListener;
import io.github.cdimascio.dotenv.Dotenv;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static com.podium.driver.DriverFactory.getDriver;

public class BaseActions {

    protected static final Logger logger = LogManager.getLogger(TestListener.class);

    protected void accessDomainPage() {

        accessPage(getDomain());

    }

    protected String getDomain() {

        Dotenv dotenv = Dotenv.load();

        return dotenv.get("DOMAIN");

    }

    protected String getProtocol() {

        Dotenv dotenv = Dotenv.load();

        return dotenv.get("PROTOCOL") + "://";

    }

    protected String getCurrentUrl() {

        return getDriver().getCurrentUrl();

    }

    protected void accessPage(String url) {

        getDriver().get(url);

    }

    public boolean verifyURI(String URI) {

        if (!"https://".equals(this.getProtocol())) {
            logger.error("Couldn't verify URI, protocol not supported.");
            return false;
        }

        try {
            URL url = new URL(URI);

            HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();
            httpURLConnect.setRequestMethod("GET");
            httpURLConnect.setConnectTimeout(1000);

            httpURLConnect.connect();
            return (httpURLConnect.getResponseCode()) < 400 && (httpURLConnect.getResponseCode() >= 200);
        } catch (Exception e) {
            logger.error("Couldn't verify URI");
            return false;
        }

    }

    protected void hoverElement(WebElement webElement) {

        this.explicitWaitsVisibilityOf(webElement);
        this.explicitWaitsElementToBeClickable(webElement);
        org.openqa.selenium.interactions.Actions builder = new org.openqa.selenium.interactions.Actions(getDriver());
        builder.moveToElement(webElement).perform();

    }

    protected void write(WebElement webElement, String text) {
        this.explicitWaitsVisibilityOf(webElement);
        webElement.clear();
        webElement.click();
        webElement.sendKeys(text);

    }

    public void select(WebElement webElement, String value) {

        this.explicitWaitsVisibilityOf(webElement);
        Select combo = new Select(webElement);
        combo.selectByVisibleText(value);

    }

    protected void clickOnElement(WebElement webElement) {

        this.explicitWaitsElementToBeClickable(webElement);
        webElement.click();

    }

    public void upload(WebElement webElement, String fileName) {

        this.explicitWaitsVisibilityOf(webElement);
        this.explicitWaitsElementToBeClickable(webElement);
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        if (file.exists()) {

            webElement.sendKeys(file.getAbsolutePath());

        }

    }

    protected String getAttribute(WebElement webElement, String attribute) {

        this.explicitWaitsVisibilityOf(webElement);
        return webElement.getAttribute(attribute);

    }

    protected String getText(WebElement webElement) {

        this.explicitWaitsVisibilityOf(webElement);
        return webElement.getText();

    }

    public boolean areAllInputFieldsClear() {
        List<WebElement> inputElements = getDriver().findElements(By.xpath("//input"));
        for (WebElement webElement : inputElements) {
            if (webElement.getAttribute("value") == "") {

                return false;

            }
        }

        return true;

    }

    protected boolean isElementPresent(WebElement webElement) {


        try {
            explicitWaitsVisibilityOf(webElement);
            webElement.isDisplayed();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected void explicitWaitsVisibilityOf(WebElement webElement) {

        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        wait.until(ExpectedConditions.visibilityOf(webElement));

    }

    protected void explicitWaitsElementToBeClickable(WebElement webElement) {

        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));

    }

    protected void waitForPageLoad() {

        new WebDriverWait(getDriver(), 10000).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

    }

}
