package of.pageObjects;

import of.abstractComponents.AbstractComponent;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CareersPage extends AbstractComponent {

    WebDriver driver;

    @FindBy(xpath = "//section[@id='career-find-our-calling']//div[@class='row']")
    WebElement teams;

    @FindBy(xpath = "(//div[@class='col-12 d-flex flex-wrap'])[1]")
    WebElement locations;

    @FindBy(xpath = "(//h2[normalize-space()='Life at Insider'])[1]")
    WebElement lifeInsider;

    public CareersPage(WebDriver driver) {

        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public boolean checkTitle() {

        String expectedTitle = "Ready to disrupt? | Insider Careers";
        String pageTitle = driver.getTitle();
        return pageTitle.equals(expectedTitle);

    }

    public void checkElementPresence() {

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", teams);
        waitForWebElementToAppear(teams);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", locations);
        waitForWebElementToAppear(locations);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", lifeInsider);
        waitForWebElementToAppear(lifeInsider);

    }

}
