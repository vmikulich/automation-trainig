package by.bsu.expedia.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverSingleton {
    private static final Logger LOGGER = LogManager.getRootLogger();
    private static final String SYSTEM_PROPERTY_BROWSER = "browser";
    private static final String SYSTEM_PROPERTY_BROWSER_CHROME = "chrome";

    private static WebDriver driver;

    private DriverSingleton() {
    }

    public static WebDriver getDriver() {
        if (null == driver) {
            String browser = System.getProperty(SYSTEM_PROPERTY_BROWSER);
            if (browser == SYSTEM_PROPERTY_BROWSER_CHROME) {
                WebDriverManager.firefoxdriver().setup();
                driver = new ChromeDriver();
                LOGGER.info("Created Firefox driver");
            } else {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                LOGGER.info("Created Edge driver");
            }
//            switch (System.getProperty(SYSTEM_PROPERTY_BROWSER)) {
//                case SYSTEM_PROPERTY_BROWSER_CHROME: {
//                    WebDriverManager.firefoxdriver().setup();
//                    driver = new ChromeDriver();
//                    LOGGER.info("Created Firefox driver");
//                }
//                default: {
//                    System.out.println("zhp[a");
//                    WebDriverManager.edgedriver().setup();
//                    driver = new EdgeDriver();
//                    LOGGER.info("Created Edge driver");
//
//                }
//            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
        LOGGER.info("Driver closed");
    }
}
