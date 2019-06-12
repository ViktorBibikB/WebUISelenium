package SOS.Tests._5_Emergency_Supplier;

import SOS.Locators.LoginPage.LoginPageLocators;
import SOS.Locators.MainPage.MainPageLocators;
import SOS.Locators.PurchasingPage.*;
import SOS.Tests._1_Create_Supplier_New_NewPendingApproval_Emergency_Approved_Reject.Demo_Agro_Farma_Site_Without_AutoApproval.Create_Supplier_Status_Emergancy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static SOS.WebHelpers.WebHelpers.goToUrl;
import static WebHelpers.GettersAndSetters.getSupplierName;
import static WebHelpers.GettersAndSetters.setSupplierName;
import static WebHelpers.WebHelpers.*;

public class Test_1_Create_Emergency_Supplier {
    public static WebDriver driver = null;
    public static LoginPageLocators loginPageLocators = null;
    public static MainPageLocators mainPageLocators = null;
    public static SearchSuppliersPage supplierSearchCreatePage = null;
    public static CreateSupplierPage createSupplierPage = null;
    public static ApprovalProcessSelectionPage approveSupplierListPage = null;
    public static ApproveSupplierPage approveSupplierPage = null;
    public static SupplierDetailPage supplierDetailPage = null;
    public static Logger logger = LogManager.getLogger(Create_Supplier_Status_Emergancy.class);

    @Before
    public void beforEeach() {
        System.setProperty("webdriver.chrome.driver", "C://Users//viktor.bibik//webdrivers//Chromedriver//chromedriver.exe");
        //System.setProperty("webdriver.gecko.driver", "C://Users//viktor.bibik//webdrivers//Firefoxdriver//geckodriver.exe");
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();

        loginPageLocators = new LoginPageLocators(driver);
        mainPageLocators = new MainPageLocators(driver);
        supplierSearchCreatePage = new SearchSuppliersPage(driver);
        createSupplierPage = new CreateSupplierPage(driver);
        approveSupplierListPage = new ApprovalProcessSelectionPage(driver);
        approveSupplierPage = new ApproveSupplierPage(driver);
        supplierDetailPage = new SupplierDetailPage(driver);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        goToUrl(driver, "http://localhost:8080/sos/splashScreen.sos");
        sendTextToMultipleWebElements(loginPageLocators.fieldLogin, "VBIBIKSU", loginPageLocators.fieldPassword, "deadman11");
        clickButtonIfEnable(loginPageLocators.buttonLogin);
        clickButtonIfEnable(mainPageLocators.tablePurchasing);

    }

    @After
    public void afterEach() {

        driver.quit();
    }

    @Test
    public void createSupplierEmergency(){
        clickButtonIfEnable(mainPageLocators.linkSupplier);
        selectWebElementFromDropDownList(supplierSearchCreatePage.dropdownListSiteCode, "130"); // 130 - AGRO_FARMA;  SALES - DEMO;
        clickButton(supplierSearchCreatePage.buttonCreate);
        createSupplierPage.fillUserDataEmergencySupplier("asd@ukr.net","Supplier_Emergency_DEMO_SALES_", "123-123-1234", "@ukr.net", "Emergency supplier was created");
        clickButtonIfEnable(createSupplierPage.buttonOKpopup);
        setSupplierName(supplierDetailPage.fieldSupplierName);

        System.out.println("Emergency Supplier was created, his number is: " + getTextFromWebElement(supplierDetailPage.fieldSupplierNumber));
        System.out.println("Emergency Supplier was created, his name is: " + getSupplierName());
        logger.info("Emergency Supplier was created, his number is: " + getTextFromWebElement(supplierDetailPage.fieldSupplierNumber));
        logger.info("Emergency Supplier was created, his name is: " + getSupplierName());
        logger.info("------------------------------------------------------");

        Assert.assertEquals("Supplier Detail", createSupplierPage.textSupplierDetails.getText());
    }
}
