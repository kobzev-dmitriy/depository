import app.App;
import app.AppConfig;
import app.pages.Users.CreateUsersPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideConfig;
import com.codeborne.selenide.SelenideDriver;
import model.UsersData;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.jws.soap.SOAPBinding;

import java.security.Key;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class UsersFunctTests extends A_BaseTest
{
    /*@Test
    public void A_createNewUser() {
        UsersData userData = new UsersData()
                .withLogin("Login").withName("Name").withSurname("Surname").withFathername("Fathername")
                .withPhone("Keys.NUMPAD7, Keys.NUMPAD9, Keys.NUMPAD1, Keys.NUMPAD2, Keys.NUMPAD3, Keys.NUMPAD4,Keys.NUMPAD5,Keys.NUMPAD6,Keys.NUMPAD7,Keys.NUMPAD8,Keys.NUMPAD9")
                .withEmail("email@email.com").withPassword("Password").withDescription("Description");
        app.mainPage.openUsers();
        app.listUsersPage.createButtonClick();
        app.createUsersPage.createUser(userData);
        app.createUsersPage.successMessageClick();
    }
    */

    @Test
    // 3905 Пользователи. Обязательные поля при создании
    public void B_requiredFieldsCreateUser() {
        app.loginPage.open();
        app.mainPage.openUsers();
        app.listUsersPage.createButtonClick();
        // Нажимаем сразу "Сохранить" Не успешно, подсвечены красным обязательные поля (Логин*, Имя*, Фамилия*, Пароль*, Группы*)
        app.createUsersPage.saveButtonClick();
        app.createUsersPage.isActivityChecked();
        app.createUsersPage.shouldHaveClass(app.createUsersPage.loginFieldRequiredClass, app.createUsersPage.highlightClass);
        app.createUsersPage.shouldHaveClass(app.createUsersPage.nameFieldRequiredClass, app.createUsersPage.highlightClass);
        app.createUsersPage.shouldHaveClass(app.createUsersPage.surnameFieldRequiredClass, app.createUsersPage.highlightClass);
        app.createUsersPage.shouldHaveClass(app.createUsersPage.passwordFieldRequiredClass, app.createUsersPage.highlightClass);
        app.createUsersPage.shouldHaveClass(app.createUsersPage.groupFieldRequiredClass, app.createUsersPage.highlightClass);
    }

    @Test
    // 4595 Пользователи. Внешний вид и обязательные поля при редактировании
    public void C_reguiredFieldsEditFormUser() {
        UsersData userData = new UsersData().withLogin("Login").withName("Name").withSurname("Surname")
                .withFathername("Fathername").withPhone("79123456789").withEmail("email@email.com").withGroup("Администратор")
                .withDescription("Description");

        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("Name");
        // Проверяем корректность значений полей в списке пользователей
        Assert.assertEquals(app.listUsersPage.getLoginUsersList(), userData.getLogin());
        Assert.assertEquals(app.listUsersPage.getNameUsersList(), userData.getName());
        Assert.assertEquals(app.listUsersPage.getSurnameUsersList(), userData.getSurname());
        Assert.assertEquals(app.listUsersPage.getEmailUsersList(), userData.getEmail());

        // Проверяем корректность значений полей в форме редактирования пользователя
        app.listUsersPage.editRow1ListUsersClick();
        app.createUsersPage.isActivityChecked();
        app.createUsersPage.checkEnabledEditUserFields();
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getLogin(), userData.getLogin());
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getName(), userData.getName());
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getSurname(), userData.getSurname());
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getFathername(), userData.getFathername());
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getEmail(), userData.getEmail());
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getGroup(), userData.getGroup());
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getDescription(), userData.getDescription());

        // Проверяем обязательные поля в форме редактирования пользователя
        app.createUsersPage.clearAllUserFields();
        app.createUsersPage.saveButtonClick();
        app.createUsersPage.shouldHaveClass(app.createUsersPage.loginFieldRequiredClass, app.createUsersPage.highlightClass);
        app.createUsersPage.shouldHaveClass(app.createUsersPage.nameFieldRequiredClass, app.createUsersPage.highlightClass);
        app.createUsersPage.shouldHaveClass(app.createUsersPage.surnameFieldRequiredClass, app.createUsersPage.highlightClass);

        // Проверяем обязательные поля в окне смены пароля пользователя
        app.createUsersPage.changepswdButtonClick();
        app.createUsersPage.saveChangepswdButtonClick();
        app.createUsersPage.shouldHaveClass(app.createUsersPage.passwordFieldRequiredClass, app.createUsersPage.highlightClass);
        app.createUsersPage.shouldHaveClass(app.createUsersPage.passwordRepeatFieldRequiredClass, app.createUsersPage.highlightClass);

        app.loginPage.open();
        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("Name");
        app.listUsersPage.editRow1ListUsersClick();
        // Проверяем что изменения не сохранились
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getLogin(), userData.getLogin());
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getName(), userData.getName());
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getSurname(), userData.getSurname());
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getFathername(), userData.getFathername());
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getEmail(), userData.getEmail());
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getGroup(), userData.getGroup());
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getDescription(), userData.getDescription());
    }

    @Test
    // 3906 Пользователи. Значения поля Логин
    public void D_valuesFieldsLogin() {
        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("Name").editRow1ListUsersClick();
        app.createUsersPage
                .loginSetValue("ЙЦУКЕН")
                .saveButtonClick()
                .shouldHaveClass(app.createUsersPage.loginFieldRequiredClass, app.createUsersPage.highlightClass);
        Assert.assertEquals(app.createUsersPage.loginFieldRequiredClassError.getText(), "Логин может содержать только латинские буквы и цифры");

        app.createUsersPage
                .loginSetValue("QWERTY")
                .saveButtonClick()
                .loginFieldRequiredClassError.shouldNot(enabled);
        app.createUsersPage.successMessage.isEnabled();

        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("Name").editRow1ListUsersClick();
        Assert.assertEquals(app.createUsersPage.loginGetValue(), "QWERTY");

        app.createUsersPage
                .loginSetValue("!\"№;%:?*()_+")
                .saveButtonClick()
                .shouldHaveClass(app.createUsersPage.loginFieldRequiredClass, app.createUsersPage.highlightClass);
        Assert.assertEquals(app.createUsersPage.loginFieldRequiredClassError.getText(), "Логин может содержать только латинские буквы и цифры");

        app.createUsersPage
                .loginSetValue("АбВThx&*^")
                .saveButtonClick()
                .shouldHaveClass(app.createUsersPage.loginFieldRequiredClass, app.createUsersPage.highlightClass);
        Assert.assertEquals(app.createUsersPage.loginFieldRequiredClassError.getText(), "Логин может содержать только латинские буквы и цифры");

        app.createUsersPage
                .loginSetValue("12345678901234567890123456789012345678901234567890")
                .saveButtonClick()
                .loginFieldRequiredClassError.shouldNot(enabled);
        app.createUsersPage.successMessage.click();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.loginGetValue(), "12345678901234567890123456789012345678901234567890");

        // Вводим 256 символов.  Сохраняется 255
        app.createUsersPage
                .loginSetValue("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456")
                .saveButtonClick()
                .loginFieldRequiredClassError.shouldNot(enabled);
        app.createUsersPage.successMessage.click();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.loginGetValue(), "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345");

        app.createUsersPage
                .loginSetValue("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345")
                .saveButtonClick()
                .loginFieldRequiredClassError.shouldNot(enabled);
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.loginGetValue(), "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345");

        app.createUsersPage
                .loginSetValue("Login")
                .saveButtonClick();
    }

    @Test
    // 4568 Пользователи. Значения поля Имя
    public void E_valuesFieldsName() {
        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("Name").editRow1ListUsersClick();
        app.createUsersPage
                .nameSetValue("ЙЦУКЕН")
                .saveButtonClick()
                .nameFieldRequiredClassError.shouldNot(enabled);
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.nameGetValue(), "ЙЦУКЕН");

        app.createUsersPage
                .nameSetValue("QWERTY")
                .saveButtonClick()
                .nameFieldRequiredClassError.shouldNot(enabled);
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.nameGetValue(), "QWERTY");

        app.createUsersPage
                .nameSetValue("!\"№;%:?*()_+")
                .saveButtonClick()
                .nameFieldRequiredClassError.shouldNot(enabled);
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.nameGetValue(), "!\"№;%:?*()_+");

        app.createUsersPage
                .nameSetValue("АбВThx&*^")
                .saveButtonClick()
                .nameFieldRequiredClassError.shouldNot(enabled);
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.nameGetValue(), "АбВThx&*^");

        app.createUsersPage
                .nameSetValue("12345678901234567890123456789012345678901234567890")
                .saveButtonClick()
                .nameFieldRequiredClassError.shouldNot(enabled);
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.nameGetValue(), "12345678901234567890123456789012345678901234567890");

        // Вводим 256 символов.  Сохраняется 255
        app.createUsersPage
                .nameSetValue("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456")
                .saveButtonClick()
                .nameFieldRequiredClassError.shouldNot(enabled);
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.nameGetValue(), "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345");

        app.createUsersPage
                .nameSetValue("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345")
                .saveButtonClick()
                .nameFieldRequiredClassError.shouldNot(enabled);
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.nameGetValue(), "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345");

        app.createUsersPage
                .nameSetValue("Name")
                .saveButtonClick();
    }

    @Test
    // 3907 Пользователи. Значения поля Фамилия
    public void F_valuesFieldsSurname() {
        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("Name").editRow1ListUsersClick();
        app.createUsersPage
                .surnameSetValue("ЙЦУКЕН")
                .saveButtonClick()
                .surnameFieldRequiredClassError.shouldNot(enabled);
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.surnameGetValue(), "ЙЦУКЕН");

        app.createUsersPage
                .surnameSetValue("QWERTY")
                .saveButtonClick()
                .surnameFieldRequiredClassError.shouldNot(enabled);
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.surnameGetValue(), "QWERTY");

        app.createUsersPage
                .surnameSetValue("!\"№;%:?*()_+")
                .saveButtonClick()
                .surnameFieldRequiredClassError.shouldNot(enabled);
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.surnameGetValue(), "!\"№;%:?*()_+");

        app.createUsersPage
                .surnameSetValue("АбВThx&*^")
                .saveButtonClick()
                .surnameFieldRequiredClassError.shouldNot(enabled);
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.surnameGetValue(), "АбВThx&*^");

        app.createUsersPage
                .surnameSetValue("12345678901234567890123456789012345678901234567890")
                .saveButtonClick()
                .surnameFieldRequiredClassError.shouldNot(enabled);
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.surnameGetValue(), "12345678901234567890123456789012345678901234567890");
        // Вводим 256 символов.  Сохраняется 255
        app.createUsersPage
                .surnameSetValue("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456")
                .saveButtonClick()
                .surnameFieldRequiredClassError.shouldNot(enabled);
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.surnameGetValue(), "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345");

        app.createUsersPage
                .surnameSetValue("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345")
                .saveButtonClick()
                .surnameFieldRequiredClassError.shouldNot(enabled);
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.surnameGetValue(), "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345");

        app.createUsersPage
                .surnameSetValue("Surname")
                .saveButtonClick();
    }

    @Test
    // 3908 Пользователи. Значения поля Описание
    public void G_valuesFieldsDescription() {
        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("Name").editRow1ListUsersClick();
        app.createUsersPage
                .descriptionSetValue("ЙЦУКЕН")
                .saveButtonClick();
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.descriptionGetValue(), "ЙЦУКЕН");

        app.createUsersPage
                .descriptionSetValue("QWERTY")
                .saveButtonClick();
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.descriptionGetValue(), "QWERTY");

        app.createUsersPage
                .descriptionSetValue("!\"№;%:?*()_+")
                .saveButtonClick();
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.descriptionGetValue(), "!\"№;%:?*()_+");

        app.createUsersPage
                .descriptionSetValue("АбВThx&*^")
                .saveButtonClick();
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.descriptionGetValue(), "АбВThx&*^");

        app.createUsersPage
                .descriptionSetValue("12345678901234567890123456789012345678901234567890")
                .saveButtonClick();
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.descriptionGetValue(), "12345678901234567890123456789012345678901234567890");
        // Вводим 256 символов.  Сохраняется 255
        app.createUsersPage
                .descriptionSetValue("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456")
                .saveButtonClick();
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.descriptionGetValue(), "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345");

        app.createUsersPage
                .descriptionSetValue("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345")
                .saveButtonClick();
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.descriptionGetValue(), "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345");

        app.createUsersPage
                .descriptionSetValue("Description")
                .saveButtonClick();
    }

    @Test
    // 4569 Пользователи. Значение поля Отчество
    public void H_valuesFieldsFathername() {
        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("Name").editRow1ListUsersClick();
        app.createUsersPage
                .fathernameSetValue("ЙЦУКЕН")
                .saveButtonClick();
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.fathernameGetValue(), "ЙЦУКЕН");

        app.createUsersPage
                .fathernameSetValue("QWERTY")
                .saveButtonClick();
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.fathernameGetValue(), "QWERTY");

        app.createUsersPage
                .fathernameSetValue("!\"№;%:?*()_+")
                .saveButtonClick();
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.fathernameGetValue(), "!\"№;%:?*()_+");

        app.createUsersPage
                .fathernameSetValue("АбВThx&*^")
                .saveButtonClick();
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.fathernameGetValue(), "АбВThx&*^");

        app.createUsersPage
                .fathernameSetValue("12345678901234567890123456789012345678901234567890")
                .saveButtonClick();
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.fathernameGetValue(), "12345678901234567890123456789012345678901234567890");
        // Вводим 256 символов.  Сохраняется 255
        app.createUsersPage
                .fathernameSetValue("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456")
                .saveButtonClick();
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.fathernameGetValue(), "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345");

        app.createUsersPage
                .fathernameSetValue("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345")
                .saveButtonClick();
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.fathernameGetValue(), "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345");

        app.createUsersPage
                .fathernameSetValue("Fathername")
                .saveButtonClick();
    }


    @Test
    // 4571 Пользователи. Проверка поля Email
    public void I_valuesFieldsEmail() {
        UsersData userData = new UsersData().withLogin("TestEmail").withName("TestEmail").withSurname("TestEmail")
                .withFathername("TestEmail").withPhone("79123456789").withPassword("TestEmail")
                .withDescription("TestEmail");
        app.mainPage.openUsers();
        app.listUsersPage.createButtonClick();
        app.createUsersPage.createUser(userData);
        app.createUsersPage.successMessage.isEnabled();

        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("TestEmail").editRow1ListUsersClick();
        app.createUsersPage
                .emailSetValue("pochta@mail.ru")
                .saveButtonClick();
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.emailGetValue(), "pochta@mail.ru");

        app.createUsersPage
                .emailSetValue("poch-ta@mail.ru")
                .saveButtonClick();
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.emailGetValue(), "poch-ta@mail.ru");

        app.createUsersPage
                .emailSetValue("pochta@ma-il.ru")
                .saveButtonClick();
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.emailGetValue(), "pochta@ma-il.ru");

        app.createUsersPage
                .emailSetValue("poch_ta@mail.ru")
                .saveButtonClick();
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.emailGetValue(), "poch_ta@mail.ru");

        app.createUsersPage
                .emailSetValue("pochta@ma_il.ru")
                .saveButtonClick();
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.emailGetValue(), "pochta@ma_il.ru");

        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("TestEmail").editRow1ListUsersClick();
        app.createUsersPage
                .emailSetValue("p.och.ta@mail.ru")
                .saveButtonClick();
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.emailGetValue(), "p.och.ta@mail.ru");

        app.createUsersPage
                .emailSetValue("pochta@mail..ru")
                .saveButtonClick()
                .shouldHaveClass(app.createUsersPage.emailFieldClass, app.createUsersPage.highlightClass);
        Assert.assertEquals(app.createUsersPage.emailFieldClassError.getText(), "Некорректный email");
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.emailGetValue(), "p.och.ta@mail.ru");

        app.createUsersPage
                .emailSetValue("pochta@mailru")
                .saveButtonClick()
                .shouldHaveClass(app.createUsersPage.emailFieldClass, app.createUsersPage.highlightClass);
        Assert.assertEquals(app.createUsersPage.emailFieldClassError.getText(), "Некорректный email");
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.emailGetValue(), "p.och.ta@mail.ru");

        app.createUsersPage
                .emailSetValue("pochtamail.ru")
                .saveButtonClick()
                .shouldHaveClass(app.createUsersPage.emailFieldClass, app.createUsersPage.highlightClass);
        Assert.assertEquals(app.createUsersPage.emailFieldClassError.getText(), "Некорректный email");
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.emailGetValue(), "p.och.ta@mail.ru");

        app.createUsersPage
                .emailSetValue("poc hta@mail.ru")
                .saveButtonClick()
                .shouldHaveClass(app.createUsersPage.emailFieldClass, app.createUsersPage.highlightClass);
        Assert.assertEquals(app.createUsersPage.emailFieldClassError.getText(), "Некорректный email");
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.emailGetValue(), "p.och.ta@mail.ru");

        app.createUsersPage
                .emailSetValue("pochta@ma il.ru")
                .saveButtonClick()
                .shouldHaveClass(app.createUsersPage.emailFieldClass, app.createUsersPage.highlightClass);
        Assert.assertEquals(app.createUsersPage.emailFieldClassError.getText(), "Некорректный email");
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.emailGetValue(), "p.och.ta@mail.ru");

        app.createUsersPage
                .emailSetValue("@mail.ru")
                .saveButtonClick()
                .shouldHaveClass(app.createUsersPage.emailFieldClass, app.createUsersPage.highlightClass);
        Assert.assertEquals(app.createUsersPage.emailFieldClassError.getText(), "Некорректный email");
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.emailGetValue(), "p.och.ta@mail.ru");

        app.createUsersPage
                .emailSetValue("pochta@")
                .saveButtonClick()
                .shouldHaveClass(app.createUsersPage.emailFieldClass, app.createUsersPage.highlightClass);
        Assert.assertEquals(app.createUsersPage.emailFieldClassError.getText(), "Некорректный email");
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.emailGetValue(), "p.och.ta@mail.ru");

        app.createUsersPage
                .emailSetValue("емейл@почта.ру")
                .saveButtonClick();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.emailGetValue(), "емейл@почта.ру");
        app.mainPage.openUsers();
        app.listUsersPage.deleteUserBySearchName("TestEmail");

        /*app.createUsersPage
                .emailSetValue("pochta@ma.il...ru")
                .saveButtonClick()
                .shouldHaveClass(app.createUsersPage.emailFieldClass, app.createUsersPage.highlightClass);
        Assert.assertEquals(app.createUsersPage.emailFieldClassError.getText(), "Некорректный email");
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.emailGetValue(), "p.och.ta@mail.ru");
        */
    }


    @Test
    // 4570 Пользователи. Проверка поля Телефон на наличие маски
    // TODO Добавить проверку маски
    public void J_valuesFieldsPhone() {
        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("Name").editRow1ListUsersClick();

        // Редактируем, в поле телефон вводим "абв"
        app.createUsersPage
                .phoneSetValue("Keys.LEFT_SHIFT, \"а\", Keys.LEFT_SHIFT, \"б\", Keys.LEFT_SHIFT, \"в\"")
                .saveButtonClick();
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.phoneGetValue(), "");
        // Вводим "werwer"
        app.createUsersPage
                .phoneSetValue("Keys.LEFT_SHIFT, \"w\", Keys.LEFT_SHIFT, \"e\", Keys.LEFT_SHIFT, \"r\",Keys.LEFT_SHIFT, \"w\", Keys.LEFT_SHIFT, \"e\", Keys.LEFT_SHIFT, \"r\"")
                .saveButtonClick();
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.phoneGetValue(), "");
        // Вводим спецсимволы
        app.createUsersPage
                .phoneSetValue("Keys.LEFT_SHIFT, \";\", Keys.LEFT_SHIFT, \"/\", Keys.LEFT_SHIFT, \"#\", Keys.LEFT_SHIFT, \"*\", Keys.LEFT_SHIFT, \"-\"")
                .saveButtonClick();
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.phoneGetValue(), "");
        // Вводим 79876543210
        app.createUsersPage
                .phoneSetValue("Keys.NUMPAD7, Keys.NUMPAD9, Keys.NUMPAD8, Keys.NUMPAD7, Keys.NUMPAD6, Keys.NUMPAD5,Keys.NUMPAD4,Keys.NUMPAD3,Keys.NUMPAD2,Keys.NUMPAD1,Keys.NUMPAD0")
                .saveButtonClick();
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.phoneGetValue(), "+79876543210");
        // Вводим 79876543210897
        app.createUsersPage
                .phoneSetValue("Keys.NUMPAD7, Keys.NUMPAD9, Keys.NUMPAD8, Keys.NUMPAD7, Keys.NUMPAD6, Keys.NUMPAD5,Keys.NUMPAD4,Keys.NUMPAD3,Keys.NUMPAD2,Keys.NUMPAD1,Keys.NUMPAD0,Keys.NUMPAD8,Keys.NUMPAD9,Keys.NUMPAD7")
                .saveButtonClick();
        app.createUsersPage.successMessage.isEnabled();
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.phoneGetValue(), "+79876543210");

    }

    @Test
    // 4572 Пользователи. Внешний вид и обязательные при создании
    public void K_fieldsCreateUser() {
        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("admin").editRow1ListUsersClick();
        app.mainPage.openUsers();
        app.listUsersPage.createButtonClick();
        app.createUsersPage
                .saveButtonClick()
                .isActivityChecked();
        // Проверяем внешний карточки нового пользователя
        // В окне присутствуют:
        //- Чек-бокс активности (по умолчанию активирована галочка)
        //- Текстовые поля (Логин, Имя, Фамилия, Отчество, Телефон, Email, Пароль, Описание)
        //- Поле Группы, при нажатии на которое появляется список заведенных в системе групп
        //- Кнопка "Сохранить"
        app.createUsersPage.checkEnabledCreateUserFields();
        app.createUsersPage.serviceAreaClick();
    }

    @Test
    // 3917 Пользователи. Проверка на уникальность есть только у поля логин
    public void L_uniqueUser() {
        UsersData userData1 = new UsersData()
                .withLogin("TestOne").withName("TestOne").withSurname("TestOne").withFathername("TestOne")
                .withPhone("Keys.NUMPAD7, Keys.NUMPAD9, Keys.NUMPAD1, Keys.NUMPAD2, Keys.NUMPAD3, Keys.NUMPAD4,Keys.NUMPAD5,Keys.NUMPAD6,Keys.NUMPAD7,Keys.NUMPAD8,Keys.NUMPAD9")
                .withEmail("email@email.com").withPassword("Deep2000").withDescription("TestOne");
        app.mainPage.openUsers();
        app.listUsersPage.createButtonClick();
        // Создаем нового пользователя с логином "TestOne"
        app.createUsersPage.createUser(userData1);
        app.createUsersPage.successMessage.isEnabled();

        // Создаем еще одного пользователя, логин прописываем "TestOne", пробуем сохранить его
        // Не успешно, появилось сообщение об ошибке
        UsersData userData2 = new UsersData()
                .withLogin("TestOne").withName("TestTwo").withSurname("TestTwo").withFathername("TestTwo")
                .withPhone("Keys.NUMPAD7, Keys.NUMPAD9, Keys.NUMPAD1, Keys.NUMPAD2, Keys.NUMPAD3, Keys.NUMPAD4,Keys.NUMPAD5,Keys.NUMPAD6,Keys.NUMPAD7,Keys.NUMPAD8,Keys.NUMPAD9")
                .withEmail("email@email.com").withPassword("Deep2000").withDescription("TestTwo");
        app.mainPage.openUsers();
        app.listUsersPage.createButtonClick();
        app.createUsersPage.createUser(userData2);
        app.createUsersPage.message.isEnabled();
        Assert.assertEquals(app.createUsersPage.messageGetValue(), "Пользователь с таким логином уже существует в системе");

        // Меняем логин на "TestTwo", сохраняем. Успешно, пользователь создан
        UsersData userData3 = new UsersData()
                .withLogin("TestTwo").withName("TestTwo").withSurname("TestTwo").withFathername("TestTwo")
                .withPhone("Keys.NUMPAD7, Keys.NUMPAD9, Keys.NUMPAD1, Keys.NUMPAD2, Keys.NUMPAD3, Keys.NUMPAD4,Keys.NUMPAD5,Keys.NUMPAD6,Keys.NUMPAD7,Keys.NUMPAD8,Keys.NUMPAD9")
                .withEmail("email@email.com").withPassword("Deep2000").withDescription("TestTwo");
        app.createUsersPage.createUser(userData3);
        app.createUsersPage.successMessage.isEnabled();
        // Редактируем пользователя "TestTwo", снова прописываем логин "TestOne", пробуем сохранить
        // Не успешно, снова нельзя сохранить,  появилось сообщение об ошибке
        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("TestTwo").editRow1ListUsersClick();
        app.createUsersPage.loginSetValue("TestOne").saveButtonClick();
        app.createUsersPage.message.isEnabled();
        Assert.assertEquals(app.createUsersPage.messageGetValue(), "Пользователь с таким логином уже существует в системе");
        Selenide.refresh();
        Assert.assertEquals(app.createUsersPage.loginGetValue(), "TestTwo");

        app.mainPage.openUsers();
        app.listUsersPage.deleteUserBySearchName("TestOne");
        app.mainPage.openUsers();
        app.listUsersPage.deleteUserBySearchName("TestTwo");
        app.mainPage.openUsers();
    }

    @Test
    // 3911 Пользователи. Ошибка авторизации
    public void M_errorAuthUser() {
        UsersData userData = new UsersData()
                .withLogin("TestAuth").withName("TestAuth").withSurname("TestAuth").withFathername("TestAuth")
                .withPhone("Keys.NUMPAD7, Keys.NUMPAD9, Keys.NUMPAD1, Keys.NUMPAD2, Keys.NUMPAD3, Keys.NUMPAD4,Keys.NUMPAD5,Keys.NUMPAD6,Keys.NUMPAD7,Keys.NUMPAD8,Keys.NUMPAD9")
                .withEmail("email@email.com").withPassword("TestAuth").withDescription("TestAuth");
        app.mainPage.openUsers();
        app.listUsersPage.createButtonClick();
        app.createUsersPage.createUser(userData);
        app.createUsersPage.successMessage.isEnabled();
        app.mainPage.openUsers();
        app.mainPage.logout();

        // Вводим неверный логин и верный пароль
        app.loginPage.open();
        app.loginPage.login("TestAuthTestAuth", "TestAuth");
        app.loginPage.errorAuthMessage.isEnabled();

        // Вводим верный логин и неверный пароль
        app.loginPage.open();
        app.loginPage.login("TestAuth", "TestAuthTestAuth");
        app.loginPage.errorAuthMessage.isEnabled();

        app.loginPage.login("TestAuth", "TestAuth");
        app.mainPage.openUsers();
        app.mainPage.logout();

        app.loginPage.open();
        app.loginPage.login(AppConfig.loginUser, AppConfig.passwordUser);
        app.mainPage.openUsers();
        app.listUsersPage.deleteUserBySearchName("TestAuth");
    }

    @Test
    // 3918 Пользователи. Регистр учитывается при вводе логина/пароля
    public void N_registerAuthUser() {
        UsersData userData = new UsersData()
                .withLogin("TestRegistr").withName("TestRegistr").withSurname("TestRegistr").withFathername("TestRegistr")
                .withPhone("Keys.NUMPAD7, Keys.NUMPAD9, Keys.NUMPAD1, Keys.NUMPAD2, Keys.NUMPAD3, Keys.NUMPAD4,Keys.NUMPAD5,Keys.NUMPAD6,Keys.NUMPAD7,Keys.NUMPAD8,Keys.NUMPAD9")
                .withEmail("email@email.com").withPassword("TestRegistr").withDescription("TestRegistr");

        app.mainPage.openUsers();
        app.listUsersPage.createButtonClick();
        app.createUsersPage.createUser(userData);
        app.createUsersPage.successMessage.isEnabled();
        app.mainPage.openUsers();
        app.mainPage.logout();

        // Вводим верный логин и неверный пароль
        app.loginPage.open();
        app.loginPage.login("TestRegistr", "Тестрегистр");
        app.loginPage.errorAuthMessage.isEnabled();

        // Вводим верный логин и верный пароль, но в неверном регистре
        app.loginPage.open();
        app.loginPage.login("TESTREGISTR", "testregistr");
        app.loginPage.errorAuthMessage.isEnabled();

        // Вводим верный логин и верный пароль
        app.loginPage.open();
        app.loginPage.login("TestRegistr", "TestRegistr");
        app.mainPage.openUsers();
        app.mainPage.logout();

        app.loginPage.open();
        app.loginPage.login(AppConfig.loginUser, AppConfig.passwordUser);
        app.mainPage.openUsers();
        app.listUsersPage.deleteUserBySearchName("TestRegistr");
    }

    /*@Test
    public void O_deleteUser() {
        app.mainPage.openUsers();
        app.listUsersPage.deleteUserBySearchName("Name");
    }
    */

    //@Test
    // 3924 Пользователи. Одновременная авторизация в разных браузерах
    /*public void sameAuthUser() {
        Configuration.browser = "opera";
        app.loginPage.open();
        app.loginPage.login(AppConfig.loginUser, AppConfig.passwordUser);
        app.mainPage.openUsers();

        Configuration.browser = "chrome";
        app.loginPage.open();
        app.loginPage.login(AppConfig.loginUser, AppConfig.passwordUser);

        app.mainPage.openUsers();
        app.mainPage.logout();
        //browser1.close();
    }*/
}
