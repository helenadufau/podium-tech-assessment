package com.podium.driver.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;


public class ChromeManager implements BrowserManager {

    @Override
    public WebDriver getBrowserDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(getBrowserService(), getBrowserOptions());

    }

    @Override
    public ChromeOptions getBrowserOptions() {

        ChromeOptions options = new ChromeOptions();

        Dotenv dotenv = Dotenv.load();

        if (Boolean.parseBoolean(dotenv.get("HEADLESS").toLowerCase())) {

            options.addArguments("--headless");

        }
        options.addArguments("--mute-audio");
        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--log-level=OFF");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-crash-reporter");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-logging");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--log-level=3");
        options.addArguments("--output=/dev/null");
        options.setAcceptInsecureCerts(true);
        return options;

    }

    @Override
    public ChromeDriverService getBrowserService() {

        return new ChromeDriverService.Builder().withSilent(true).withVerbose(false).build();

    }

}
