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

public class SignupLoginPage extends BasePage {

    @FindBy(css = "div:nth-child(3) > div > h2")
    private WebElement newUserSignupText;

    @FindBy(css = "div.signup-form > form > input[type=text]:nth-child(2)")
    private WebElement signupNameInput;

    @FindBy(css = "div.signup-form > form > input[type=email]:nth-child(3)")
    private WebElement signupEmailInput;

    @FindBy(css = "div.signup-form > form > button")
    private WebElement signupButton;
    
    @FindBy(css = "div.login-form > h2")
    private WebElement loginToYourAccountText;

    @FindBy(xpath = "//input[@data-qa='login-email']")
    private WebElement loginEmailInput;

    @FindBy(xpath = "//input[@data-qa='login-password']")
    private WebElement loginPasswordInput;

    @FindBy(css = "div.login-form > form > button")
    private WebElement loginButton;
    
    @FindBy(xpath = "//p[contains(text(),'Your email or password is incorrect!')]")
    private WebElement loginErrorMessage;
    
    @FindBy(xpath = "//p[contains(text(),'Email Address already exist!')]")
    private WebElement emailAlreadyExistsError;

    public SignupLoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isNewUserSignupVisible() {
        return newUserSignupText.isDisplayed();
    }

    public void enterNameAndEmail(String name, String email) {
        signupNameInput.sendKeys(name);
        signupEmailInput.sendKeys(email);
    }

    public void clickSignup() {
        signupButton.click();
    }
    
    public boolean isLoginToYourAccountVisible() {
        return loginToYourAccountText.isDisplayed();
    }

    public void login(String email, String password) {
        loginEmailInput.sendKeys(email);
        loginPasswordInput.sendKeys(password);
        loginButton.click();
    }
    
    public boolean isLoginErrorVisible() {
        return loginErrorMessage.isDisplayed();
    }
    
    public boolean isEmailAlreadyExistsErrorVisible() {
        return emailAlreadyExistsError.isDisplayed();
    }
}
