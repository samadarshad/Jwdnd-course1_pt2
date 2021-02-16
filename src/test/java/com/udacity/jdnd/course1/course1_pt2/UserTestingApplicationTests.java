package com.udacity.jdnd.course1.course1_pt2;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserTestingApplicationTests {
    @LocalServerPort
    private Integer port;

    private static WebDriver driver;
    private LoginPage loginPage;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
    }

    @BeforeEach
    public void beforeEach() {
        driver.get("http://localhost:" + port + "/login");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void whenUserGoesToPageTheyCanSignUp() throws InterruptedException {
        loginPage.goToSignup();
        Thread.sleep(3000);
    }

//    @Test
//    public void testIncrement() throws InterruptedException {
//        Thread.sleep(3000);
////        int prevValue = counter.getDisplayedCount();
////        counter.incrementCount();
////        assertEquals(prevValue + 1, counter.getDisplayedCount());
//    }
//
//    @Test
//    public void testIncrementTenTimes() {
//        int prevValue = counter.getDisplayedCount();
//        for (int i = 0; i < 10; i++) {
//            assertEquals(prevValue + i, counter.getDisplayedCount());
//            counter.incrementCount();
//        }
//    }
//
//    @Test
//    public void testReset() {
//        counter.resetCount(10);
//        assertEquals(10, counter.getDisplayedCount());
//        counter.resetCount(0);
//        assertEquals(0, counter.getDisplayedCount());
//    }

}