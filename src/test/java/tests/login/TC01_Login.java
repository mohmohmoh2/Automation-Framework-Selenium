package tests.login;

import Pages.P01_Login;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static DriverSettings.DriverManager.*;
import static utilities.DataUtils.getJsonData;
import static utilities.DataUtils.getPropertyValue;

public class TC01_Login {
    private static final String EXPECTED_URL;

    static {
        try {
            EXPECTED_URL = getPropertyValue("config", "BASE_URL");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeMethod
    public void setUp() throws IOException {
        // TODO: Open the browser
        setDriver(new ChromeDriver());
        getDriver().get(getPropertyValue("config", "BASE_URL"));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testLogin() throws IOException {
        new P01_Login(getDriver())
                .enterUsername(getJsonData("login", "username"))
                .enterPassword(getJsonData("login", "password"))
                .clickLogin();
        Assert.assertTrue(new P01_Login(getDriver()).assertLoginTc(EXPECTED_URL));
    }

    @AfterMethod
    public void tearDown() {
        // TODO: Close the browser
        quitDriver();
    }
}
