package app.pages;

import com.codeborne.selenide.SelenideElement;
import helpers.Driver;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage extends BasePage {

    public SelenideElement loginField = $("input[name='username']");
    public SelenideElement passwordField = $("input[name='password']");
    public SelenideElement signInButton = $("button.button_lg.button_primary");
    public SelenideElement errorAuthMessage = $(By.xpath("//div[text() = 'Неверные учетные данные пользователя']"));
    public SelenideElement errorMessage = $(By.xpath("//div[contains(@class, '_Error_')]"));

    public LoginPage(String pageUrl) {
        super(pageUrl);
    }

    public void login(String login, String password) {
        loginField.setValue(login);
        passwordField.setValue(password);
        signInButton.click();
        Driver.wait(1);
        Driver.waitForUrlContains("/#/");
    }

    public String errorMessageGetText(){
        return errorMessage.getText();
    }
}
