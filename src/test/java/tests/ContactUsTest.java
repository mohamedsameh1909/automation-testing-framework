/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tests;

/**
 *
 * @author HP
 */
import framework.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.file.Paths;
import pages.ContactUsPage;
import pages.HomePage;

public class ContactUsTest extends BaseTest {

    @Test(description = "Test Case 6: Contact Us Form")
    public void contactUsFormFlow() {
        HomePage home = new HomePage(driver);
        home.open();
        Assert.assertTrue(home.isHomeVisible(), "Home page not visible");

        //Navigate to Contact Us
        driver.findElement(org.openqa.selenium.By.linkText("Contact us")).click();

        ContactUsPage contactUs = new ContactUsPage(driver);
        Assert.assertTrue(contactUs.isGetInTouchVisible(), "'GET IN TOUCH' is not visible");

        //Example file to upload
        String filePath = Paths.get("src/test/resources/dummy.txt").toAbsolutePath().toString();

        contactUs.fillContactForm("John Doe", "john@example.com", "Automation Test", "This is a test message.", filePath);
        contactUs.submitForm();

        Assert.assertTrue(contactUs.isSuccessMessageVisible(), "Success message not visible after form submission");

        contactUs.clickHome();
        Assert.assertTrue(home.isHomeVisible(), "Did not return to Home page");
    }
}
