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

public class PlaceOrderRegisterBeforeCheckoutTest extends BaseTest {

    @Test(description = "Test Case 15: Place Order: Register before Checkout")
    public void placeOrderRegisterBeforeCheckout() {
        HomePage home = new HomePage(driver);
        home.open();
        Assert.assertTrue(home.isHomeVisible(), "Home page not visible");

        //Step 1: Register new account
        home.clickSignupLogin();

        SignupLoginPage signupPage = new SignupLoginPage(driver);
        Assert.assertTrue(signupPage.isNewUserSignupVisible(), "Signup page not visible");

        String firstName = "PreCheckout";
        String lastName = "User";
        String email = Utils.generateRandomEmail();

        signupPage.enterNameAndEmail(firstName + lastName, email);
        signupPage.clickSignup();

        AccountInformationPage accountInfo = new AccountInformationPage(driver);
        Assert.assertTrue(accountInfo.isEnterAccountInfoVisible(), "Account Info page not visible");

        accountInfo.fillAccountForm(firstName, lastName, "password123");
        accountInfo.clickCreateAccount();

        AccountCreatedPage accountCreated = new AccountCreatedPage(driver);
        Assert.assertTrue(accountCreated.isAccountCreatedVisible(), "Account not created");
        accountCreated.clickContinue();
        Assert.assertTrue(accountCreated.isLoggedInVisible(), "User not logged in after account creation");

        //Step 2: Add product to cart
        home.clickProducts();
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addFirstProductAndGoToCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isShoppingCartTitleVisible(), "Cart page not visible");

        //Step 3: Proceed to checkout
        cartPage.clickProceedToCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        Assert.assertTrue(checkoutPage.isAddressDetailsVisible(), "Address details not visible");
        Assert.assertTrue(checkoutPage.isReviewOrderVisible(), "Review order not visible");

        checkoutPage.enterComment("Leave at the front door if not home");
        checkoutPage.clickPlaceOrder();

        //Step 4: Payment
        PaymentPage paymentPage = new PaymentPage(driver);
        paymentPage.enterPaymentDetails("PreCheckout User", "4111111111111111", "123", "12", "2026");
        paymentPage.clickPayAndConfirm();

        Assert.assertTrue(paymentPage.waitForOrderPlaced(), "Final order placed confirmation not visible");

        //Step 5: Delete account
        accountCreated.clickDeleteAccount();
        AccountDeletedPage deletedPage = new AccountDeletedPage(driver);
        Assert.assertTrue(deletedPage.isAccountDeletedVisible(), "'ACCOUNT DELETED!' not visible");
        deletedPage.clickContinue();
    }
}
