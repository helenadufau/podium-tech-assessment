package com.podium.driver.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public interface BrowserManager {

    WebDriver getBrowserDriver();

    ChromeOptions getBrowserOptions();

    ChromeDriverService getBrowserService() ;

}
