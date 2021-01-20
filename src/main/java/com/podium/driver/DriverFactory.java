package com.podium.driver;

import com.podium.driver.browser.ChromeManager;
import com.podium.listeners.TestListener;
import io.github.cdimascio.dotenv.Dotenv;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class DriverFactory {

    private static final Logger logger = LogManager.getLogger(TestListener.class);

    private static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>() {

        @Override
        protected synchronized WebDriver initialValue() {

            return getBrowser();

        }

    };


    public static WebDriver getDriver() {

        return threadDriver.get();

    }

    public static void removeThreadDriver() {

        threadDriver.remove();

    }


    private static WebDriver getBrowser() {


        Dotenv dotenv = Dotenv.load();

        switch (dotenv.get("BROWSER").toLowerCase()) {
            case "chrome":
                return new ChromeManager().getBrowserDriver();
            default:
                logger.error("Driver selected doesn't exist! Using default browser: chrome.");
                return new ChromeManager().getBrowserDriver();
        }
    }

}
