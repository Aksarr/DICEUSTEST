package of.pageObjects;

import of.abstractComponents.AbstractComponent;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class QAPage extends AbstractComponent {

    WebDriver driver;

    @FindBy(linkText = "See all QA jobs")
    WebElement allQaJobs;

    @FindBy(linkText = "Only Necessary")
    WebElement cookies;

    @FindBy(id = "select2-filter-by-location-container")
    WebElement selectLocation;

    @FindBy(id = "jobs-list")
    WebElement jobsList;

    @FindBy(css = ".position-location.text-large")
    List<WebElement> locations;

    @FindBy(css = ".position-department.text-large.font-weight-600.text-primary")
    List<WebElement> departments;

    @FindBy(xpath = "(//a[@class='btn btn-navy rounded pt-2 pr-5 pb-2 pl-5'][normalize-space()='View Role'])[1]")
    WebElement link;

    public QAPage(WebDriver driver) {

        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public void goTo() {

        driver.get("https://useinsider.com/careers/quality-assurance/");
        cookies.click();
        allQaJobs.click();

    }

    public void selectFilters() throws InterruptedException {

        Thread.sleep(3000);
        Actions a = new Actions(driver);
        a.moveToElement(selectLocation).click().build().perform();
        Thread.sleep(3000);
        a.moveToElement(selectLocation).keyDown(Keys.ARROW_DOWN).keyDown(Keys.ENTER).build().perform();
        Thread.sleep(3000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", jobsList);
        waitForWebElementToAppear(jobsList);

    }

    public Boolean verifyLocation(String locationName) {

        Boolean match = locations.stream().anyMatch(location -> location.getAttribute("innerHTML").equalsIgnoreCase(locationName));
        return match;

    }

    public Boolean verifyDepartment(String departmentName) {

        Boolean match = departments.stream().anyMatch(location -> location.getAttribute("innerHTML").equalsIgnoreCase(departmentName));
        return match;

    }

    public boolean verifyRedirection() throws InterruptedException {

        String expectedLink = "jobs.lever.co";
        Actions a = new Actions(driver);
        a.moveToElement(link).click().build().perform();
        Thread.sleep(3000);
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> id = windows.iterator();
        String parentWindow = id.next();
        String childWindow = id.next();
        driver.switchTo().window(childWindow);
        String pageLink = driver.getCurrentUrl();
        return pageLink.contains(expectedLink);

    }
}