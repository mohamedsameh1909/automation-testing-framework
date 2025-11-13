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

public class PlaceOrderRegisterWhileCheckoutTest extends BaseTest {

    @Test(description = "Test Case 14: Place Order: Register while Checkout")
    public void placeOrderRegisterWhileCheckout() {
        HomePage home = new HomePage(driver);
        home.open();
        Assert.assertTrue(home.isHomeVisible(), "Home page not visible");

        //Step 1: Add product to cart safely
        ProductsPage productsPage = new ProductsPage(driver);
        home.clickProducts();
        productsPage.addFirstProductAndGoToCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isShoppingCartTitleVisible(), "Cart page not visible");

        //Step 2: Proceed to checkout -> Register
        cartPage.clickProceedToCheckout();
        cartPage.clickRegisterLogin();

        SignupLoginPage signupPage = new SignupLoginPage(driver);
        Assert.assertTrue(signupPage.isNewUserSignupVisible(), "Signup page not visible");

        String firstName = "Order";
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

        //Step 3: Back to cart and proceed to checkout
        home.clickCart();
        cartPage.clickProceedToCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        Assert.assertTrue(checkoutPage.isAddressDetailsVisible(), "Address details not visible");
        Assert.assertTrue(checkoutPage.isReviewOrderVisible(), "Review order not visible");

        checkoutPage.enterComment("Please deliver between 9am - 5pm");
        checkoutPage.clickPlaceOrder();

        //Step 4: Enter payment details
        PaymentPage paymentPage = new PaymentPage(driver);
        paymentPage.enterPaymentDetails("Checkout User", "4111111111111111", "123", "12", "2026");
        paymentPage.clickPayAndConfirm();
        Assert.assertTrue(paymentPage.waitForOrderPlaced(), "Final order placed confirmation not visible");

        
        // Step 5: Delete account after order
        accountCreated.clickDeleteAccount();

        AccountDeletedPage deletedPage = new AccountDeletedPage(driver);
        Assert.assertTrue(deletedPage.isAccountDeletedVisible(), "'ACCOUNT DELETED!' not visible");
        deletedPage.clickContinue();
    }
}
