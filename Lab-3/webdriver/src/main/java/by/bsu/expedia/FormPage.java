package by.bsu.expedia;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class FormPage {
    private final String PAGE_URL = "https://www.expedia.com/Cars";
    private final int WAIT_TIMEOUT_SECONDS = 40;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");

    private WebDriver driver;

    public FormPage(WebDriver driver) {
        this.driver = driver;
        driver.get(PAGE_URL);
        PageFactory.initElements(this.driver, this);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
    }

    @FindBy(xpath = "//*[@id=\"car-pickup-clp\"]")
    private WebElement pickUpPlace;

    @FindBy(xpath = "//*[@id=\"car-dropoff-clp\"]")
    private WebElement dropOffPlace;

    @FindBy(xpath = "//*[@id=\"car-pickup-date-clp\"]")
    private WebElement pickUpDate;

    @FindBy(xpath = "//*[@id=\"car-dropoff-date-clp\"]")
    private WebElement dropOffDate;

    @FindBy(xpath = "//*[@id=\"car-pickup-time-clp\"]")
    private WebElement pickUpTime;

    @FindBy(xpath = "//*[@id=\"car-dropoff-time-clp\"]")
    private WebElement dropOffTime;

    @FindBy(xpath = "//*[@id=\"gcw-submit-car\"]")
    private WebElement searchButton;

    public void searchCar() {
        searchButton.click();
    }

    public void selectPickUpTime(LocalTime picTime) {
        new Select(pickUpTime).selectByVisibleText(picTime.format(timeFormatter));
    }

    public void selectDropOffTime(LocalTime dropTime) {
        new Select(dropOffTime).selectByVisibleText(dropTime.format(timeFormatter));
    }

    public void inputPickUpPlace(String place) {
        pickUpPlace.clear();
        pickUpPlace.sendKeys(place);
    }

    public void inputDropOffPlace(String place) {
        dropOffPlace.clear();
        dropOffPlace.sendKeys(place);
    }

    public void inputPickUpDate(LocalDate date) {
        pickUpDate.clear();
        pickUpDate.sendKeys(date.format(dateFormatter));
    }

    public void inputDropOffDate(LocalDate date) {
        dropOffDate.clear();
        dropOffDate.sendKeys(date.format(dateFormatter));
    }

    public boolean isErrorMessgaeVisiable1() {
        WebElement errorMessage =
                new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                        .until(ExpectedConditions
                                .presenceOfElementLocated(By.xpath("//*[@id=\"gcw-cars-form-clp\"]/div[2]")));
        return errorMessage.isDisplayed();
    }

    public boolean isErrorMessgaeVisiable2() {
        WebElement errorMessage =
                new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                        .until(ExpectedConditions
                                .presenceOfElementLocated(By.xpath("//*[@id=\"wizard-errors\"]")));
        return errorMessage.isDisplayed();
    }

}
