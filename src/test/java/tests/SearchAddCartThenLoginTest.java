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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import pages.AccountCreatedPage;
import pages.AccountDeletedPage;
import pages.AccountInformationPage;
import pages.CartPage;
import pages.HomePage;
import pages.ProductsPage;
import pages.SignupLoginPage;

public class SearchAddCartThenLoginTest extends BaseTest {

    @Test(description = "Search products as guest, add them to cart, verify cart, login and verify cart persists")
    public void searchAddCartThenLogin() {
        HomePage home = new HomePage(driver);
        home.open();
        Assert.assertTrue(home.isHomeVisible(), "Home page not visible");

        //0) Create an account to use later, then logout (we need credentials to login later)
        home.clickSignupLogin();
        SignupLoginPage signup = new SignupLoginPage(driver);
        String firstName = "Persist";
        String lastName = "User";
        String email = Utils.generateRandomEmail();
        String password = "password123";

        signup.enterNameAndEmail(firstName + lastName, email);
        signup.clickSignup();

        AccountInformationPage accountInfo = new AccountInformationPage(driver);
        Assert.assertTrue(accountInfo.isEnterAccountInfoVisible(), "Account info page not visible");
        accountInfo.fillAccountForm(firstName, lastName, password);
        accountInfo.clickCreateAccount();

        AccountCreatedPage accountCreated = new AccountCreatedPage(driver);
        Assert.assertTrue(accountCreated.isAccountCreatedVisible(), "Account not created");
        accountCreated.clickContinue();
        Assert.assertTrue(accountCreated.isLoggedInVisible(), "User not logged in after create");

        //logout so we act as guest
        home.clickLogout();

        //1) Search products
        home.clickProducts();
        ProductsPage products = new ProductsPage(driver);
        //(use a common search phrase; adjust if needed)
        products.searchProduct("Dress");

        //2) Add all searched products to cart (handles modal). returns count
        int addedCount = products.addAllSearchedProductsAndGoToCart();

        //3) Verify cart page is displayed & contains those products
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.urlContains("view_cart"));
        CartPage cart = new CartPage(driver);
        Assert.assertTrue(cart.isShoppingCartTitleVisible(), "Cart page not visible after adding products");
        Assert.assertTrue(cart.hasAtLeastNItems(addedCount),
                "Cart item count (" + cart.getCartItemsCount() + ") is less than expected added (" + addedCount + ")");

        //4) Click Signup / Login and login with the same account
        home.clickSignupLogin();
        SignupLoginPage loginPage = new SignupLoginPage(driver);
        Assert.assertTrue(loginPage.isLoginToYourAccountVisible(), "Login section not visible");
        loginPage.login(email, password);

        //5) Go to Cart page again
        home.clickCart();
        wait.until(ExpectedConditions.urlContains("view_cart"));
        CartPage cartAfterLogin = new CartPage(driver);

        //6) Verify products are still visible in cart after login
        Assert.assertTrue(cartAfterLogin.hasAtLeastNItems(addedCount),
                "Cart did not persist items after login. before=" + addedCount + ", after=" + cartAfterLogin.getCartItemsCount());

        //Cleanup: delete account
        AccountCreatedPage accountPage = new AccountCreatedPage(driver);
        accountPage.clickDeleteAccount();
        AccountDeletedPage deleted = new AccountDeletedPage(driver);
        Assert.assertTrue(deleted.isAccountDeletedVisible(), "'ACCOUNT DELETED!' not visible");
        deleted.clickContinue();
    }
}
