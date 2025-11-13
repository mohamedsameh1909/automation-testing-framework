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
import pages.HomePage;
import pages.SignupLoginPage;

public class LoginNegativeTest extends BaseTest {

    @Test(description = "Test Case 3: Login User with incorrect email and password")
    public void loginUserWithIncorrectCredentials() {
        HomePage home = new HomePage(driver);
        home.open();
        Assert.assertTrue(home.isHomeVisible(), "Home page not visible");

        home.clickSignupLogin();
        SignupLoginPage loginPage = new SignupLoginPage(driver);
        Assert.assertTrue(loginPage.isLoginToYourAccountVisible(), "'Login to your account' not visible");

        // Invalid credentials
        String fakeEmail = "notarealuser@example.com";
        String fakePassword = "wrongpassword";

        loginPage.login(fakeEmail, fakePassword);

        // Verify error message is visible
        Assert.assertTrue(
                loginPage.isLoginErrorVisible(),
                "Expected error message not visible after invalid login"
        );
    }
}
