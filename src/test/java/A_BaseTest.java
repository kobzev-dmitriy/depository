import app.App;
import app.AppConfig;
import helpers.Driver;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

class A_BaseTest {

    protected App app;
    protected SoftAssert softAssert;
    protected Logger logger;

    @BeforeClass
    public void setUp() {

        Driver.initDriver();

        app = new App();
        softAssert = new SoftAssert();

        logger = LogManager.getLogger("");
        DOMConfigurator.configure("src/main/resources/log4j.xml");


        app.loginPage.open();
        app.loginPage.login(AppConfig.loginUser, AppConfig.passwordUser);

    }

    @AfterClass
    public void tearDown() {
        app.loginPage.open();
        app.mainPage.logout();
        Driver.clearCookies();
        Driver.close();
    }
}
