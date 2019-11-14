package by.bsu.expedia;

import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {
    protected final int WAIT_TIMEOUT_SECONDS = 40;

    protected WebDriver webDriver;
    protected abstract AbstractPage openPage();


    protected AbstractPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
}