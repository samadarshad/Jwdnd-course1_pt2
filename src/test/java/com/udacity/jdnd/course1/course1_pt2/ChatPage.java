package com.udacity.jdnd.course1.course1_pt2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import javax.swing.text.Style;
import java.util.List;
import java.util.stream.Collectors;

public class ChatPage {
    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "messageText")
    private WebElement messageText;

    @FindBy(id = "messageType")
    private WebElement messageType;

    @FindBy(id = "messagesList")
    private WebElement messagesList;

    @FindBy(xpath = "/html/body/ul/li")
    private List<WebElement> messagesList2;

    @FindBy(id = "logout")
    private WebElement logoutLink;

    public ChatPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public List<String> getMessagesList() {
        List<String> messagesListStr = messagesList2.stream().map(item -> item.getText()).collect(Collectors.toList());
        return messagesListStr;
    }

    public void sendMessage(String inMsgText, String inMsgType) {
        messageText.clear();
        messageText.sendKeys(inMsgText);
        selectMessageType(inMsgType);
        messageText.submit();
    }

    public void selectMessageType(String value) {
        Select messageTypeDropdown = new Select(messageType);
        messageTypeDropdown.selectByValue(value);
    }

    public void logout() {
        logoutLink.click();
    }
}
