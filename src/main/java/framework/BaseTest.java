/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package framework;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.ByteArrayInputStream;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        driver = DriverFactory.initDriver(browser);
        driver.get("http://automationexercise.com");
        
        ((JavascriptExecutor) driver).executeScript(
        "let ads = document.querySelectorAll('iframe[id^=\"aswift_\"]'); " +
        "ads.forEach(a => a.style.display='none');"
    );
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (driver != null) {
            // Take screenshot for every test
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            String status;
            status = switch (result.getStatus()) {
                case ITestResult.SUCCESS -> "Success";
                case ITestResult.FAILURE -> "Failure";
                case ITestResult.SKIP -> "Skipped";
                default -> "Unknown";
            };

            Allure.addAttachment(status + " - " + result.getName(),
                    new ByteArrayInputStream(screenshot));

            // Quit driver after test finishes
            DriverFactory.quitDriver();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
