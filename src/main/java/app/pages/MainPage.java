package app.pages;

import app.AppConfig;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import helpers.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.$;

public class MainPage extends BasePage{

    public SelenideElement logoutButton = $(By.xpath(".//span[text()='Выйти']"));
    public SelenideElement groupsModuleLink = $(By.linkText("Группы"));
    public SelenideElement usersModuleLink = $(By.linkText("Пользователи"));

    public MainPage(String pageUrl) {
        super(pageUrl);
    }

    public MainPage logout(){
        logoutButton.isEnabled();
        logoutButton.click();
        return this;
    }

    public MainPage openGroups(){
        groupsModuleLink.click();
        Driver.wait(1);
        return this;
    }

    public MainPage openUsers(){
        usersModuleLink.click();
        Driver.wait(1);
        return this;
    }
}
