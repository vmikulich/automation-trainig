package by.bsu.expedia.page;

import by.bsu.expedia.model.CarReservation;
import by.bsu.expedia.model.PageError;
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
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FormPage extends AbstractPage{
    private final String PAGE_URL = "https://www.expedia.com/Cars";
    private static final Logger LOGGER = LogManager.getRootLogger();
    private final WebDriverWait wait;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");


    public FormPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
        wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
    }

    public FormPage openPage() {
        driver.navigate().to(PAGE_URL);
        LOGGER.info("Home page opened");
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

    @FindBy(xpath = "[@id=\"wizard-errors\"]/div/ul/li/a")
    private WebElement pickUpTimeError;

    @FindBy(xpath = "[@id=\"gcw-cars-form-clp\"]/div[2]/div/ul/li/a")
    private WebElement emptyPickUpPlaceFieldError;

    @FindBy(xpath = "[@id=\"gcw-cars-form-clp\"]/div[2]/div/ul/li[2]/a")
    private WebElement emptyPickUpDateFieldError;

    @FindBy(xpath = "[@id=\"gcw-cars-form-clp\"]/div[2]/div/ul/li[3]/a")
    private WebElement emptyDropOffDateFieldError;

    @FindBy(xpath = "[@id=\"gcw-cars-form-clp\"]/div[2]/div/ul/li/a")
    private WebElement equalityOfPickupAndDropOffTimeError;

    @FindBy(xpath = "[@id=\"ember906\"]/div/h5")
    private WebElement InexistentPickUpPlaceError;

    @FindBy(xpath = "[@id=\"ember906\"]/div/h5")
    private WebElement PickUpAndReturnPlacesAcrossContinentsError;


    public void search() {
        searchButton.click();
        LOGGER.info("Searching...");
    }

    public FormPage fillFromParams(CarReservation params) {
        params.getPlaceFrom().ifPresent(this::inputPickUpPlace);
        params.getPlaceTo().ifPresent(this::inputDropOffPlace);
        params.getReservationDates().ifPresent(e -> {
            e.getPickUpDate().ifPresent(this::inputPickUpDate);
            e.getReturnDate().ifPresent(this::inputDropOffDate);
        });
        LOGGER.info("Filled cars search params");
        return this;
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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        LOGGER.info("Filled 'Pick-up place' field with " + place);
        focusAway();
        return this;
    }

    private FormPage inputDropOffPlace(String place) {
        dropOffPlace.clear();
        dropOffPlace.sendKeys(place);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        LOGGER.info("Filled 'Drop-off place' field with " + place);
        focusAway();
        return this;
    }

    private FormPage inputPickUpDate(String date) {
        pickUpDate.clear();
//        pickUpDate.sendKeys(date.format(dateFormatter));
        pickUpDate.sendKeys(date);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        LOGGER.info("Filled 'Pick-up date' field with " + date);
        focusAway();
        return this;
    }

    private FormPage inputDropOffDate(String date) {
        dropOffDate.clear();
        dropOffDate.sendKeys(date);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        LOGGER.info("Filled 'Pick-up date' field with " + date);
        focusAway();
        return this;
    }

    public boolean checkEmptyPickUpPlaceFieldErrorMessage(PageError error) {
        return emptyPickUpPlaceFieldError.isDisplayed()
                && emptyPickUpPlaceFieldError.getText().
                contains(error.getErrorDescription());
    }

    public boolean checkEmptyPickUpDateFieldErrorMessage(PageError error) {
        return emptyPickUpDateFieldError.isDisplayed()
                && emptyPickUpDateFieldError.getText().
                contains(error.getErrorDescription());
    }

    public boolean checkEmptyDropOffDateFieldErrorMessage(PageError error) {
        return emptyDropOffDateFieldError.isDisplayed()
                && emptyDropOffDateFieldError.getText().
                contains(error.getErrorDescription());
    }

    public boolean checkPickUpTimeErrorMessage(PageError error) {
        return pickUpTimeError.isDisplayed()
                && pickUpTimeError.getText().
                contains(error.getErrorDescription());
    }

    public boolean checkEqualityOfPickupAndDropOffTimeErrorMessage(PageError error) {
        return equalityOfPickupAndDropOffTimeError.isDisplayed()
                && equalityOfPickupAndDropOffTimeError.getText().
                contains(error.getErrorDescription());
    }

    public boolean checkInexistentPickUpPlaceErrorMessage(PageError error) {
        return InexistentPickUpPlaceError.isDisplayed()
                && InexistentPickUpPlaceError.getText().
                contains(error.getErrorDescription());
    }

    public boolean checkPickUpAndReturnPlacesAcrossContinentsErrorMessage(PageError error) {
        return PickUpAndReturnPlacesAcrossContinentsError.isDisplayed()
                && PickUpAndReturnPlacesAcrossContinentsError.getText().
                contains(error.getErrorDescription());
    }




//    public boolean isErrorMessgaeVisiable1(WebDriver driver) {
//        WebElement errorMessage =
//                new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
//                        .until(ExpectedConditions
//                                .presenceOfElementLocated(By.xpath("//*[@id=\"gcw-cars-form-clp\"]/div[2]")));
//        return errorMessage.isDisplayed();
//    }
//
//    public boolean isErrorMessgaeVisiable2(WebDriver driver) {
//        WebElement errorMessage =
//                new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
//                        .until(ExpectedConditions
//                                .presenceOfElementLocated(By.xpath("//*[@id=\"wizard-errors\"]")));
//        return errorMessage.isDisplayed();
//    }

//    public FormPage pickUpTimeIsAfterTheCurrentTime(String pickUpPlace, LocalDate pickUpDate, LocalDate dropOffDate) {
//        inputPickUpPlace(pickUpPlace);
//        inputPickUpDate(pickUpDate);
//        inputDropOffDate(dropOffDate);
//        searchCar();
//        return this;
//    }
//
//    public FormPage searchWithEmptyPickUpField(String EmptyPickUpPlace, LocalDate pickUpDate) {
//        inputPickUpPlace(EmptyPickUpPlace);
//        inputPickUpDate(pickUpDate);
//        inputDropOffDate(pickUpDate);
//        searchCar();
//        return this;
//    }

}
