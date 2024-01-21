package of.tests;

import of.pageObjects.CareersPage;
import of.pageObjects.LandingPage;
import of.pageObjects.QAPage;
import of.testComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

// 1st test - home page open?
public class TestTasks extends BaseTest {

    @Test
    public void firstTest() {

        landingPage = new LandingPage(driver);
        landingPage.goTo();
        Assert.assertTrue(landingPage.checkTitle());
        CareersPage careersPage = landingPage.goToCareersPage();
        Assert.assertTrue(careersPage.checkTitle());
        careersPage.checkElementPresence();

    }

    @Test
    public void secondTest() throws InterruptedException {

        String location = "Istanbul, Turkey";
        String department = "Quality Assurance";
        qaPage = new QAPage(driver);
        qaPage.goTo();
        qaPage.selectFilters();
        Thread.sleep(3000);
        Boolean matchLocation = qaPage.verifyLocation(location);
        Assert.assertTrue(matchLocation);
        Boolean matchDepartment = qaPage.verifyDepartment(department);
        Assert.assertTrue(matchDepartment);
        Assert.assertTrue(qaPage.verifyRedirection());

    }

}