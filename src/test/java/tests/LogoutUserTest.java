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
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AccountCreatedPage;
import pages.AccountDeletedPage;
import pages.AccountInformationPage;
import pages.HomePage;
import pages.SignupLoginPage;

public class LogoutUserTest extends BaseTest {

    @Test(description = "Test Case 4: Logout User")
    public void logoutUserFlow() {
        //Register a new user (so we have valid login credentials)
        HomePage home = new HomePage(driver);
        home.open();
        Assert.assertTrue(home.isHomeVisible(), "Home page not visible");

        home.clickSignupLogin();
        SignupLoginPage signupPage = new SignupLoginPage(driver);
        Assert.assertTrue(signupPage.isNewUserSignupVisible(), "'New User Signup!' not visible");

        String firstName = "Logout";
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

        //Logout
        accountCreated.clickLogout();

        //Verify we are back at login page
        Assert.assertTrue(signupPage.isLoginToYourAccountVisible(), "User was not redirected to login page after logout");

        //Cleanup: log back in and delete the account
        signupPage.login(email, password);
        Assert.assertTrue(accountCreated.isLoggedInVisible(), "Could not log back in after logout");

        accountCreated.clickDeleteAccount();
        AccountDeletedPage deletedPage = new AccountDeletedPage(driver);
        Assert.assertTrue(deletedPage.isAccountDeletedVisible(), "'ACCOUNT DELETED!' not visible");
        deletedPage.clickContinue();
    }
}
