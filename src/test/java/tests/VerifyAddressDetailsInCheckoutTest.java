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
import pages.ProductsPage;
import pages.SignupLoginPage;

public class VerifyAddressDetailsInCheckoutTest extends BaseTest {

    @Test(description = "Test Case 23: Verify Address Details in Checkout Page")
    public void verifyAddressDetailsInCheckout() {
        HomePage home = new HomePage(driver);
        home.open();
        Assert.assertTrue(home.isHomeVisible(), "Home page not visible");

        //Step 1: Register a new account
        home.clickSignupLogin();
        SignupLoginPage signupPage = new SignupLoginPage(driver);

        String firstName = "Address";
        String lastName = "User";
        String email = Utils.generateRandomEmail();
        String password = "password123";

        signupPage.enterNameAndEmail(firstName + lastName, email);
        signupPage.clickSignup();

        AccountInformationPage accountInfo = new AccountInformationPage(driver);
        accountInfo.fillAccountForm(firstName, lastName, password);
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
        Assert.assertTrue(checkoutPage.isAddressDetailsVisible(), "Address details section not visible");

        //Step 4: Verify delivery and billing addresses
        String deliveryAddress = checkoutPage.getDeliveryAddressText();
        String billingAddress = checkoutPage.getBillingAddressText();

        System.out.println("Delivery Address:\n" + deliveryAddress);
        System.out.println("Billing Address:\n" + billingAddress);

        //Split both addresses into lines
        String[] deliveryLines = deliveryAddress.split("\\r?\\n");
        String[] billingLines = billingAddress.split("\\r?\\n");

        //Remove the first line (titles differ: "YOUR DELIVERY ADDRESS" vs "YOUR BILLING ADDRESS")
        String deliveryContent = String.join("\n", java.util.Arrays.copyOfRange(deliveryLines, 1, deliveryLines.length));
        String billingContent = String.join("\n", java.util.Arrays.copyOfRange(billingLines, 1, billingLines.length));

        //Debug print (optional)
        System.out.println("Delivery Content:\n" + deliveryContent);
        System.out.println("Billing Content:\n" + billingContent);

        //Assertions
        Assert.assertTrue(deliveryContent.contains(firstName), "Delivery address missing first name");
        Assert.assertTrue(deliveryContent.contains(lastName), "Delivery address missing last name");
        Assert.assertEquals(deliveryContent, billingContent, "Delivery and Billing address details do not match");


        //Step 5: Cleanup (Delete Account)
        accountCreated.clickDeleteAccount();
        AccountDeletedPage deletedPage = new AccountDeletedPage(driver);
        Assert.assertTrue(deletedPage.isAccountDeletedVisible(), "'ACCOUNT DELETED!' not visible");
        deletedPage.clickContinue();
    }
}