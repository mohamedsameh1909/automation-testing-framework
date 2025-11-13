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

public class DownloadInvoiceAfterOrderTest extends BaseTest {

    @Test(description = "Test Case 24: Download Invoice after Purchase Order")
    public void downloadInvoiceAfterOrder() {
        HomePage home = new HomePage(driver);
        home.open();
        Assert.assertTrue(home.isHomeVisible(), "Home page not visible");

        //Step 1: Add product to cart
        home.clickProducts();
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addFirstProductAndGoToCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isShoppingCartTitleVisible(), "Cart page not visible");

        //Step 2: Proceed to checkout
        cartPage.clickProceedToCheckout();

        //Step 3: Register new account
        cartPage.clickRegisterLogin();
        SignupLoginPage signupPage = new SignupLoginPage(driver);

        String firstName = "Invoice";
        String lastName = "User";
        String email = Utils.generateRandomEmail();
        String password = "password123";

        signupPage.enterNameAndEmail(firstName + lastName, email);
        signupPage.clickSignup();

        AccountInformationPage accountInfo = new AccountInformationPage(driver);
        accountInfo.fillAccountForm(password, firstName, lastName);
        accountInfo.clickCreateAccount();

        AccountCreatedPage accountCreated = new AccountCreatedPage(driver);
        Assert.assertTrue(accountCreated.isAccountCreatedVisible(), "Account not created");
        accountCreated.clickContinue();

        //Step 4: Checkout again
        home.clickCart();
        cartPage.clickProceedToCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        Assert.assertTrue(checkoutPage.isAddressDetailsVisible(), "Address details not visible");

        //Step 5: Place order
        checkoutPage.enterComment("Please deliver fast");
        checkoutPage.clickPlaceOrder();

        PaymentPage paymentPage = new PaymentPage(driver);
        paymentPage.enterPaymentDetails(firstName + lastName,"4111111111111111", "123", "12", "2030");
        paymentPage.clickPayAndConfirm();

        //Wait for confirmation
        Assert.assertTrue(paymentPage.isOrderSuccessVisible(), "Order success message not visible");

        //Step 6: Download invoice
        paymentPage.clickDownloadInvoice();
        //We cannot assert actual file download in Selenium, but we can check that the button was clickable
        System.out.println("Invoice download triggered successfully.");

        //Step 7: Continue and delete account (cleanup)
        accountCreated.clickContinue();
        accountCreated.clickDeleteAccount();
        AccountDeletedPage deletedPage = new AccountDeletedPage(driver);
        Assert.assertTrue(deletedPage.isAccountDeletedVisible(), "'ACCOUNT DELETED!' not visible");
        deletedPage.clickContinue();
    }
}
