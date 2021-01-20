package com.podium.driver.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public interface BrowserManager {

    WebDriver getBrowserDriver();

    ChromeOptions getBrowserOptions();

    ChromeDriverService getBrowserService();

}
