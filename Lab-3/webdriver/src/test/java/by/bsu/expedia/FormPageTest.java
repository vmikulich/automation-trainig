package by.bsu.expedia;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalTime;

public class FormPageTest {
    private WebDriver driver;
    private FormPage page;
    private final String pickUpPlace = "Minsk, Belarus (MSQ-Minsk Intl.)";
    private final String EmptyPickUpPlace = "";
    private final LocalDate pickUpDate = LocalDate.now();
    private final LocalDate dropOffDate = LocalDate.now().plusDays(1);
    private final LocalTime currentTime = LocalTime.NOON;

    @BeforeClass
    public void browserSetUp() {
        driver = new EdgeDriver();
//        driver.manage().window().maximize();
    }

    @Test
    public void pickUpTimeIsAfterTheCurrentTime() {
        page = new FormPage(driver)
                .openPage()
                .pickUpTimeIsAfterTheCurrentTime(pickUpPlace, pickUpDate, dropOffDate);
        Assert.assertTrue(page.isErrorMessgaeVisiable2(driver));
    }

    @Test
    public void searchWithEmptyPickUpField() {
        page = new FormPage(driver)
                .openPage()
                .searchWithEmptyPickUpField(EmptyPickUpPlace, pickUpDate);
        Assert.assertTrue(page.isErrorMessgaeVisiable1(driver));
    }

    @AfterClass
    public void browserTearDown() {
        if (driver != null) {
            driver.close();
            driver = null;
        }
    }
}
