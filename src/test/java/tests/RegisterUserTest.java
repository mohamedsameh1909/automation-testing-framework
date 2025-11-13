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

public class RegisterUserTest extends BaseTest {

    @Test(description = "Test Case 1: Register User")
    public void registerUserFlow() {
        HomePage home = new HomePage(driver);
        home.open();
        Assert.assertTrue(home.isHomeVisible(), "Home page not visible");

        home.clickSignupLogin();
        SignupLoginPage signupPage = new SignupLoginPage(driver);
        Assert.assertTrue(signupPage.isNewUserSignupVisible(), "'New User Signup!' not visible");

        String firstName = "Test";
        String lastName = "User";
        String email = Utils.generateRandomEmail();
        signupPage.enterNameAndEmail(firstName + lastName, email);
        signupPage.clickSignup();

        AccountInformationPage accountInfo = new AccountInformationPage(driver);
        Assert.assertTrue(accountInfo.isEnterAccountInfoVisible(), "'ENTER ACCOUNT INFORMATION' not visible");

        accountInfo.fillAccountForm(firstName, lastName, "password123");
        accountInfo.clickCreateAccount();

        AccountCreatedPage accountCreated = new AccountCreatedPage(driver);
        Assert.assertTrue(accountCreated.isAccountCreatedVisible(), "'ACCOUNT CREATED!' not visible");
        accountCreated.clickContinue();

        Assert.assertTrue(accountCreated.isLoggedInVisible(), "'Logged in as username' not visible");

        accountCreated.clickDeleteAccount();

        AccountDeletedPage deletedPage = new AccountDeletedPage(driver);
        Assert.assertTrue(deletedPage.isAccountDeletedVisible(), "'ACCOUNT DELETED!' not visible");
        deletedPage.clickContinue();
    }
}
