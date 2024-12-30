package com.inlaze.tests;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC02_ValidarNombre {

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
    @DisplayName("Validar que el botón 'Sign Up' permanezca deshabilitado con una sola palabra en el campo nombre")
    void testNombreUnaPalabra() throws InterruptedException {
        driver.get("https://test-qa.inlaze.com/auth/sign-up");

        WebElement fullNameField = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("full-name")));
        fullNameField.clear();
        fullNameField.sendKeys("Pedro");
        Thread.sleep(3000); 

        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("pedro.error" + System.currentTimeMillis() + "@gmail.com");

        By passwordLocator = By.cssSelector("app-password#password input#password");
        WebElement passwordField = wait.until(
                ExpectedConditions.elementToBeClickable(passwordLocator));
        Thread.sleep(3000);

        passwordField.click();
        passwordField.sendKeys("Prueba1234!");

        By confirmPasswordLocator = By.cssSelector("app-password#confirm-password input#confirm-password");
        WebElement confirmPasswordField = wait.until(
                ExpectedConditions.elementToBeClickable(confirmPasswordLocator));
        confirmPasswordField.sendKeys("Prueba1234!");

        WebElement signUpButton = driver.findElement(By.xpath("//button[@type='submit']"));
        Thread.sleep(2000); 

        assertFalse(signUpButton.isEnabled(), 
                "El botón 'Sign Up' debe permanecer deshabilitado cuando el campo de nombre tiene solo una palabra.");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
