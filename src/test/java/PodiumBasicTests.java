import com.podium.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PodiumBasicTests extends BaseTest {


    private HomePage homePage;

    @BeforeMethod(description = "Prepare test")
    public void prepareTest() {

        instantiatePages();

    }

    public void instantiatePages() {

        homePage = new HomePage();

    }

    @Test(description = "Testing if the product menu shows the expected products")
    public void showingAllHeaderElements() {

        Assert.assertTrue(homePage.accessPage().headerComponent().hoverOnProducts().areAllProductsAvailable());

    }


    @Test(description = "Testing if the header shows the expected elements ")
    public void showingAllProducts() {

        Assert.assertTrue(homePage.accessPage().headerComponent().areAllHeaderElementsAvailable());

    }

    @Test(description = "Testing login link")
    public void loginLink() {

        Assert.assertTrue(homePage.accessPage().headerComponent().isLoginLinkOk());

    }

    @Test(description = "Testing princing link")
    public void pricingLink() {

        Assert.assertTrue(homePage.accessPage().headerComponent().isPricingLinkOk());

    }

    @Test(description = "Searching for broken links on home page")
    public void brokenLinksOnHomePage() {

        Assert.assertTrue(homePage.accessPage().headerComponent().areLinksOk());

    }


}
