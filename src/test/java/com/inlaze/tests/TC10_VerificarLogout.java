package com.inlaze.tests;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
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

public class TC10_VerificarLogout {

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
    @DisplayName("Verificar que la plataforma permita cerrar sesión correctamente")
    void testCerrarSesion() throws InterruptedException {
        driver.get("https://test-qa.inlaze.com/auth/sign-in");
        Thread.sleep(3000);

        WebElement emailField = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("email"))
        );
        emailField.sendKeys("pedro.perez1@test.com");
        Thread.sleep(3000);

        WebElement passwordField = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("app-password#password input#password")
                )
        );
        passwordField.sendKeys("Pedro123!");
        Thread.sleep(3000);

        WebElement signInButton = driver.findElement(By.xpath("//button[@type='submit']"));
        signInButton.click();
        Thread.sleep(5000);

        WebElement avatar = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("img[src*='/assets/rengoku.webp']")
                )
        );
        avatar.click();
        Thread.sleep(3000);

        WebElement logoutButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[text()='Logout']")
                )
        );
        logoutButton.click();
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
