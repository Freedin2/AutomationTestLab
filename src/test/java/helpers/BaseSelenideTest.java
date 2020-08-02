package helpers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.testng.ScreenShooter;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;


public class BaseSelenideTest {

    protected final static Logger LOG = Logger.getLogger(BaseSelenideTest.class);


    public BaseSelenideTest() {
        System.setProperty("org.uncommons.reportng.escape-output", "false");
        System.setProperty("selenide.openBrowserTimeout", "30000");
        System.setProperty("selenide.browser", Config.getInstance().getBrowser());
        Configuration.timeout = 60000;
        Configuration.reopenBrowserOnFail = true;
        ScreenShooter.captureSuccessfulTests = true;
        Configuration.savePageSource = false;
        Configuration.startMaximized = true;
        Configuration.driverManagerEnabled = true;
    }


    @BeforeTest
    public void globalLogs(final ITestContext testContext) {
        LOG.info("Cьют: " + testContext.getCurrentXmlTest().getSuite().getName());
        LOG.info("Запускаем тест: " + testContext.getCurrentXmlTest().getName());
    }


    @AfterClass(alwaysRun = true, description = "Очищение cookies браузера")
    public void clearCookiesAfter(ITestContext context) {
        try {
            Selenide.clearBrowserCookies();
            Selenide.open("");
        } catch (WebDriverException ex) {
            LOG.error(ex.getMessage());
            Selenide.open("");
        }
    }

}