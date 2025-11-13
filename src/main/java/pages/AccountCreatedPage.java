/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pages;

/**
 *
 * @author HP
 */

import framework.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountCreatedPage extends BasePage {

    @FindBy(xpath = "//b[text()='Account Created!']")
    private WebElement accountCreatedText;

    @FindBy(xpath = "//a[@data-qa='continue-button']")
    private WebElement continueButton;

    @FindBy(xpath = "//a[contains(text(),'Logged in as')]")
    private WebElement loggedInText;

    @FindBy(xpath = "//a[contains(text(),'Delete Account')]")
    private WebElement deleteAccountLink;
    
    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    private WebElement logoutLink;

    public AccountCreatedPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAccountCreatedVisible() {
        return accountCreatedText.isDisplayed();
    }

    public void clickContinue() {
        continueButton.click();
    }

    public boolean isLoggedInVisible() {
        return loggedInText.isDisplayed();
    }

    public void clickDeleteAccount() {
        deleteAccountLink.click();
    }
    
    public void clickLogout() {
        logoutLink.click();
    }
}
