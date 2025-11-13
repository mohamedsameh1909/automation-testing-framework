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
import framework.Utils;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AccountCreatedPage;
import pages.AccountDeletedPage;
import pages.AccountInformationPage;
import pages.HomePage;
import pages.SignupLoginPage;

public class LoginUserTest extends BaseTest {

    @Test(description = "Test Case 2: Login User with correct credentials")
    public void loginUserWithCorrectCredentials() {
        HomePage home = new HomePage(driver);
        home.open();
        Assert.assertTrue(home.isHomeVisible(), "Home page not visible");

        home.clickSignupLogin();
        SignupLoginPage signupPage = new SignupLoginPage(driver);
        Assert.assertTrue(signupPage.isNewUserSignupVisible(), "'New User Signup!' not visible");

        String firstName = "LoginTest";
        String lastName = "User";
        String email = Utils.generateRandomEmail();
        String password = "password123";

        signupPage.enterNameAndEmail(firstName + lastName, email);
        signupPage.clickSignup();

        AccountInformationPage accountInfo = new AccountInformationPage(driver);
        Assert.assertTrue(accountInfo.isEnterAccountInfoVisible(), "'ENTER ACCOUNT INFORMATION' not visible");

        accountInfo.fillAccountForm(firstName, lastName, password);
        accountInfo.clickCreateAccount();

        AccountCreatedPage accountCreated = new AccountCreatedPage(driver);
        Assert.assertTrue(accountCreated.isAccountCreatedVisible(), "'ACCOUNT CREATED!' not visible");
        accountCreated.clickContinue();
        Assert.assertTrue(accountCreated.isLoggedInVisible(), "'Logged in as username' not visible");
        
        home.clickLogout();
        
        home.clickSignupLogin();

        Assert.assertTrue(signupPage.isLoginToYourAccountVisible(), "'Login to your account' not visible");
        signupPage.login(email, password);

        Assert.assertTrue(accountCreated.isLoggedInVisible(), "Login failed with registered credentials");

        accountCreated.clickDeleteAccount();
        AccountDeletedPage deletedPage = new AccountDeletedPage(driver);
        Assert.assertTrue(deletedPage.isAccountDeletedVisible(), "'ACCOUNT DELETED!' not visible");
        deletedPage.clickContinue();
    }
}