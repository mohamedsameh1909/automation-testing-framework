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

public class SubscriptionSection extends BasePage {

    @FindBy(xpath = "//h2[text()='Subscription']")
    private WebElement subscriptionTitle;

    @FindBy(id = "susbscribe_email")
    private WebElement subscriptionInput;

    @FindBy(id = "subscribe")
    private WebElement subscribeButton;

    @FindBy(xpath = "//div[contains(text(),'You have been successfully subscribed!')]")
    private WebElement successMessage;

    public SubscriptionSection(WebDriver driver) {
        super(driver);
    }

    public boolean isSubscriptionTitleVisible() {
        return subscriptionTitle.isDisplayed();
    }

    public void subscribe(String email) {
        subscriptionInput.clear();
        subscriptionInput.sendKeys(email);
        subscribeButton.click();
    }

    public boolean isSuccessMessageVisible() {
        return successMessage.isDisplayed();
    }
}
