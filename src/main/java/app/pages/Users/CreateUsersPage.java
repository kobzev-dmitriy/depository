package app.pages.Users;

import app.pages.MainPage;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import helpers.Driver;
import model.UsersData;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CreateUsersPage extends MainPage {
    public SelenideElement activityCheckboxContainer = $(By.xpath("//input[contains(@class, 'checkbox__content')]")).find(By.xpath("./ancestor::fieldset[contains(@class, 'fieldset mb-3')][1]"));
    public SelenideElement activityCheckbox = $(By.xpath("//input[contains(@class, 'checkbox__content')]"));

    public SelenideElement loginField = $("input[name='login']");
    public SelenideElement loginFieldRequiredClass = loginField.find(By.xpath("./ancestor::fieldset[contains(@class, 'fieldset mb-3')][1]"));
    public SelenideElement loginFieldRequiredClassError = loginFieldRequiredClass.find(By.xpath("//div[contains(@class, 'fieldset__label-invalid')]"));

    public SelenideElement nameField = $("input[name='name']");
    public SelenideElement nameFieldRequiredClass = nameField.find(By.xpath("./ancestor::fieldset[contains(@class, 'fieldset mb-3')][1]"));
    public SelenideElement nameFieldRequiredClassError = nameFieldRequiredClass.find(By.xpath("//div[contains(@class, 'fieldset__label-invalid')]"));

    public SelenideElement surnameField = $("input[name='surname']");
    public SelenideElement surnameFieldRequiredClass = surnameField.find(By.xpath("./ancestor::fieldset[contains(@class, 'fieldset mb-3')][1]"));
    public SelenideElement surnameFieldRequiredClassError = surnameFieldRequiredClass.find(By.xpath("//div[contains(@class, 'fieldset__label-invalid')]"));

    public SelenideElement fathernameField = $("input[name='father_name']");

    public SelenideElement phoneField = $("input[name='phone']");

    public SelenideElement emailField = $("input[name='email']");
    public SelenideElement emailFieldClass = emailField.find(By.xpath("./ancestor::fieldset[contains(@class, 'fieldset mb-3')][1]"));
    public SelenideElement emailFieldClassError = emailFieldClass.find(By.xpath("//div[contains(@class, 'fieldset__label-invalid')]"));

    public SelenideElement passwordField = $("input[name='password']");
    public SelenideElement passwordFieldRequiredClass = passwordField.find(By.xpath("./ancestor::fieldset[contains(@class, 'fieldset mb-3')][1]"));

    public SelenideElement changepswdButton = $(By.xpath("//span[text()='Сменить пароль']"));

    public SelenideElement descriptionField = $(By.xpath(".//textarea[contains(@name, 'description')]"));

    public SelenideElement groupField = $(By.xpath("//form/fieldset[9]//div[contains(@class, 'fieldset__input')]//div[contains(@class, 'py-2')]"));
    public SelenideElement groupFieldRequiredClass = groupField.find(By.xpath("./ancestor::fieldset[contains(@class, 'fieldset mb-3')][1]"));

    public SelenideElement groupFirstList = $(By.xpath("//div[contains(@class, 'flat-list__item')][1]//label[contains(@class, 'checkbox__container')]"));

    public SelenideElement groupSearchField = $(By.xpath("//input[contains(@placeholder, 'Поиск')]"));

    public SelenideElement saveButton = $(By.xpath("//button[contains(@class, 'button_lg')]//span[text()='Сохранить']"));

    public SelenideElement saveChangeButton = $(By.xpath("//button[contains(@class, 'button_md')]//span[text()='Сохранить']"));

    public SelenideElement serviceAreaScreen = $(By.xpath("//div[contains(@class, '_Container_f')]"));

    public SelenideElement passwordRepeatField = $("input[name='password_confirm']");
    public SelenideElement passwordRepeatFieldRequiredClass = passwordRepeatField.find(By.xpath("./ancestor::fieldset[contains(@class, 'fieldset mb-3')][1]"));

    public SelenideElement successMessage = $(By.xpath("//div[text()='Успешно']"));

    public SelenideElement message = $("div[class='message']");

    public SelenideElement messageError = $(By.xpath("//h4[text()='Ошибка']"));

    public String highlightClass = "fieldset_invalid";



    public CreateUsersPage(String pageUrl) {
        super(pageUrl);
    }


    public CreateUsersPage shouldHaveClass(SelenideElement element, String text) {
        element.shouldHave(cssClass(text));
        Driver.wait(1);
        return this;
    }

    public void isActivityChecked(){
        //activityCheckbox.shouldHave(cssClass("fieldset mb-3 fieldset_active"));
        activityCheckbox.shouldHave(attribute("checked"));
    }

    public CreateUsersPage activityClick(){
        activityCheckboxContainer.click();
        return this;
    }

    // Поле Логин
    public CreateUsersPage loginSetValue(String value){
        loginField.sendKeys(Keys.CONTROL + "A");
        loginField.sendKeys(Keys.DELETE);
        loginField.setValue(value);
        return this;
    }

    public String loginGetValue(){
        return loginField.getAttribute("value");
    }

    // Поле Имя
    public CreateUsersPage nameSetValue(String value){
        nameField.sendKeys(Keys.CONTROL + "A");
        nameField.sendKeys(Keys.DELETE);
        nameField.setValue(value);
        return this;
    }

    public String nameGetValue(){
        return nameField.getAttribute("value");
    }

    // Поле Фамилия
    public CreateUsersPage surnameSetValue(String value){
        surnameField.sendKeys(Keys.CONTROL + "A");
        surnameField.sendKeys(Keys.DELETE);
        surnameField.setValue(value);
        return this;
    }

    public String surnameGetValue(){
        return surnameField.getAttribute("value");
    }

    // Поле Отчество
    public CreateUsersPage fathernameSetValue(String value){
        fathernameField.sendKeys(Keys.CONTROL + "A");
        fathernameField.sendKeys(Keys.DELETE);
        fathernameField.setValue(value);
        return this;
    }

    public String fathernameGetValue(){
        return fathernameField.getAttribute("value");
    }

    // Поле Телефон
    public CreateUsersPage phoneSetValue(String value){
        phoneField.sendKeys(Keys.CONTROL + "A");
        phoneField.sendKeys(Keys.DELETE);
        phoneField.sendKeys(Keys.chord(value));
        //Selenide.executeJavaScript("arguments[0].value = '" + value + "';", phoneField);
        //Selenide.executeJavaScript("arguments[0].scrollIntoView(true);", phoneField);
        //phoneField.setValue(value);
        return this;
    }

    public String phoneGetValue(){
        return phoneField.getAttribute("value");
    }

    // Поле Email
    public CreateUsersPage emailSetValue(String value){
        emailField.sendKeys(Keys.CONTROL + "A");
        emailField.sendKeys(Keys.DELETE);
        emailField.setValue(value);
        return this;
    }

    public String emailGetValue(){
        return emailField.getAttribute("value");
    }

    // Поле Пароль
    public CreateUsersPage passwordSetValue(String value){
        passwordField.sendKeys(Keys.CONTROL + "A");
        passwordField.sendKeys(Keys.DELETE);
        passwordField.setValue(value);
        return this;
    }

    public String passwordGetValue(){
        return passwordField.getAttribute("value");
    }

    // Поле повторного ввода нового пароля
    public CreateUsersPage passwordRepeatSetValue(String value){
        passwordRepeatField.sendKeys(Keys.CONTROL + "A");
        passwordRepeatField.sendKeys(Keys.DELETE);
        passwordRepeatField.setValue(value);
        return this;
    }

    public String passwordRepeatGetValue(){
        return passwordRepeatField.getAttribute("value");
    }

    // Поле Группа
    public CreateUsersPage groupFieldClick(){
        groupField.click();
        Driver.wait(1);
        return this;
    }

    // 1-я группа в списке групп
    public CreateUsersPage groupFirstListClick(){
        groupFirstList.click();
        return this;
    }

    public String groupGetValue(){
        return groupField.getText();
    }

    // Поле Описание
    public CreateUsersPage descriptionSetValue(String value){
        descriptionField.sendKeys(Keys.CONTROL + "A");
        descriptionField.sendKeys(Keys.DELETE);
        descriptionField.setValue(value);
        return this;
    }

    public String descriptionGetValue(){
        return descriptionField.getAttribute("value");
    }


    public CreateUsersPage successMessageClick(){
        if(successMessage.isEnabled())
            successMessage.click();
        return this;
    }

    public CreateUsersPage serviceAreaClick(){
        serviceAreaScreen.click(1,1);
        Driver.wait(1);
        return this;
    }

    // Кнопка Смены пароля
    public CreateUsersPage changepswdButtonClick(){
        changepswdButton.click();
        Driver.wait(1);
        return this;
    }

    // Кнопка Сохранить изменения пароля
    public CreateUsersPage saveChangepswdButtonClick(){
        saveChangeButton.click();
        Driver.wait(1);
        return this;
    }

    // Кнопка Сохранить
    public CreateUsersPage saveButtonClick(){
        saveButton.click();
        Driver.wait(1);
        return this;
    }

    public String messageGetValue(){
        return message.getText();
    }

    public CreateUsersPage messageClick(){
        message.click();
        return this;
    }

    public CreateUsersPage messageErrorClick(){
        messageError.click();
        return this;
    }

    public void createUser(UsersData user){
        loginSetValue(user.getLogin());
        nameSetValue(user.getName());
        surnameSetValue(user.getSurname());
        fathernameSetValue(user.getFathername());
        phoneSetValue(user.getPhone());
        emailSetValue(user.getEmail());
        passwordSetValue(user.getPassword());
        descriptionSetValue(user.getDescription());
        groupFieldClick();
        groupFirstListClick();
        serviceAreaClick();
        saveButtonClick();
    }

    public void editUser(UsersData user){
        loginSetValue(user.getLogin());
        nameSetValue(user.getName());
        surnameSetValue(user.getSurname());
        fathernameSetValue(user.getFathername());
        phoneSetValue(user.getPhone());
        emailSetValue(user.getEmail());
        changePasswordUser(user.getPassword());
        descriptionSetValue(user.getDescription());
        groupFieldClick();
        groupFirstListClick();
        serviceAreaClick();
        saveButtonClick();
    }

    public UsersData infoFromEditUserForm(){
        String login = loginGetValue();
        String name = nameGetValue();
        String surname = surnameGetValue();
        String fathername = fathernameGetValue();
        String email = emailGetValue();
        String group = groupGetValue();
        String description = descriptionGetValue();
        return new UsersData().withLogin(login).withName(name).withSurname(surname).withFathername(fathername)
                .withEmail(email).withGroup(group).withDescription(description);
    }

    public void clearAllUserFields(){
        loginField.sendKeys(Keys.CONTROL + "A");
        loginField.sendKeys(Keys.DELETE);
        nameField.sendKeys(Keys.CONTROL + "A");
        nameField.sendKeys(Keys.DELETE);
        surnameField.sendKeys(Keys.CONTROL + "A");
        surnameField.sendKeys(Keys.DELETE);
        fathernameField.sendKeys(Keys.CONTROL + "A");
        fathernameField.sendKeys(Keys.DELETE);
        emailField.sendKeys(Keys.CONTROL + "A");
        emailField.sendKeys(Keys.DELETE);
        phoneField.sendKeys(Keys.CONTROL + "A");
        phoneField.sendKeys(Keys.DELETE);
        descriptionField.sendKeys(Keys.CONTROL + "A");
        descriptionField.sendKeys(Keys.DELETE);
        groupFieldClick();
        groupFirstListClick();
        serviceAreaClick();
    }

    public void checkEnabledEditUserFields() {
        activityCheckbox.isEnabled();
        loginField.isEnabled();
        nameField.isEnabled();
        surnameField.isEnabled();
        fathernameField.isEnabled();
        phoneField.isEnabled();
        groupField.isEnabled();
        descriptionField.isEnabled();
        emailField.isEnabled();
        changepswdButton.isEnabled();
    }

    public void checkEnabledCreateUserFields() {
        isActivityChecked();
        activityCheckbox.isEnabled();
        loginField.isEnabled();
        nameField.isEnabled();
        surnameField.isEnabled();
        fathernameField.isEnabled();
        emailField.isEnabled();
        phoneField.isEnabled();
        passwordField.isEnabled();
        groupField.isEnabled();
        descriptionField.isEnabled();
        saveButton.isEnabled();
        groupField.click();
        groupSearchField.isEnabled();
        groupFirstList.isEnabled();
    }

    public void changePasswordUser(String value){
        changepswdButtonClick();
        passwordField.isEnabled();
        passwordSetValue(value);
        passwordRepeatField.isEnabled();
        passwordRepeatSetValue(value);
        saveChangepswdButtonClick();
        serviceAreaClick();
    }
}
