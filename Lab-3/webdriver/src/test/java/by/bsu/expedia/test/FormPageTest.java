package by.bsu.expedia.test;

import by.bsu.expedia.model.Account;
import by.bsu.expedia.model.PageError;
import by.bsu.expedia.page.FormPage;
import by.bsu.expedia.service.AccountCreator;
import by.bsu.expedia.service.CarReservationCreator;
import by.bsu.expedia.service.PageErrorCreator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FormPageTest extends CommonConditions {
    @Test(testName = "testcase 1: Search car with empty pick-up field")
    public void searchCarsForEmptyPickUpPlaceTest() {
        FormPage page = new FormPage(driver);
        page.openPage().fillFromParams(CarReservationCreator.withEmptyPickUpField()).search();
        PageError expectedError = PageErrorCreator.errorForEmptyPickUpFieldFromProperty();
        Assert.assertTrue(page.checkEmptyPickUpPlaceFieldErrorMessage(expectedError));
    }

    @Test(testName = "testcase 2: Search car with empty fields")
    public void searchCarsWithEmptyFieldsTest() {
        FormPage page = new FormPage(driver);
        page.openPage().fillFromParams(CarReservationCreator.withEmptyFields()).search();
        PageError expectedErrorForPickUpPlace = PageErrorCreator.errorForEmptyPickUpFieldFromProperty();
        Assert.assertTrue(page.checkEmptyPickUpPlaceFieldErrorMessage(expectedErrorForPickUpPlace));

        PageError expectedErrorForPickUpDate = PageErrorCreator.errorForEmptyPickUpDateFieldFromProperty();
        Assert.assertTrue(page.checkEmptyPickUpDateFieldErrorMessage(expectedErrorForPickUpDate));

        PageError expectedErrorForDropOffDate = PageErrorCreator.errorForEmptyDropOffDateFieldFromProperty();
        Assert.assertTrue(page.checkEmptyDropOffDateFieldErrorMessage(expectedErrorForDropOffDate));
    }

    @Test(testName = "testcase 3: Search car at not available place")
    public void searchCarsAtNotAvailablePlaceTest() {
        FormPage page = new FormPage(driver);
        page.openPage().fillFromParams(CarReservationCreator.withNotAvailablePlaceFromProperty()).search();
        PageError expectedError = PageErrorCreator.errorForNotAvailablePlaceFromProperty();
        Assert.assertTrue(page.checkInexistentPickUpPlaceErrorMessage(expectedError));
    }

    @Test(testName = "testcase 4: Search car for past pick-up date")
    public void searchCarsForPastPickUpDateTest() {
        FormPage page = new FormPage(driver);
        page.openPage().fillFromParams(CarReservationCreator.withPastPickUpDateFromProperty()).search();
        PageError expectedError = PageErrorCreator.errorForPastPickUpDateFromProperty();
        Assert.assertTrue(page.checkPickUpTimeErrorMessage(expectedError));
    }

    @Test(testName = "testcase 5: Search car for equal pick-up date and drop-off date")
    public void searchCarsForEqualPickUpDateAndDropOffDateTest() {
        FormPage page = new FormPage(driver);
        page.openPage().fillFromParams(CarReservationCreator.withEqualPickUpDateAndDropOffDateFromProperty()).search();
        PageError expectedError = PageErrorCreator.errorForEqualityOfPickupAndDropOffTimeErrorMessage();
        Assert.assertTrue(page.checkEqualityOfPickupAndDropOffTimeErrorMessage(expectedError));
    }

    @Test(testName = "testcase 6: Search car for pick-up and return places across continents")
    public void searchCarsForPickUpAndReturnPlacesAcrossContinentsTest() {
        FormPage page = new FormPage(driver);
        page.openPage().fillFromParams(CarReservationCreator.withPickUpAndReturnPlacesAcrossContinentsFromProperty()).search();
        PageError expectedError = PageErrorCreator.errorForPickUpAndReturnPlacesAcrossContinentsFromProperty();
        Assert.assertTrue(page.checkPickUpAndReturnPlacesAcrossContinentsErrorMessage(expectedError));
    }

    @Test(testName = "testcase 7: Login with not registered email")
    public void loginWithNotRegisteredEmailTest() {
        Account testAccount = AccountCreator.withNotRegisteredEmailOrPassword();
        FormPage page = new FormPage(driver);
        page.login(testAccount);
        PageError expectedError = PageErrorCreator.errorForNotRegisteredEmailOrPasswordFromProperty();
        Assert.assertTrue(page.checkloginErrorMessage(expectedError));
    }


}
