package com.udacity.jdnd.course1.course1_pt2;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserTestingApplicationTests {
    @LocalServerPort
    private Integer port;

    private static WebDriver driver;
    private LoginPage loginPage;
    private SignupPage signupPage;
    private ChatPage chatPage;

    @BeforeAll
    public static void beforeEach() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void afterEach() {
        driver.quit();
    }
    
    public void signupAndLogin(String username) {
        String firstname = "first";
        String lastname = "last";
        String password = "pass";

        driver.get("http://localhost:" + port + "/signup");
        signupPage = new SignupPage(driver);
        signupPage.signup(firstname, lastname, username, password);

        driver.get("http://localhost:" + port + "/login");
        loginPage = new LoginPage(driver);

        loginPage.login(username, password);
    }

    @Test
    public void whenUserSignsUpTheyCanLogin() throws InterruptedException {
        signupAndLogin("user");

        driver.get("http://localhost:" + port + "/chat");
        chatPage = new ChatPage(driver);

        chatPage.sendMessage("Hello", "Say");
        chatPage.sendMessage("Hello", "Shout");
        chatPage.sendMessage("Hello", "Whisper");
        List<String> messages = chatPage.getMessagesList();

        List<String> expectedList = Arrays.asList("user: Hello", "user: HELLO", "user: hello");
        assertEquals(messages, expectedList);
    }

    @Test
    public void whenUserOneSendsMessageThenUserTwoCanReadMessage() throws InterruptedException {
        signupAndLogin("user1"); //how do I do all this user signup setting-up in the backend?

        driver.get("http://localhost:" + port + "/chat");
        chatPage = new ChatPage(driver);

        chatPage.sendMessage("Hello", "Say");
        chatPage.logout();

        signupAndLogin("user2");

        driver.get("http://localhost:" + port + "/chat");
        chatPage = new ChatPage(driver);

        chatPage.sendMessage("Hi", "Say");


        List<String> expectedList = Arrays.asList("user1: Hello", "user2: Hi");
        List<String> messages = chatPage.getMessagesList();
        assertEquals(messages, expectedList);
    }


}