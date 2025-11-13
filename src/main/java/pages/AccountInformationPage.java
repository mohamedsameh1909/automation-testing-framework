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
import org.openqa.selenium.support.ui.Select;

public class AccountInformationPage extends BasePage {

    @FindBy(css = "div.login-form > h2 > b")
    private WebElement enterAccountInfoText;

    @FindBy(id = "id_gender1")
    private WebElement mrTitleRadio;
    
    @FindBy(id = "id_gender2")
    private WebElement mrsTitleRadio;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "days")
    private WebElement dayDropdown;

    @FindBy(id = "months")
    private WebElement monthDropdown;

    @FindBy(id = "years")
    private WebElement yearDropdown;

    @FindBy(id = "newsletter")
    private WebElement newsletterCheckbox;

    @FindBy(id = "optin")
    private WebElement offersCheckbox;

    @FindBy(id = "first_name")
    private WebElement firstNameInput;

    @FindBy(id = "last_name")
    private WebElement lastNameInput;

    @FindBy(id = "company")
    private WebElement companyInput;

    @FindBy(id = "address1")
    private WebElement address1Input;

    @FindBy(id = "address2")
    private WebElement address2Input;

    @FindBy(id = "country")
    private WebElement countryDropdown;

    @FindBy(id = "state")
    private WebElement stateInput;

    @FindBy(id = "city")
    private WebElement cityInput;

    @FindBy(id = "zipcode")
    private WebElement zipcodeInput;

    @FindBy(id = "mobile_number")
    private WebElement mobileNumberInput;

    @FindBy(css = "div.login-form > form > button")
    private WebElement createAccountButton;

    public AccountInformationPage(WebDriver driver) {
        super(driver);
    }

    public boolean isEnterAccountInfoVisible() {
        return enterAccountInfoText.isDisplayed();
    }

    public void fillAccountForm(String firstName, String lastName, String password) {
        mrTitleRadio.click();
        passwordInput.sendKeys(password);

        new Select(dayDropdown).selectByValue("10");
        new Select(monthDropdown).selectByValue("5");
        new Select(yearDropdown).selectByValue("1995");

        newsletterCheckbox.click();
        offersCheckbox.click();

        this.firstNameInput.sendKeys(firstName);
        this.lastNameInput.sendKeys(lastName);
        companyInput.sendKeys("TestCompany");
        address1Input.sendKeys("123 Street");
        address2Input.sendKeys("Apt 4B");
        new Select(countryDropdown).selectByVisibleText("Canada");
        stateInput.sendKeys("Ontario");
        cityInput.sendKeys("Toronto");
        zipcodeInput.sendKeys("12345");
        mobileNumberInput.sendKeys("1234567890");
    }

    public void clickCreateAccount() {
        createAccountButton.click();
    }
}
