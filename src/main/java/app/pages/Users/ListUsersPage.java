package app.pages.Users;

import app.pages.MainPage;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import helpers.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ListUsersPage extends MainPage {
    public SelenideElement idRow1ListUsers = $(By.xpath("//div[contains(@class, 'table')]//div[contains(@class, 'tbody')]//div[contains(@class, 'tr')][1]//div[contains(@class, 'td')][1]//span[contains(@class, 'td__text')]"));
    public SelenideElement idNameRowListUsers = $(By.xpath("//div[contains(@class, 'tr')]/div[contains(@role, 'columnheader')][1]"));

    public SelenideElement loginRow1ListUsers = $(By.xpath("//div[contains(@class, 'table')]//div[contains(@class, 'tbody')]//div[contains(@class, 'tr')][1]//div[contains(@class, 'td')][2]//span[contains(@class, 'td__text')]"));
    public SelenideElement loginNameRowListUsers = $(By.xpath("//div[contains(@class, 'tr')]/div[contains(@role, 'columnheader')][2]"));

    public SelenideElement nameRow1ListUsers = $(By.xpath("//div[contains(@class, 'table')]//div[contains(@class, 'tbody')]//div[contains(@class, 'tr')][1]//div[contains(@class, 'td')][3]//span[contains(@class, 'td__text')]"));
    public SelenideElement nameNameRowListUsers = $(By.xpath("//div[contains(@class, 'tr')]/div[contains(@role, 'columnheader')][3]"));

    public SelenideElement surnameRow1ListUsers = $(By.xpath("//div[contains(@class, 'table')]//div[contains(@class, 'tbody')]//div[contains(@class, 'tr')][1]//div[contains(@class, 'td')][4]//span[contains(@class, 'td__text')]"));
    public SelenideElement surnameNameRowListUsers = $(By.xpath("//div[contains(@class, 'tr')]/div[contains(@role, 'columnheader')][4]"));

    public SelenideElement phoneRow1ListUsers = $(By.xpath("//div[contains(@class, 'table')]//div[contains(@class, 'tbody')]//div[contains(@class, 'tr')][1]//div[contains(@class, 'td')][5]//span[contains(@class, 'td__text')]"));

    public SelenideElement emailRow1ListUsers = $(By.xpath("//div[contains(@class, 'table')]//div[contains(@class, 'tbody')]//div[contains(@class, 'tr')][1]//div[contains(@class, 'td')][6]//span[contains(@class, 'td__text')]"));

    public SelenideElement activityRow1ListUsers = $(By.xpath("//div[contains(@class, 'table')]//div[contains(@class, 'tbody')]//div[contains(@class, 'tr')][1]//div[contains(@class, 'td')][7]//span[contains(@class, 'td__text')]"));

    public SelenideElement lastaccessRow1ListUsers = $(By.xpath("//div[contains(@class, 'table')]//div[contains(@class, 'tbody')]//div[contains(@class, 'tr')][1]//div[contains(@class, 'td')][8]//span[contains(@class, 'td__text')]"));

    public SelenideElement editRow1ListUsers = $(By.xpath("//div[contains(@class, 'table')]//div[contains(@class, 'tbody')]//div[contains(@class, 'tr')][1]//div[contains(@class, 'td')][9]//div[contains(@class, 'col')][1]//button[contains(@class, 'button')]//span[text()='Редактировать']"));

    public SelenideElement deleteRow1ListUsers = $(By.xpath("//div[contains(@class, 'table')]//div[contains(@class, 'tbody')]//div[contains(@class, 'tr')][1]//div[contains(@class, 'td')][9]//div[contains(@class, 'col')][2]//button[contains(@class, 'button')]//span[text()='Удалить']"));

    public SelenideElement acceptDeleteUser = $(By.xpath("//button[contains(@class, 'button')]//span[text()='Да']"));

    public SelenideElement denialDeleteUser = $(By.xpath("//button[contains(@class, 'button')]//span[text()='Нет']"));

    public SelenideElement createButton = $(By.xpath(".//span[text()='Создать']"));

    public SelenideElement searchUserByNameField = $(By.xpath("//input[contains(@placeholder, 'Поиск по имени')]"));

    public SelenideElement emptySearchResultMsg = $(By.xpath("//span[text()='Нет данных для отображения']"));

    public SelenideElement successMessageList = $(By.xpath("//div[text()='Успешно']"));

    public ElementsCollection rowSearchResult = $$(By.xpath("//div[contains(@class, 'table')]//div[contains(@class, 'tbody')]//div[contains(@class, 'tr')]"));

    public SelenideElement firstPagePagination = $(By.xpath("//button[text()='«']"));

    public SelenideElement prevPagePagination = $(By.xpath("//button[text()='‹']"));

    public SelenideElement nextPagePagination = $(By.xpath("//button[text()='›']"));

    public SelenideElement lastPagePagination = $(By.xpath("//button[text()='»']"));

    public SelenideElement fivePerListPagination = $(By.xpath("//option[text()='5']"));

    public SelenideElement twentyFivePerListPagination = $(By.xpath("//option[text()='25']"));

    public SelenideElement fiftyPerListPagination = $(By.xpath("//option[text()='50']"));

    public SelenideElement hundredPerListPagination = $(By.xpath("//option[text()='100']"));

    public ListUsersPage(String pageUrl) {
        super(pageUrl);
    }

    public ListUsersPage createButtonClick(){
        createButton.click();
        Driver.wait(1);
        return this;
    }

    public String getLoginUsersList(){
        return loginRow1ListUsers.getText();
    }
    public String getNameUsersList(){
        return nameRow1ListUsers.getText();
    }
    public String getSurnameUsersList(){
        return surnameRow1ListUsers.getText();
    }
    public String getPhoneUsersList(){
        return phoneRow1ListUsers.getText();
    }
    public String getEmailUsersList(){
        return emailRow1ListUsers.getText();
    }
    public String getActivityUsersList() {
        return activityRow1ListUsers.getText();
    }
    public String lastaccessUsersList(){
        return lastaccessRow1ListUsers.getText();
    }

    public ListUsersPage idUsersSort(){
        idNameRowListUsers.click();
        Driver.wait(1);
        return this;
    }

    public ListUsersPage loginUsersSort(){
        loginNameRowListUsers.click();
        Driver.wait(1);
        return this;
    }

    public ListUsersPage nameUsersSort(){
        nameNameRowListUsers.click();
        Driver.wait(1);
        return this;
    }

    public ListUsersPage surnameUsersSort(){
        surnameNameRowListUsers.click();
        Driver.wait(1);
        return this;
    }

    public ListUsersPage editRow1ListUsersClick(){
        editRow1ListUsers.click();
        Driver.wait(1);
        return this;
    }

    public ListUsersPage deleteRow1ListUsersClick(){
        deleteRow1ListUsers.click();
        Driver.wait(1);
        return this;
    }

    public ListUsersPage acceptDeleteUserClick(){
        acceptDeleteUser.click();
        Driver.wait(1);
        return this;
    }

    public ListUsersPage denialDeleteUserClick(){
        denialDeleteUser.click();
        Driver.wait(1);
        return this;
    }

    public ListUsersPage searchUserByName(String string) {
        if (string == "") {
            searchUserByNameField.sendKeys(Keys.CONTROL + "A");
            searchUserByNameField.sendKeys(Keys.DELETE);
        } else {
            searchUserByNameField.setValue(string);
        }
        Driver.wait(1);
        return this;
    }

    public ListUsersPage emptySearchResultMsgEnabled(){
        emptySearchResultMsg.isEnabled();
        return this;
    }

    public void deleteUserBySearchName(String name){
        searchUserByName(name);
        deleteRow1ListUsersClick();
        acceptDeleteUserClick();
        successMessageListClick();
    }

    public ListUsersPage successMessageListClick(){
        if(successMessageList.isEnabled())
            successMessageList.click();
        return this;
    }

    public int countSearchResult() {
        return rowSearchResult.size();
    }

    public void checkEnabledListUsersFields() {
        idRow1ListUsers.isEnabled();
        loginRow1ListUsers.isEnabled();
        nameRow1ListUsers.isEnabled();
        surnameRow1ListUsers.isEnabled();
        phoneRow1ListUsers.isEnabled();
        emailRow1ListUsers.isEnabled();
        activityRow1ListUsers.isEnabled();
        lastaccessRow1ListUsers.isEnabled();
        editRow1ListUsers.isEnabled();
        deleteRow1ListUsers.isEnabled();
        createButton.isEnabled();
        searchUserByNameField.isEnabled();
        firstPagePagination.isEnabled();
        prevPagePagination.isEnabled();
        nextPagePagination.isEnabled();
        lastPagePagination.isEnabled();
        fivePerListPagination.isEnabled();
        twentyFivePerListPagination.isEnabled();
        fiftyPerListPagination.isEnabled();
        hundredPerListPagination.isEnabled();
    }
}
