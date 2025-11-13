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

public class BrandPage extends BasePage {

    @FindBy(xpath = "//h2[@class='title text-center']")
    private WebElement brandTitle;

    public BrandPage(WebDriver driver) {
        super(driver);
    }

    public String getBrandTitle() {
        return brandTitle.getText();
    }
}
