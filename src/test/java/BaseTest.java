import com.podium.listeners.TestListener;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;

import static com.podium.driver.DriverFactory.getDriver;
import static com.podium.driver.DriverFactory.removeThreadDriver;

@Listeners(TestListener.class)
public class BaseTest {


    @AfterMethod
    public void finalizeTestConfiguration(ITestResult result) {

        Dotenv dotenv = Dotenv.load();

        if (Boolean.parseBoolean(dotenv.get("BROWSER_CLOSE").toLowerCase())) {

            WebDriver driver = getDriver();
            if (driver != null) {
                driver.quit();

            }
            removeThreadDriver();

        }
    }
}

