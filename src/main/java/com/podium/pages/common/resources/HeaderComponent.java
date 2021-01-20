package com.podium.pages.common.resources;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.podium.driver.DriverFactory.getDriver;

public class HeaderComponent extends BaseActions {

    @FindBy(css = ".menu-item:nth-child(3) > span")
    protected WebElement productsItem;
    @FindBy(css = "#theme-white > nav > div > div.menu-left > ul > li:nth-child(4)")
    protected WebElement solutionItem;
    @FindBy(linkText = "Enterprise")
    protected WebElement enterpriseItem;
    @FindBy(css = "#theme-white > nav > div > div.menu-left > ul > li:nth-child(6)")
    protected WebElement resourcesItem;
    @FindBy(linkText = "Pricing")
    protected WebElement pricingItem;
    @FindBy(linkText = "Login")
    protected WebElement loginItem;
    @FindBy(linkText = "Watch Demo")
    protected WebElement watchDemoItem;
    @FindBy(css = ".product-items:nth-child(1) .product-text > span")
    protected WebElement reviews;
    @FindBy(css = ".product-items:nth-child(2) .product-text > span")
    protected WebElement feedback;
    @FindBy(css = ".product-items:nth-child(3) .product-text > span")
    protected WebElement payments;
    @FindBy(css = ".product-items:nth-child(4) .product-text > span")
    protected WebElement teamChat;
    @FindBy(css = ".product-items:nth-child(5) .product-text > span")
    protected WebElement videoChat;
    @FindBy(css = ".product-items:nth-child(6) .product-text > span")
    protected WebElement webChat;
    @FindBy(css = ".product-items:nth-child(7) .product-text > span")
    protected WebElement inbox;
    @FindBy(css = "#page > nav > div.menu-wrapper > div.menu-left > ul > li:nth-child(3) > ul > li:nth-child(8) > a")
    protected WebElement tryingFreeVersion;
    @FindAll({@FindBy(tagName = "a")})
    List<WebElement> allLinks;

    public HeaderComponent() {

        PageFactory.initElements(getDriver(), this);

    }

    public HeaderComponent hoverOnProducts() {

        super.hoverElement(this.productsItem);

        return this;
    }

    public boolean areAllProductsAvailable() {

        return super.isElementPresent(this.reviews) && super.isElementPresent(this.feedback)
                && super.isElementPresent(this.payments) && super.isElementPresent(this.teamChat)
                && super.isElementPresent(this.videoChat) && super.isElementPresent(this.webChat)
                && super.isElementPresent(this.inbox);

    }

    public boolean areAllHeaderElementsAvailable() {

        return super.isElementPresent(this.productsItem) && super.isElementPresent(this.solutionItem)
                && super.isElementPresent(this.enterpriseItem) && super.isElementPresent(this.resourcesItem)
                && super.isElementPresent(this.pricingItem) && super.isElementPresent(this.loginItem)
                && super.isElementPresent(this.watchDemoItem);

    }

    public boolean isLoginLinkOk() {

        return getAttribute(this.loginItem, "href").equals("https://app.podium.com/");

    }


    public boolean isPricingLinkOk() {

        return getAttribute(this.pricingItem, "href").equals("https://www.podium.com/pricing");

    }

    public boolean areLinksOk() {

        boolean areOk = true;
        super.waitForPageLoad();
        for (WebElement link : allLinks) {
            boolean isOk = verifyURI(link.getAttribute("href"));
            if (!isOk) {
                areOk = false;
                logger.error("Broken link: " + link.getAttribute("href"));
            }
        }
        return areOk;
    }

}
