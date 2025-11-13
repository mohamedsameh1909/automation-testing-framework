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

public class RegisterExistingEmailTest extends BaseTest {

    @Test(description = "Test Case 5: Register User with existing email")
    public void registerUserWithExistingEmail() {
        //First register a user (so we have an existing email)
        HomePage home = new HomePage(driver);
        home.open();
        Assert.assertTrue(home.isHomeVisible(), "Home page not visible");

        home.clickSignupLogin();
        SignupLoginPage signupPage = new SignupLoginPage(driver);
        Assert.assertTrue(signupPage.isNewUserSignupVisible(), "'New User Signup!' not visible");

        String firstName = "Existing";
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

        //Logout so we can test re-registration
        accountCreated.clickLogout();

        //Try to register again with the same email
        home.clickSignupLogin();
        Assert.assertTrue(signupPage.isNewUserSignupVisible(), "'New User Signup!' not visible");

        signupPage.enterNameAndEmail("DuplicateUser", email); // use same email
        signupPage.clickSignup();

        //Verify error
        Assert.assertTrue(signupPage.isEmailAlreadyExistsErrorVisible(), "'Email Address already exist!' error not visible");

        //Cleanup: log back in and delete the account
        signupPage.login(email, password);
        Assert.assertTrue(accountCreated.isLoggedInVisible(), "Login failed during cleanup");

        accountCreated.clickDeleteAccount();
        AccountDeletedPage deletedPage = new AccountDeletedPage(driver);
        Assert.assertTrue(deletedPage.isAccountDeletedVisible(), "'ACCOUNT DELETED!' not visible");
        deletedPage.clickContinue();
    }
}
