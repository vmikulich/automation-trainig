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
    private final LocalTime currentTime = LocalTime.NOON;

    @BeforeClass
    public void browserSetUp() {
        driver = new EdgeDriver();
        page = new FormPage(driver);
        driver.manage().window().maximize();
    }

    @Test
    public void pickUpTimeIsAfterTheCurrentTime() {
        page.inputPickUpPlace(pickUpPlace);
        page.inputPickUpDate(pickUpDate);
        page.inputDropOffDate(pickUpDate);
//        page.selectPickUpTime(currentTime.plusHours(2));
//        page.selectDropOffTime(currentTime.plusHours(2));
        page.searchCar();
        Assert.assertTrue(page.isErrorMessgaeVisiable2());
    }

    @Test
    public void searchWithEmptyPickUpField() {
        page.inputPickUpPlace(EmptyPickUpPlace);
        page.inputPickUpDate(pickUpDate);
        page.inputDropOffDate(pickUpDate);
        page.searchCar();
        Assert.assertTrue(page.isErrorMessgaeVisiable1());
    }

    @AfterClass
    public void browserTearDown() {
        if (driver != null) {
            driver.close();
            driver = null;
        }
    }
}
