package com.podium.pages;

import com.podium.pages.common.resources.BaseActions;
import com.podium.pages.common.resources.HeaderComponent;
import org.openqa.selenium.support.PageFactory;

import static com.podium.driver.DriverFactory.getDriver;

public class HomePage extends BaseActions {

    private String URI = super.getProtocol() + super.getDomain() + "/";
    private HeaderComponent headerComponent;


    public HomePage() {

        PageFactory.initElements(getDriver(), this);
        headerComponent = new HeaderComponent();

    }


    public HomePage accessPage() {
        if (!super.verifyURI(this.URI)) {

            throw new RuntimeException("Invalid URI: " + this.URI);
        }
        accessPage(this.URI);
        return this;
    }

    public HeaderComponent headerComponent() {
        return this.headerComponent;
    }


}
