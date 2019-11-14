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
import java.util.concurrent.TimeUnit;

public class FormPage extends AbstractPage{
    private final String PAGE_URL = "https://www.expedia.com/Cars";
    private final int WAIT_TIMEOUT_SECONDS = 40;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");


    public FormPage(WebDriver driver) {
        super(driver);
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    @Override
    public FormPage openPage() {
        webDriver.get(PAGE_URL);
        webDriver.manage().timeouts().implicitlyWait(WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        return this;
    }

    @FindBy(xpath = "//[@id=\"car-pickup-clp\"]")
    private WebElement pickUpPlace;

    @FindBy(xpath = "//[@id=\"car-dropoff-clp\"]")
    private WebElement dropOffPlace;

    @FindBy(xpath = "//[@id=\"car-pickup-date-clp\"]")
    private WebElement pickUpDate;

    @FindBy(xpath = "//[@id=\"car-dropoff-date-clp\"]")
    private WebElement dropOffDate;

    @FindBy(xpath = "//[@id=\"car-pickup-time-clp\"]")
    private WebElement pickUpTime;

    @FindBy(xpath = "//[@id=\"car-dropoff-time-clp\"]")
    private WebElement dropOffTime;

    @FindBy(xpath = "//[@id=\"gcw-submit-car\"]")
    private WebElement searchButton;

    private void searchCar() {
        searchButton.click();
    }

    private FormPage selectPickUpTime(LocalTime picTime) {
        new Select(pickUpTime).selectByVisibleText(picTime.format(timeFormatter));
        return this;
    }

    private FormPage selectDropOffTime(LocalTime dropTime) {
        new Select(dropOffTime).selectByVisibleText(dropTime.format(timeFormatter));
        return this;
    }

    private FormPage inputPickUpPlace(String place) {
        pickUpPlace.clear();
        pickUpPlace.sendKeys(place);
        return this;
    }

    private FormPage inputDropOffPlace(String place) {
        dropOffPlace.clear();
        dropOffPlace.sendKeys(place);
        return this;
    }

    private FormPage inputPickUpDate(LocalDate date) {
        pickUpDate.clear();
        pickUpDate.sendKeys(date.format(dateFormatter));
        return this;
    }

    private FormPage inputDropOffDate(LocalDate date) {
        dropOffDate.clear();
        dropOffDate.sendKeys(date.format(dateFormatter));
        return this;
    }

    public boolean isErrorMessgaeVisiable1(WebDriver driver) {
        WebElement errorMessage =
                new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                        .until(ExpectedConditions
                                .presenceOfElementLocated(By.xpath("//*[@id=\"gcw-cars-form-clp\"]/div[2]")));
        return errorMessage.isDisplayed();
    }

    public boolean isErrorMessgaeVisiable2(WebDriver driver) {
        WebElement errorMessage =
                new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                        .until(ExpectedConditions
                                .presenceOfElementLocated(By.xpath("//*[@id=\"wizard-errors\"]")));
        return errorMessage.isDisplayed();
    }

    public FormPage pickUpTimeIsAfterTheCurrentTime(String pickUpPlace, LocalDate pickUpDate, LocalDate dropOffDate) {
        inputPickUpPlace(pickUpPlace);
        inputPickUpDate(pickUpDate);
        inputDropOffDate(dropOffDate);
        searchCar();
        return this;
    }

    public FormPage searchWithEmptyPickUpField(String EmptyPickUpPlace, LocalDate pickUpDate) {
        inputPickUpPlace(EmptyPickUpPlace);
        inputPickUpDate(pickUpDate);
        inputDropOffDate(pickUpDate);
        searchCar();
        return this;
    }

}
