package com.udacity.jdnd.course1.course1_pt2;
import com.udacity.jdnd.course1.course1_pt2.model.User;
import com.udacity.jdnd.course1.course1_pt2.service.UserService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserTestingApplicationTests {
    @LocalServerPort
    private Integer port;

    private static WebDriver driver;
    private LoginPage loginPage;
    private SignupPage signupPage;
    private ChatPage chatPage;

    @Autowired
    private UserService userService;

    @BeforeAll
    public static void beforeEach() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void afterEach() {
        driver.quit();
    }

    public void signup(String username, String password) {
        String firstname = "first";
        String lastname = "last";

        driver.get("http://localhost:" + port + "/signup");
        signupPage = new SignupPage(driver);
        signupPage.signup(firstname, lastname, username, password);
    }

    public void login(String username, String password) {
        driver.get("http://localhost:" + port + "/login");
        loginPage = new LoginPage(driver);

        loginPage.login(username, password);
    }

    public void signupAndLoginAndRedirectToChatPage(String username) {
        String password = "pass";
        signup(username, password);
        login(username, password);
    }

    @Test
    public void whenUserSignsUpTheyCanLogin() throws InterruptedException {
        signupAndLoginAndRedirectToChatPage("user");

//        driver.get("http://localhost:" + port + "/chat");
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
        signupAndLoginAndRedirectToChatPage("user1"); //how do I do all this user signup setting-up in the backend?

//        driver.get("http://localhost:" + port + "/chat");
        chatPage = new ChatPage(driver);

        chatPage.sendMessage("Hello", "Say");
        chatPage.logout();

        signupAndLoginAndRedirectToChatPage("user2");

//        driver.get("http://localhost:" + port + "/chat"); // you dont need this, it automatically takes you to the chatpage
        chatPage = new ChatPage(driver);

        chatPage.sendMessage("Hi", "Say");


        List<String> expectedList = Arrays.asList("user1: Hello", "user2: Hi");
        List<String> messages = chatPage.getMessagesList();
        assertEquals(messages, expectedList);
    }

    @Test
    public void whenAddUserToDatabaseTheirUsernameIsNotAvailable() throws InterruptedException {
        assertTrue(userService.isUsernameAvailable("user"));
        User user = new User(null, "user", null, "pass", "first", "last");
        userService.createUser(user);
        assertFalse(userService.isUsernameAvailable("user"));
    }

    @Test
    public void whenAddUserToDatabaseTheyCanLogin() throws InterruptedException {
        User user = new User(null, "user", null, "pass", "first", "last");
        userService.createUser(user);
        login("user", "pass");

        chatPage = new ChatPage(driver);
        assertTrue(chatPage.isLoggedIn());
//        Thread.sleep(3000);
    }


}