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

public class TC03_FormatoSingEmail {

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
    @DisplayName("Verificar que el botón 'Sign Up' no se habilite con email inválido")
    void testEmailInvalido() throws InterruptedException {
        driver.get("https://test-qa.inlaze.com/auth/sign-up");
        Thread.sleep(2000);
        
        WebElement fullNameField = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("full-name")));
        fullNameField.sendKeys("Pedro Perez");
        Thread.sleep(2000);
        
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("correoinvalido");
        Thread.sleep(2000);
        
        By passwordLocator = By.cssSelector("app-password#password input#password");
        WebElement passwordField = wait.until(
                ExpectedConditions.elementToBeClickable(passwordLocator));
        Thread.sleep(2000);
        
        passwordField.click();
        passwordField.sendKeys("Prueba1234!");
        Thread.sleep(2000);
        
        By confirmPasswordLocator = By.cssSelector("app-password#confirm-password input#confirm-password");
        WebElement confirmPasswordField = wait.until(
                ExpectedConditions.elementToBeClickable(confirmPasswordLocator));
        Thread.sleep(2000);
        
        confirmPasswordField.sendKeys("Prueba1234!");
        Thread.sleep(2000);
        
        WebElement signUpButton = driver.findElement(By.xpath("//button[@type='submit']"));
        Thread.sleep(2000);
        
        signUpButton.click();
        
        try {
            boolean redirected = wait.until(ExpectedConditions.urlContains("sign-in"));
            assertFalse(redirected, "No debería redirigir con email inválido.");
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    @DisplayName("Verificar duplicidad de email")
    void testCorreoDuplicado() throws InterruptedException {
        driver.get("https://test-qa.inlaze.com/auth/sign-up");
        Thread.sleep(2000);
        
        WebElement fullNameField = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("full-name")));
        fullNameField.sendKeys("Pedro Perez");
        Thread.sleep(2000);
        
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("correo@test.com");
        Thread.sleep(2000);
        
        By passwordLocator = By.cssSelector("app-password#password input#password");
        WebElement passwordField = wait.until(
                ExpectedConditions.elementToBeClickable(passwordLocator));
        Thread.sleep(2000);
        
        passwordField.click();
        passwordField.sendKeys("Prueba1234!");
        Thread.sleep(2000);
        
        By confirmPasswordLocator = By.cssSelector("app-password#confirm-password input#confirm-password");
        WebElement confirmPasswordField = wait.until(
                ExpectedConditions.elementToBeClickable(confirmPasswordLocator));
        Thread.sleep(2000);
        
        confirmPasswordField.sendKeys("Prueba1234!");
        Thread.sleep(2000);
        
        WebElement signUpButton = driver.findElement(By.xpath("//button[@type='submit']"));
        Thread.sleep(2000);
        
        signUpButton.click();
        Thread.sleep(3000);
        
        driver.get("https://test-qa.inlaze.com/auth/sign-up");
        Thread.sleep(2000);
        
        fullNameField = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("full-name")));
        fullNameField.sendKeys("Maria Garcia");
        Thread.sleep(2000);
        
        WebElement emailField2 = driver.findElement(By.id("email"));
        emailField2.sendKeys("correo@test.com");
        Thread.sleep(2000);
        
        WebElement passwordField2 = wait.until(
                ExpectedConditions.elementToBeClickable(passwordLocator));
        Thread.sleep(2000);
        
        passwordField2.click();
        passwordField2.sendKeys("Prueba1234!");
        Thread.sleep(2000);
        
        WebElement confirmPasswordField2 = wait.until(
                ExpectedConditions.elementToBeClickable(confirmPasswordLocator));
        Thread.sleep(2000);
        
        confirmPasswordField2.sendKeys("Prueba1234!");
        Thread.sleep(2000);
        
        signUpButton = driver.findElement(By.xpath("//button[@type='submit']"));
        Thread.sleep(2000);
        
        signUpButton.click();
        try {
            boolean redirected = wait.until(ExpectedConditions.urlContains("sign-in"));
            assertFalse(redirected, "No debería redirigir con email duplicado.");
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
