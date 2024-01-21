package of.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import of.abstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

    WebDriver driver;

    @FindBy(css = "body>nav:nth-child(5)>div:nth-child(2)>div:nth-child(3)>ul:nth-child(1)>li:nth-child(6)>a:nth-child(1)")
    WebElement companyTab;

    @FindBy(linkText = "Careers")
    WebElement careersButton;

    public LandingPage(WebDriver driver) {

        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public void goTo() {

        driver.get("https://useinsider.com/");

    }

    public boolean checkTitle() {

        String expectedTitle = "#1 Leader in Individualized, Cross-Channel CX — Insider";
        String pageTitle = driver.getTitle();
        return pageTitle.equals(expectedTitle);

    }

    public CareersPage goToCareersPage() {

        companyTab.click();
        careersButton.click();
        return new CareersPage(driver);

    }
}