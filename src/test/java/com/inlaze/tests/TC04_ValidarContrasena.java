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

public class TC04_ValidarContrasena {

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
    @DisplayName("Validar Requisitos de Contraseña y Coincidencia")
    void testValidarContraseña() throws InterruptedException {
        driver.get("https://test-qa.inlaze.com/auth/sign-up");

        WebElement fullNameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("full-name")));
        fullNameField.sendKeys("Pedro Perez");
        Thread.sleep(5000);

        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("pedro.perez" + System.currentTimeMillis() + "@gmail.com");
        Thread.sleep(5000);

        By passwordLocator = By.cssSelector("app-password#password input#password");
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(passwordLocator));
        passwordField.sendKeys("12345");
        Thread.sleep(5000);

        By confirmPasswordLocator = By.cssSelector("app-password#confirm-password input#confirm-password");
        WebElement confirmPasswordField = wait.until(ExpectedConditions.elementToBeClickable(confirmPasswordLocator));
        confirmPasswordField.sendKeys("12345");
        Thread.sleep(5000);

        WebElement signUpButton = driver.findElement(By.xpath("//button[@type='submit']"));
        assertFalse(signUpButton.isEnabled());
        Thread.sleep(5000);

        passwordField.clear();
        passwordField.sendKeys("Prueba1234!");

        Thread.sleep(5000);

        confirmPasswordField.clear();
        confirmPasswordField.sendKeys("Prueba1234!");
        Thread.sleep(5000);

        assertTrue(signUpButton.isEnabled());
        signUpButton.click();
        Thread.sleep(5000);
        assertTrue(driver.getCurrentUrl().contains("sign-in"));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
