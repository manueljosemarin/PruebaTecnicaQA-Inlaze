package com.inlaze.tests;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC01_RegistroValido {

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
    @DisplayName("Registro Válido de Usuario")
    void testRegistroValido() throws InterruptedException {

        driver.get("https://test-qa.inlaze.com/auth/sign-up");

        WebElement fullNameField = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("full-name")));
        fullNameField.sendKeys("Pedro Perez");
        Thread.sleep(5000);

        WebElement emailField = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("email")));
        emailField.sendKeys("pedro.perez" + System.currentTimeMillis() + "@gmail.com");
        Thread.sleep(5000);

        By passwordLocator = By.cssSelector("app-password#password input#password");
        WebElement passwordField = wait.until(
                ExpectedConditions.elementToBeClickable(passwordLocator));
        Thread.sleep(5000);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", passwordField);

        passwordField.click();

        passwordField.sendKeys("Prueba1234!");

        By confirmPasswordLocator = By.cssSelector("app-password#confirm-password input#confirm-password");
        WebElement confirmPasswordField = wait.until(
                ExpectedConditions.elementToBeClickable(confirmPasswordLocator));
        confirmPasswordField.sendKeys("Prueba1234!");

        WebElement signUpButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        signUpButton.click();

        boolean redirected = wait.until(ExpectedConditions.urlContains("sign-in"));
        Thread.sleep(5000);

        assertTrue(redirected,
                "El registro debe redirigir a la página de inicio de sesión (sign-in).");

    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
