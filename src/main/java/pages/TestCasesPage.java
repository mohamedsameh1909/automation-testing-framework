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

public class TestCasesPage extends BasePage {

    @FindBy(xpath = "//b[contains(text(),'Test Cases')]")
    private WebElement testCasesTitle;

    public TestCasesPage(WebDriver driver) {
        super(driver);
    }

    public boolean isTestCasesTitleVisible() {
        return testCasesTitle.isDisplayed();
    }
}
