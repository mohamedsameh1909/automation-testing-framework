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
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.PaymentPage;
import pages.ProductsPage;
import pages.SignupLoginPage;

public class PlaceOrderLoginBeforeCheckoutTest extends BaseTest {

    @Test(description = "Test Case 16: Place Order: Login before Checkout")
    public void placeOrderLoginBeforeCheckout() {
        HomePage home = new HomePage(driver);
        home.open();
        Assert.assertTrue(home.isHomeVisible(), "Home page not visible");

        //Step 1: Register a fresh account to use for login
        home.clickSignupLogin();

        SignupLoginPage signupPage = new SignupLoginPage(driver);
        String firstName = "LoginBefore";
        String lastName = "User";
        String email = Utils.generateRandomEmail();

        signupPage.enterNameAndEmail(firstName + lastName, email);
        signupPage.clickSignup();

        AccountInformationPage accountInfo = new AccountInformationPage(driver);
        accountInfo.fillAccountForm(firstName, lastName, "password123");
        accountInfo.clickCreateAccount();

        AccountCreatedPage accountCreated = new AccountCreatedPage(driver);
        Assert.assertTrue(accountCreated.isAccountCreatedVisible(), "Account not created");
        accountCreated.clickContinue();
        Assert.assertTrue(accountCreated.isLoggedInVisible(), "User not logged in after account creation");

        //Step 2: Logout to simulate login flow
        home.clickLogout();

        //Step 3: Login with same account
        home.clickSignupLogin();
        signupPage.login(email, "password123");
        Assert.assertTrue(accountCreated.isLoggedInVisible(), "User not logged in after login");

        //Step 4: Add product to cart
        home.clickProducts();
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addFirstProductAndGoToCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isShoppingCartTitleVisible(), "Cart page not visible");

        //Step 5: Checkout
        cartPage.clickProceedToCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        Assert.assertTrue(checkoutPage.isAddressDetailsVisible(), "Address details not visible");
        Assert.assertTrue(checkoutPage.isReviewOrderVisible(), "Review order not visible");

        checkoutPage.enterComment("Ring the bell on arrival");
        checkoutPage.clickPlaceOrder();

        //Step 6: Payment
        PaymentPage paymentPage = new PaymentPage(driver);
        paymentPage.enterPaymentDetails("LoginBefore User", "4111111111111111", "123", "12", "2026");
        paymentPage.clickPayAndConfirm();

        Assert.assertTrue(paymentPage.waitForOrderPlaced(), "Final order placed confirmation not visible");

        //Step 7: Delete account
        accountCreated.clickDeleteAccount();
        AccountDeletedPage deletedPage = new AccountDeletedPage(driver);
        Assert.assertTrue(deletedPage.isAccountDeletedVisible(), "'ACCOUNT DELETED!' not visible");
        deletedPage.clickContinue();
    }
}
