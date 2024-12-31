package com.inlaze.tests;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC05_CamposObligatorios {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    @DisplayName("Comprobar que el formulario no se envíe si los campos obligatorios no están completos")
    void testCamposObligatorios() throws InterruptedException {
        driver.get("https://test-qa.inlaze.com/auth/sign-up");
        Thread.sleep(3000);

        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("pedro.perez" + System.currentTimeMillis() + "@gmail.com");
        Thread.sleep(3000);

        WebElement passwordField = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("app-password#password input#password")
            )
        );
        passwordField.sendKeys("Contra123!");
        Thread.sleep(3000);

        WebElement confirmPasswordField = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("app-password#confirm-password input#confirm-password")
            )
        );
        confirmPasswordField.sendKeys("Contra123!");
        Thread.sleep(3000);

        WebElement signUpButton = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']"))
        );
        Thread.sleep(3000);

        assertFalse(signUpButton.isEnabled());
        Thread.sleep(3000);

        WebElement fullNameField = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("full-name"))
        );
        fullNameField.sendKeys("Pedro Pérez");
        Thread.sleep(3000);

        assertTrue(signUpButton.isEnabled());
        signUpButton.click();
        Thread.sleep(3000);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
