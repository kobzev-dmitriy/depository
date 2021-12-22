import app.AppConfig;
import com.codeborne.selenide.Selenide;
import helpers.Driver;
import model.UsersData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UsersScenarTests extends A_BaseTest {

    @Test
    // 3897 Пользователи. Создание пользователя
    public void A_createUser() {
        UsersData userData = new UsersData()
                .withLogin("TestCreate").withName("TestCreate").withSurname("TestCreate")
                .withPassword("TestCreate").withGroup("Администратор");
        app.mainPage.openUsers();
        app.listUsersPage.createButtonClick();
        app.createUsersPage.createUser(userData);
        app.createUsersPage.successMessageClick();
        Driver.refresh();
        app.createUsersPage.checkEnabledEditUserFields();
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getLogin(), userData.getLogin());
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getName(), userData.getName());
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getSurname(), userData.getSurname());
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getGroup(), userData.getGroup());

        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("TestCreate");
        app.listUsersPage.editRow1ListUsersClick();
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getLogin(), "TestCreate");

        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("admin");
        app.listUsersPage.editRow1ListUsersClick();
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getLogin(), "admin");

        app.mainPage.openUsers();
        app.listUsersPage.deleteUserBySearchName("TestCreate");
        app.createUsersPage.successMessageClick();
    }


    @Test
    // 3898 Пользователи. Редактирование пользователя
    public void B_editUser() {
        UsersData userData = new UsersData()
                .withLogin("TestEdit").withName("TestEdit").withSurname("TestEdit").withFathername("TestEdit")
                .withPhone("Keys.NUMPAD7, Keys.NUMPAD9, Keys.NUMPAD1, Keys.NUMPAD2, Keys.NUMPAD3, Keys.NUMPAD4,Keys.NUMPAD5,Keys.NUMPAD6,Keys.NUMPAD7,Keys.NUMPAD8,Keys.NUMPAD9")
                .withEmail("email@email.com").withPassword("TestEdit").withDescription("TestEdit");
        app.mainPage.openUsers();
        app.listUsersPage.createButtonClick();
        app.createUsersPage.createUser(userData);
        app.createUsersPage.successMessageClick();


        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("TestEdit");
        app.listUsersPage.editRow1ListUsersClick();
        app.createUsersPage.activityClick();
        UsersData userData2 = new UsersData()
                .withLogin("TestEdit2").withName("TestEdit2").withSurname("TestEdit2").withFathername("TestEdit2")
                .withPhone("Keys.NUMPAD7, Keys.NUMPAD1, Keys.NUMPAD2, Keys.NUMPAD3, Keys.NUMPAD4, Keys.NUMPAD5,Keys.NUMPAD6,Keys.NUMPAD7,Keys.NUMPAD8,Keys.NUMPAD9,Keys.NUMPAD10")
                .withEmail("email2@email2.com").withPassword("TestEdit2").withDescription("TestEdit2").withGroup("Администратор");
        app.createUsersPage.editUser(userData2);
        app.createUsersPage.saveButtonClick();
        Driver.refresh();

        // Проверяем корректность изменений значений полей в форме редактирования пользователей
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getLogin(), userData2.getLogin());
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getName(), userData2.getName());
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getSurname(), userData2.getSurname());
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getFathername(), userData2.getFathername());
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getEmail(), userData2.getEmail());
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getGroup(), userData2.getGroup());
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getDescription(), userData2.getDescription());

        // Проверяем корректность изменений значений полей в списке пользователей
        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("TestEdit2");
        Assert.assertEquals(app.listUsersPage.getLoginUsersList(), userData2.getLogin());
        Assert.assertEquals(app.listUsersPage.getNameUsersList(), userData2.getName());
        Assert.assertEquals(app.listUsersPage.getSurnameUsersList(), userData2.getSurname());
        Assert.assertEquals(app.listUsersPage.getEmailUsersList(), userData2.getEmail());
        Assert.assertEquals(app.listUsersPage.getActivityUsersList(), "Нет");

        app.mainPage.openUsers();
        app.listUsersPage.deleteUserBySearchName("TestEdit2");
        app.createUsersPage.successMessageClick();

    }

    @Test
    // 3899 Пользователи. Удаление пользователя
    public void C_deleteUser() {
        UsersData userData = new UsersData()
                .withLogin("TestDelete").withName("TestDelete").withSurname("TestDelete").withFathername("TestDelete")
                .withPhone("Keys.NUMPAD7, Keys.NUMPAD9, Keys.NUMPAD1, Keys.NUMPAD2, Keys.NUMPAD3, Keys.NUMPAD4,Keys.NUMPAD5,Keys.NUMPAD6,Keys.NUMPAD7,Keys.NUMPAD8,Keys.NUMPAD9")
                .withEmail("email@email.com").withPassword("TestDelete").withDescription("TestDelete");
        app.mainPage.openUsers();
        app.listUsersPage.createButtonClick();
        app.createUsersPage.createUser(userData);
        app.createUsersPage.successMessageClick();

        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("TestDelete");
        app.listUsersPage.deleteRow1ListUsersClick();
        app.listUsersPage.denialDeleteUserClick();

        app.mainPage.openUsers();
        app.listUsersPage.deleteUserBySearchName("TestDelete");

        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("TestDelete");
        app.listUsersPage.emptySearchResultMsgEnabled();
    }

    @Test
    // 3899 Пользователи. Удаление пользователя
    public void D_deactivationUser() {
        // Создаем нового пользователя "TestActive" с ролью админ
        UsersData userData = new UsersData()
                .withLogin("TestActive").withName("TestActive").withSurname("TestActive").withFathername("TestActive")
                .withPhone("Keys.NUMPAD7, Keys.NUMPAD9, Keys.NUMPAD1, Keys.NUMPAD2, Keys.NUMPAD3, Keys.NUMPAD4,Keys.NUMPAD5,Keys.NUMPAD6,Keys.NUMPAD7,Keys.NUMPAD8,Keys.NUMPAD9")
                .withEmail("email@email.com").withPassword("TestActive").withDescription("TestActive");
        app.mainPage.openUsers();
        app.listUsersPage.createButtonClick();
        app.createUsersPage.createUser(userData);
        app.createUsersPage.successMessageClick();

        //Завершаем сессию своим админом и пробуем авторизоваться под "TestActive". Успешно, пользователь авторизовался

        app.mainPage.openUsers();
        app.mainPage.logout();

        app.loginPage.open();
        app.loginPage.login("TestActive", "TestActive");
        app.mainPage.openUsers();
        app.mainPage.logout();

        // Снимаем чек-бокс Активности, сохраняем, проверяем что в столбце "Активность"  стоит значение "Нет"
        app.loginPage.open();
        app.loginPage.login(AppConfig.loginUser, AppConfig.passwordUser);
        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("TestActive");
        app.listUsersPage.editRow1ListUsersClick();
        app.createUsersPage.activityClick();
        app.createUsersPage.saveButtonClick();

        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("TestActive");
        Assert.assertEquals(app.listUsersPage.getActivityUsersList(), "Нет");
        app.mainPage.logout();

        // Пробуем авторизоваться под "TestActive". Не успешно
        app.loginPage.open();
        app.loginPage.login("TestActive", "TestActive");
        Assert.assertEquals(app.loginPage.errorMessageGetText(), "Пользователь 'TestActive' неактивен");

        //Снова авторизуемся своим админом и возвращаем активность пользователю "TestActive"
        app.loginPage.login(AppConfig.loginUser, AppConfig.passwordUser);
        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("TestActive");
        app.listUsersPage.editRow1ListUsersClick();
        app.createUsersPage.activityClick();
        app.createUsersPage.saveButtonClick();
        app.mainPage.openUsers();
        app.mainPage.logout();

        //Снова пробуем авторизоваться под пользователем "TestActive". Успешно, пользователь авторизовался
        app.loginPage.open();
        app.loginPage.login("TestActive", "TestActive");
        app.mainPage.openUsers();
        app.mainPage.logout();

        app.loginPage.open();
        app.loginPage.login(AppConfig.loginUser, AppConfig.passwordUser);


        app.mainPage.openUsers();
        app.listUsersPage.deleteUserBySearchName("TestActive");
        app.createUsersPage.successMessageClick();
    }

    @Test
    // 3910 Пользователи. Редактирование предустановленного
    public void E_defaultUser() {
        app.mainPage.openUsers();
        // Нажать Редактировать у предустановленного администратора
        app.listUsersPage.searchUserByName(AppConfig.loginDefaultUser);
        UsersData userData = new UsersData()
                .withLogin("adminDefault").withName("adminDefault").withSurname("adminDefault").withFathername("adminDefault")
                .withPhone("Keys.NUMPAD7, Keys.NUMPAD9, Keys.NUMPAD1, Keys.NUMPAD2, Keys.NUMPAD3, Keys.NUMPAD4,Keys.NUMPAD5,Keys.NUMPAD6,Keys.NUMPAD7,Keys.NUMPAD8,Keys.NUMPAD9")
                .withEmail("adminDefault@email.com").withGroup("Администратор").withPassword("adminDefault").withDescription("adminDefault");
        app.listUsersPage.editRow1ListUsersClick();
        // Изменить значения у всех полей на новые ( в том числе и пароль), снять чекбокс активность
        app.createUsersPage.activityClick();
        app.createUsersPage.editUser(userData);
        app.createUsersPage.saveButtonClick();
        Driver.refresh();
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getLogin(), userData.getLogin());
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getName(), userData.getName());
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getSurname(), userData.getSurname());
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getFathername(), userData.getFathername());
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getEmail(), userData.getEmail());
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getGroup(), userData.getGroup());
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getDescription(), userData.getDescription());

        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("adminDefault");
        Assert.assertEquals(app.listUsersPage.getLoginUsersList(), userData.getLogin());
        Assert.assertEquals(app.listUsersPage.getNameUsersList(), userData.getName());
        Assert.assertEquals(app.listUsersPage.getSurnameUsersList(), userData.getSurname());
        Assert.assertEquals(app.listUsersPage.getEmailUsersList(), userData.getEmail());
        Assert.assertEquals(app.listUsersPage.getActivityUsersList(), "Нет");

        app.mainPage.openUsers();
        app.mainPage.logout();

        // Ввести первоначальный данные предустановленного администратора, попытаться авторизоваться. Ошибка авторизации
        app.loginPage.open();
        app.loginPage.login(AppConfig.loginDefaultUser, AppConfig.passwordDefaultUser);
        app.loginPage.errorAuthMessage.isEnabled();

        // Ввести новые данные предустановленного администратора. Сообщение "Пользователь неактивен"
        app.loginPage.open();
        app.loginPage.login("adminDefault", "adminDefault");
        Assert.assertEquals(app.loginPage.errorMessageGetText(), "Пользователь 'adminDefault' неактивен");

        // Вернуть ему активность
        app.loginPage.login(AppConfig.loginUser, AppConfig.passwordUser);
        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("adminDefault");
        app.listUsersPage.editRow1ListUsersClick();
        app.createUsersPage.activityClick();
        app.createUsersPage.saveButtonClick();
        app.mainPage.openUsers();
        app.mainPage.logout();

        // Попытаться авторизоваться с новыми данными
        app.loginPage.open();
        app.loginPage.login("adminDefault", "adminDefault");
        app.mainPage.openUsers();
        app.mainPage.logout();

        // Возвращаем предустановленному пользователю значения до изменений
        app.loginPage.open();
        app.loginPage.login(AppConfig.loginUser, AppConfig.passwordUser);
        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("adminDefault");
        UsersData userData2 = new UsersData()
                .withLogin(AppConfig.loginDefaultUser).withName(AppConfig.loginDefaultUser).withSurname(AppConfig.loginDefaultUser).withFathername(AppConfig.loginDefaultUser)
                .withPhone("Keys.NUMPAD7, Keys.NUMPAD9, Keys.NUMPAD1, Keys.NUMPAD2, Keys.NUMPAD3, Keys.NUMPAD4,Keys.NUMPAD5,Keys.NUMPAD6,Keys.NUMPAD7,Keys.NUMPAD8,Keys.NUMPAD9")
                .withEmail("adminDefault@email.com").withPassword("Deep2000").withDescription(AppConfig.loginDefaultUser);
        app.listUsersPage.editRow1ListUsersClick();
        app.createUsersPage.editUser(userData2);
        app.createUsersPage.saveButtonClick();
        app.createUsersPage.successMessageClick();
    }

    @Test
    // 3915 Пользователи. Смена пароля другому пользователю
    public void F_changePasswordAnotherUser() {
        UsersData userData = new UsersData()
                .withLogin("TestChangePassword").withName("TestChangePassword")
                .withSurname("TestChangePassword").withFathername("TestChangePassword")
                .withPhone("Keys.NUMPAD7, Keys.NUMPAD9, Keys.NUMPAD1, Keys.NUMPAD2, Keys.NUMPAD3, Keys.NUMPAD4,Keys.NUMPAD5,Keys.NUMPAD6,Keys.NUMPAD7,Keys.NUMPAD8,Keys.NUMPAD9")
                .withEmail("email0@email.com").withDescription("TestChangePassword")
                .withPassword("Deep2000");
        app.mainPage.openUsers();
        app.listUsersPage.createButtonClick();
        app.createUsersPage.createUser(userData);
        app.createUsersPage.successMessageClick();

        // В роли администратора 1 открываем Пользователи, редактируем TestChangePassword и изменяем пароль на Deep2001
        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("TestChangePassword");
        app.listUsersPage.editRow1ListUsersClick();
        app.createUsersPage.changePasswordUser("Deep2001");
        app.createUsersPage.saveButtonClick();
        app.createUsersPage.successMessageClick();
        app.createUsersPage.successMessageClick();
        app.mainPage.openUsers();
        app.mainPage.logout();

        app.loginPage.open();
        app.loginPage.login("TestChangePassword", "Deep2001");
        app.mainPage.openUsers();
        app.mainPage.logout();

        app.loginPage.open();
        app.loginPage.login(AppConfig.loginUser, AppConfig.passwordUser);
        app.mainPage.openUsers();
        app.listUsersPage.deleteUserBySearchName("TestChangePassword");
        app.createUsersPage.successMessageClick();

    }

    @Test
    // 3916 Пользователи. Смена пароля самому себе
    public void G_changePasswordMyselfUser() {
        // В системе заводим администратора TestPassword c паролем admin123
        UsersData userData = new UsersData()
                .withLogin("TestPassword").withName("TestPassword")
                .withSurname("TestPassword").withFathername("TestPassword")
                .withPhone("Keys.NUMPAD7, Keys.NUMPAD9, Keys.NUMPAD1, Keys.NUMPAD2, Keys.NUMPAD3, Keys.NUMPAD4,Keys.NUMPAD5,Keys.NUMPAD6,Keys.NUMPAD7,Keys.NUMPAD8,Keys.NUMPAD9")
                .withEmail("email0@email.com").withDescription("TestPassword")
                .withPassword("admin123");
        app.mainPage.openUsers();
        app.listUsersPage.createButtonClick();
        app.createUsersPage.createUser(userData);
        app.createUsersPage.successMessageClick();
        app.mainPage.openUsers();
        app.mainPage.logout();

        // Авторизуемся созданным администратором и меняем пароль на "123admin", сохраняем
        app.loginPage.open();
        app.loginPage.login("TestPassword", "admin123");
        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("TestPassword");
        app.listUsersPage.editRow1ListUsersClick();
        app.createUsersPage.changePasswordUser("123admin");
        app.createUsersPage.saveButtonClick();
        app.createUsersPage.successMessageClick();
        app.createUsersPage.successMessageClick();
        app.mainPage.openUsers();
        app.mainPage.logout();

        // Авторизуемся под логином admin1 с паролем admin123. Не успешно, сообщение "Неверные данные пользователя"
        app.loginPage.open();
        app.loginPage.login("TestPassword", "admin123");
        app.loginPage.errorAuthMessage.isEnabled();

        // Авторизуемся с паролем "123admin". Успешно
        app.loginPage.open();
        app.loginPage.login("TestPassword", "123admin");
        app.mainPage.openUsers();
        app.mainPage.logout();

        app.loginPage.open();
        app.loginPage.login(AppConfig.loginUser, AppConfig.passwordUser);
        app.mainPage.openUsers();
        app.listUsersPage.deleteUserBySearchName("TestPassword");
        app.createUsersPage.successMessageClick();
    }

    @Test
    // 3922 Пользователи. Создание нового с данными удаленного
    public void H_createNewWithDeleteUserData() {
        // Создать активного пользователя admindelete, зафиксировать, что введено во всех полях. Удалить его
        UsersData userData = new UsersData()
                .withLogin("admindelete").withName("admindelete")
                .withSurname("admindelete").withFathername("admindelete")
                .withPhone("Keys.NUMPAD7, Keys.NUMPAD9, Keys.NUMPAD1, Keys.NUMPAD2, Keys.NUMPAD3, Keys.NUMPAD4,Keys.NUMPAD5,Keys.NUMPAD6,Keys.NUMPAD7,Keys.NUMPAD8,Keys.NUMPAD9")
                .withEmail("email0@email.com").withDescription("admindelete")
                .withPassword("admindelete");
        app.mainPage.openUsers();
        app.listUsersPage.createButtonClick();
        app.createUsersPage.createUser(userData);
        app.createUsersPage.successMessageClick();

        app.mainPage.openUsers();
        app.listUsersPage.deleteUserBySearchName("admindelete");
        app.createUsersPage.successMessageClick();
        app.mainPage.openUsers();
        app.mainPage.logout();

        // Попытаться авторизоваться удаленным пользователем. Ошибка авторизации
        app.loginPage.open();
        app.loginPage.login("admindelete", "admindelete");
        app.loginPage.errorAuthMessage.isEnabled();

        // Создать абсолютно такого же нового пользователя
        app.loginPage.open();
        app.loginPage.login(AppConfig.loginUser, AppConfig.passwordUser);
        app.mainPage.openUsers();
        app.listUsersPage.createButtonClick();
        app.createUsersPage.createUser(userData);
        app.createUsersPage.successMessageClick();
        app.mainPage.openUsers();
        app.mainPage.logout();
        // Авторизоваться им. Успешно
        app.loginPage.open();
        app.loginPage.login("admindelete", "admindelete");
        app.mainPage.openUsers();
        app.mainPage.logout();

        app.loginPage.open();
        app.loginPage.login(AppConfig.loginUser, AppConfig.passwordUser);
        app.mainPage.openUsers();
        app.listUsersPage.deleteUserBySearchName("admindelete");
        app.createUsersPage.successMessageClick();
    }

    @Test
    // 3904 Пользователи. Наличие полей, внешний вид модуля
    public void I_formModuleUsers() {
        app.mainPage.openUsers();
        Driver.refresh();
        Assert.assertTrue(app.listUsersPage.countSearchResult() > 0);
        app.listUsersPage.checkEnabledListUsersFields();
    }

    @Test
    // 4521 Пользователи. Поиск по имени
    public void J_searchByName() {
        UsersData userData1 = new UsersData()
                .withLogin("TestSearch1").withName("TestSearch1").withSurname("TestSearch1").withFathername("TestSearch1")
                .withPhone("Keys.NUMPAD7, Keys.NUMPAD9, Keys.NUMPAD1, Keys.NUMPAD2, Keys.NUMPAD3, Keys.NUMPAD4,Keys.NUMPAD5,Keys.NUMPAD6,Keys.NUMPAD7,Keys.NUMPAD8,Keys.NUMPAD9")
                .withEmail("TestSearch1@email.com").withPassword("TestSearch1").withDescription("TestSearch1");
        app.mainPage.openUsers();
        app.listUsersPage.createButtonClick();
        app.createUsersPage.createUser(userData1);
        app.createUsersPage.successMessageClick();

        UsersData userData2 = new UsersData()
                .withLogin("TestSearch2").withName("TestSearch2").withSurname("TestSearch2").withFathername("TestSearch2")
                .withPhone("Keys.NUMPAD7, Keys.NUMPAD9, Keys.NUMPAD1, Keys.NUMPAD2, Keys.NUMPAD3, Keys.NUMPAD4,Keys.NUMPAD5,Keys.NUMPAD6,Keys.NUMPAD7,Keys.NUMPAD9,Keys.NUMPAD8")
                .withEmail("TestSearch2@email.com").withPassword("TestSearch2").withDescription("TestSearch2");
        app.mainPage.openUsers();
        app.listUsersPage.createButtonClick();
        app.createUsersPage.createUser(userData2);
        app.createUsersPage.successMessageClick();

        app.mainPage.openUsers();
        int countEmptySearch = app.listUsersPage.countSearchResult();

        app.listUsersPage.searchUserByName("TestSearch");
        Assert.assertEquals(app.listUsersPage.countSearchResult(), 2);
        app.listUsersPage.searchUserByName("TestSearch1");
        Assert.assertEquals(app.listUsersPage.countSearchResult(), 1);
        app.listUsersPage.searchUserByName("TestSearch2");
        Assert.assertEquals(app.listUsersPage.countSearchResult(), 1);

        app.listUsersPage.searchUserByName("");
        Assert.assertEquals(app.listUsersPage.countSearchResult(), countEmptySearch);

        app.listUsersPage.searchUserByName("RD6idrfwsNgfdwkfwj");
        app.listUsersPage.emptySearchResultMsgEnabled();
        Assert.assertEquals(app.listUsersPage.countSearchResult(), 0);

        app.listUsersPage.searchUserByName("");
        Assert.assertEquals(app.listUsersPage.countSearchResult(), countEmptySearch);

        app.mainPage.openUsers();
        app.listUsersPage.deleteUserBySearchName("TestSearch1");
        app.createUsersPage.successMessageClick();
        app.mainPage.openUsers();
        app.listUsersPage.deleteUserBySearchName("TestSearch2");
        app.createUsersPage.successMessageClick();
    }

    @Test
    // 4617 Пользователи. Обновление данных в столбце "Последняя авторизация" пользователя
    public void K_lastAccessUser() {
        UsersData userData = new UsersData()
                .withLogin("TestTime").withName("TestTime").withSurname("TestTime").withFathername("TestTime")
                .withPhone("Keys.NUMPAD7, Keys.NUMPAD9, Keys.NUMPAD1, Keys.NUMPAD2, Keys.NUMPAD3, Keys.NUMPAD4,Keys.NUMPAD5,Keys.NUMPAD6,Keys.NUMPAD7,Keys.NUMPAD8,Keys.NUMPAD9")
                .withEmail("TestTime@email.com").withPassword("TestTime").withDescription("TestTime");
        app.mainPage.openUsers();
        app.listUsersPage.createButtonClick();
        app.createUsersPage.createUser(userData);
        app.createUsersPage.successMessageClick();

        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("TestTime");
        Assert.assertEquals(app.listUsersPage.lastaccessUsersList(), "");
        app.mainPage.logout();

        app.loginPage.open();
        app.loginPage.login("TestTime", "TestTime");
        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("TestTime");
        Assert.assertTrue(app.listUsersPage.lastaccessUsersList() != "");
        app.mainPage.logout();

        app.loginPage.open();
        app.loginPage.login(AppConfig.loginUser, AppConfig.passwordUser);
        app.mainPage.openUsers();
        app.listUsersPage.deleteUserBySearchName("TestTime");
        app.createUsersPage.successMessageClick();
    }

    @Test
    // 4617 Пользователи. Обновление данных в столбце "Последняя авторизация" пользователя
    public void M_sortUsers() {
        UsersData firstUserData = new UsersData()
                .withLogin("00000000").withName("00000000").withSurname("00000000").withFathername("00000000")
                .withPhone("Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0")
                .withEmail("00000000@email.com").withPassword("Deep2000").withDescription("00000000");
        app.mainPage.openUsers();
        app.listUsersPage.createButtonClick();
        app.createUsersPage.createUser(firstUserData);
        app.createUsersPage.successMessageClick();

        UsersData lastUserData = new UsersData()
                .withLogin("zzzzzzzzz").withName("яяяяяяяя").withSurname("яяяяяяяя").withFathername("яяяяяяяя")
                .withPhone("Keys.NUMPAD9, Keys.NUMPAD9, Keys.NUMPAD9, Keys.NUMPAD9, Keys.NUMPAD9, Keys.NUMPAD9,Keys.NUMPAD9,Keys.NUMPAD9,Keys.NUMPAD9,Keys.NUMPAD9,Keys.NUMPAD9")
                .withEmail("яяяяяяяя@email.com").withPassword("Deep2000").withDescription("яяяяяяяя");
        app.mainPage.openUsers();
        app.listUsersPage.createButtonClick();
        app.createUsersPage.createUser(lastUserData);
        app.createUsersPage.successMessageClick();

        app.mainPage.openUsers();
        app.listUsersPage.idUsersSort();
        app.listUsersPage.idUsersSort();
        Assert.assertEquals(app.listUsersPage.getLoginUsersList(), lastUserData.getLogin());
        Assert.assertEquals(app.listUsersPage.getNameUsersList(), lastUserData.getName());
        Assert.assertEquals(app.listUsersPage.getSurnameUsersList(), lastUserData.getSurname());

        app.listUsersPage.loginUsersSort();
        Assert.assertEquals(app.listUsersPage.getLoginUsersList(), firstUserData.getLogin());
        Assert.assertEquals(app.listUsersPage.getNameUsersList(), firstUserData.getName());
        Assert.assertEquals(app.listUsersPage.getSurnameUsersList(), firstUserData.getSurname());

        app.listUsersPage.loginUsersSort();
        Assert.assertEquals(app.listUsersPage.getLoginUsersList(), lastUserData.getLogin());
        Assert.assertEquals(app.listUsersPage.getNameUsersList(), lastUserData.getName());
        Assert.assertEquals(app.listUsersPage.getSurnameUsersList(), lastUserData.getSurname());

        app.listUsersPage.nameUsersSort();
        Assert.assertEquals(app.listUsersPage.getLoginUsersList(), firstUserData.getLogin());
        Assert.assertEquals(app.listUsersPage.getNameUsersList(), firstUserData.getName());
        Assert.assertEquals(app.listUsersPage.getSurnameUsersList(), firstUserData.getSurname());

        app.listUsersPage.nameUsersSort();
        Assert.assertEquals(app.listUsersPage.getLoginUsersList(), lastUserData.getLogin());
        Assert.assertEquals(app.listUsersPage.getNameUsersList(), lastUserData.getName());
        Assert.assertEquals(app.listUsersPage.getSurnameUsersList(), lastUserData.getSurname());

        app.listUsersPage.surnameUsersSort();
        Assert.assertEquals(app.listUsersPage.getLoginUsersList(), firstUserData.getLogin());
        Assert.assertEquals(app.listUsersPage.getNameUsersList(), firstUserData.getName());
        Assert.assertEquals(app.listUsersPage.getSurnameUsersList(), firstUserData.getSurname());

        app.listUsersPage.surnameUsersSort();
        Assert.assertEquals(app.listUsersPage.getLoginUsersList(), lastUserData.getLogin());
        Assert.assertEquals(app.listUsersPage.getNameUsersList(), lastUserData.getName());
        Assert.assertEquals(app.listUsersPage.getSurnameUsersList(), lastUserData.getSurname());

        app.mainPage.openUsers();
        app.listUsersPage.deleteUserBySearchName("00000000");
        app.createUsersPage.successMessageClick();
        app.mainPage.openUsers();
        app.listUsersPage.deleteUserBySearchName("яяяяяяяя");
        app.createUsersPage.successMessageClick();
    }

    @Test
    // 3919 Пользователи. Парольная политика. Значение поля пароль
    public void N_passwordValuesUsers() {
        UsersData userData1 = new UsersData()
                .withLogin("TestPassword").withName("TestPassword").withSurname("TestPassword").withFathername("TestPassword")
                .withPhone("Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0")
                .withEmail("TestPassword@email.com").withPassword("Pass").withDescription("TestPassword");
        app.mainPage.openUsers();
        app.listUsersPage.createButtonClick();
        app.createUsersPage.createUser(userData1);
        app.createUsersPage.messageErrorClick();

        UsersData userData2 = new UsersData()
                .withLogin("TestPassword2").withName("TestPassword2").withSurname("TestPassword2").withFathername("TestPassword2")
                .withPhone("Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0")
                .withEmail("TestPassword2@email.com").withPassword("LongPassword").withDescription("TestPassword2");
        app.createUsersPage.createUser(userData2);
        app.createUsersPage.successMessageClick();
        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("TestPassword2");
        Assert.assertEquals(app.listUsersPage.getLoginUsersList(), "TestPassword2");

        app.mainPage.openUsers();
        app.listUsersPage.createButtonClick();
        UsersData userData3 = new UsersData()
                .withLogin("TestPassword3").withName("TestPassword3").withSurname("TestPassword3").withFathername("TestPassword3")
                .withPhone("Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0")
                .withEmail("TestPassword3@email.com").withPassword("12345678").withDescription("TestPassword3");
        app.createUsersPage.createUser(userData3);
        app.createUsersPage.successMessageClick();
        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("TestPassword3");
        Assert.assertEquals(app.listUsersPage.getLoginUsersList(), "TestPassword3");

        app.mainPage.openUsers();
        app.listUsersPage.createButtonClick();
        UsersData userData4 = new UsersData()
                .withLogin("TestPassword4").withName("TestPassword4").withSurname("TestPassword4").withFathername("TestPassword4")
                .withPhone("Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0")
                .withEmail("TestPassword4@email.com").withPassword("«»«»«»«»").withDescription("TestPassword3");
        app.mainPage.openUsers();
        app.listUsersPage.createButtonClick();
        app.createUsersPage.createUser(userData4);
        app.createUsersPage.messageErrorClick();

        UsersData userData5 = new UsersData().withPassword("汉字汉字4321");
        app.createUsersPage.passwordSetValue(userData5.getPassword());
        app.createUsersPage.saveButtonClick();
        app.createUsersPage.messageErrorClick();

        UsersData userData6 = new UsersData().withPassword("PassВорд汉字汉1298");
        app.createUsersPage.passwordSetValue(userData6.getPassword());
        app.createUsersPage.saveButtonClick();
        app.createUsersPage.messageErrorClick();

        UsersData userData7 = new UsersData().withPassword("««汉字1298»»");
        app.createUsersPage.passwordSetValue(userData7.getPassword());
        app.createUsersPage.saveButtonClick();
        app.createUsersPage.messageErrorClick();

        UsersData userData8 = new UsersData().withPassword("1234Pass");
        app.createUsersPage.passwordSetValue(userData8.getPassword());
        app.createUsersPage.saveButtonClick();
        app.createUsersPage.successMessageClick();

        UsersData userData9 = new UsersData()
                .withLogin("TestPassword9").withName("TestPassword9").withSurname("TestPassword9").withFathername("TestPassword9")
                .withPhone("Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0")
                .withEmail("TestPassword9@email.com").withPassword("!\\\\\\PassВорд1298///!s").withDescription("TestPassword9");
        app.mainPage.openUsers();
        app.listUsersPage.createButtonClick();
        app.createUsersPage.createUser(userData9);
        app.createUsersPage.successMessageClick();
        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("TestPassword9");
        Assert.assertEquals(app.listUsersPage.getLoginUsersList(), "TestPassword9");

        app.mainPage.openUsers();
        app.listUsersPage.deleteUserBySearchName("TestPassword2");
        app.createUsersPage.successMessageClick();
        app.mainPage.openUsers();
        app.listUsersPage.deleteUserBySearchName("TestPassword3");
        app.createUsersPage.successMessageClick();
        app.mainPage.openUsers();
        app.listUsersPage.deleteUserBySearchName("TestPassword4");
        app.createUsersPage.successMessageClick();
        app.mainPage.openUsers();
        app.listUsersPage.deleteUserBySearchName("TestPassword9");
        app.createUsersPage.successMessageClick();
    }

    @Test
    // 4514 Пользователи. Парольная политика. Проверка отсутствия ограничений на устойчивые выражения
    public void O_passwordValuesUsers() {
        UsersData userData1 = new UsersData()
                .withLogin("TestPassword1").withName("TestPassword1").withSurname("TestPassword1").withFathername("TestPassword1")
                .withPhone("Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0")
                .withEmail("TestPassword1@email.com").withPassword("QWERTY12").withDescription("TestPassword1");
        app.mainPage.openUsers();
        app.listUsersPage.createButtonClick();
        app.createUsersPage.createUser(userData1);
        app.createUsersPage.successMessageClick();

        app.mainPage.logout();
        app.loginPage.open();
        app.loginPage.login("TestPassword1", "QWERTY12");
        app.mainPage.logout();

        app.loginPage.open();
        app.loginPage.login(AppConfig.loginUser, AppConfig.passwordUser);
        UsersData userData2 = new UsersData()
                .withLogin("TestPassword2").withName("TestPassword2").withSurname("TestPassword2").withFathername("TestPassword2")
                .withPhone("Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0")
                .withEmail("TestPassword2@email.com").withPassword("123456qazwsx").withDescription("TestPassword2");
        app.mainPage.openUsers();
        app.listUsersPage.createButtonClick();
        app.createUsersPage.createUser(userData2);
        app.createUsersPage.successMessageClick();

        app.mainPage.logout();
        app.loginPage.open();
        app.loginPage.login("TestPassword2", "123456qazwsx");
        app.mainPage.logout();

        app.loginPage.open();
        app.loginPage.login(AppConfig.loginUser, AppConfig.passwordUser);
        UsersData userData3 = new UsersData()
                .withLogin("TestPassword3").withName("TestPassword3").withSurname("TestPassword3").withFathername("TestPassword3")
                .withPhone("Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0")
                .withEmail("TestPassword3@email.com").withPassword("Password9876").withDescription("TestPassword3");
        app.mainPage.openUsers();
        app.listUsersPage.createButtonClick();
        app.createUsersPage.createUser(userData3);
        app.createUsersPage.successMessageClick();

        app.mainPage.logout();
        app.loginPage.open();
        app.loginPage.login("TestPassword3", "Password9876");
        app.mainPage.logout();

        app.loginPage.open();
        app.loginPage.login(AppConfig.loginUser, AppConfig.passwordUser);
        app.mainPage.openUsers();
        app.listUsersPage.deleteUserBySearchName("TestPassword1");
        app.createUsersPage.successMessageClick();
        app.mainPage.openUsers();
        app.listUsersPage.deleteUserBySearchName("TestPassword2");
        app.createUsersPage.successMessageClick();
        app.mainPage.openUsers();
        app.listUsersPage.deleteUserBySearchName("TestPassword3");
        app.createUsersPage.successMessageClick();
    }

    @Test
    // 4515 Пользователи. Парольная политика. Проверка смены пароля
    public void P_passwordChangeUsers() {
        UsersData userData1 = new UsersData()
                .withLogin("TestPassword").withName("TestPassword").withSurname("TestPassword").withFathername("TestPassword")
                .withPhone("Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0")
                .withEmail("TestPassword@email.com").withPassword("Password123").withDescription("TestPassword");
        app.mainPage.openUsers();
        app.listUsersPage.createButtonClick();
        app.createUsersPage.createUser(userData1);
        app.createUsersPage.successMessageClick();

        app.mainPage.logout();
        app.loginPage.open();
        app.loginPage.login("TestPassword", "Password123");
        app.mainPage.logout();

        app.loginPage.open();
        app.loginPage.login(AppConfig.loginUser, AppConfig.passwordUser);
        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("TestPassword");
        app.listUsersPage.editRow1ListUsersClick();
        app.createUsersPage
                .changepswdButtonClick()
                .passwordSetValue("««Pass1298»»")
                .passwordRepeatSetValue("««Pass1298»»")
                .saveChangepswdButtonClick();
        app.createUsersPage.messageErrorClick();

        app.createUsersPage
                .passwordSetValue("123Пароль")
                .passwordRepeatSetValue("")
                .saveChangepswdButtonClick();
        app.createUsersPage.shouldHaveClass(app.createUsersPage.passwordRepeatFieldRequiredClass, app.createUsersPage.highlightClass);
        app.createUsersPage.serviceAreaClick();

        app.mainPage.openUsers();
        app.mainPage.logout();
        app.loginPage.open();
        app.loginPage.login("TestPassword", "123Пароль");
        Assert.assertEquals(app.loginPage.errorMessageGetText(), "Неверные учетные данные пользователя");

        app.loginPage.open();
        app.loginPage.login(AppConfig.loginUser, AppConfig.passwordUser);
        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("TestPassword");
        app.listUsersPage.editRow1ListUsersClick();
        app.createUsersPage
                .changepswdButtonClick()
                .passwordSetValue("")
                .passwordRepeatSetValue("123Пароль")
                .saveChangepswdButtonClick();
        app.createUsersPage.shouldHaveClass(app.createUsersPage.passwordFieldRequiredClass, app.createUsersPage.highlightClass);
        app.createUsersPage.serviceAreaClick();

        app.mainPage.openUsers();
        app.mainPage.logout();
        app.loginPage.open();
        app.loginPage.login("TestPassword", "123Пароль");
        Assert.assertEquals(app.loginPage.errorMessageGetText(), "Неверные учетные данные пользователя");

        app.loginPage.open();
        app.loginPage.login(AppConfig.loginUser, AppConfig.passwordUser);
        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("TestPassword");
        app.listUsersPage.editRow1ListUsersClick();
        app.createUsersPage
                .changepswdButtonClick()
                .passwordSetValue("123Пароль")
                .passwordRepeatSetValue("123Пароль")
                .saveChangepswdButtonClick()
                .successMessageClick();

        app.mainPage.openUsers();
        app.mainPage.logout();
        app.loginPage.open();
        app.loginPage.login("TestPassword", "Password123");
        Assert.assertEquals(app.loginPage.errorMessageGetText(), "Неверные учетные данные пользователя");

        app.loginPage.login("TestPassword", "123Пароль");
        app.mainPage.openUsers();
        app.mainPage.logout();

        app.loginPage.open();
        app.loginPage.login(AppConfig.loginUser, AppConfig.passwordUser);

        app.mainPage.openUsers();
        app.listUsersPage.deleteUserBySearchName("TestPassword");
    }

    @Test
    // 4516 Пользователи.Парольная политика.Проверка кол -ва допустимых одинаковых символов в пароле
    public void R_countSymbolsPasswordUsers() {
        UsersData userData1 = new UsersData()
                .withLogin("TestCountSymbols").withName("TestCountSymbols").withSurname("TestCountSymbols").withFathername("TestCountSymbols")
                .withPhone("Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0,Keys.NUMPAD0")
                .withEmail("TestCountSymbols@email.com").withPassword("QW3QW3QW3").withDescription("TestCountSymbols");
        app.mainPage.openUsers();
        app.listUsersPage.createButtonClick();
        app.createUsersPage.createUser(userData1);
        app.createUsersPage.messageErrorClick();

        app.createUsersPage
                .passwordSetValue("qw3qw3qw3")
                .saveButtonClick()
                .messageErrorClick();

        app.createUsersPage
                .passwordSetValue("qw3Rqw3qw")
                .saveButtonClick()
                .successMessageClick();

        app.mainPage.openUsers();
        app.mainPage.logout();
        app.loginPage.open();
        app.loginPage.login("TestCountSymbols", "qw3Rqw3qw");
        app.mainPage.logout();

        app.loginPage.open();
        app.loginPage.login(AppConfig.loginUser, AppConfig.passwordUser);
        app.mainPage.openUsers();
        app.listUsersPage.deleteUserBySearchName("TestCountSymbols");
    }

    @Test
    // 3920 Пользователи. Создание, авторизация 25+
    public void Q_createLoginUser25plus() {
        // Предусловие: В системе предварительно заведено минимум 27 пользователей
        UsersData userData = new UsersData()
                .withLogin("zzzzzzzzz").withName("zzzzzzzzz").withSurname("zzzzzzzzz").withFathername("zzzzzzzzz")
                .withPhone("Keys.NUMPAD9, Keys.NUMPAD9, Keys.NUMPAD9, Keys.NUMPAD9, Keys.NUMPAD9, Keys.NUMPAD9,Keys.NUMPAD9,Keys.NUMPAD9,Keys.NUMPAD9,Keys.NUMPAD9,Keys.NUMPAD9")
                .withEmail("zzzzzzzzz@email.com").withPassword("Deep2000").withDescription("zzzzzzzzz");
        app.mainPage.openUsers();
        app.listUsersPage.createButtonClick();
        app.createUsersPage.createUser(userData);
        app.createUsersPage.successMessageClick();
        app.mainPage.openUsers();
        app.mainPage.logout();

        app.loginPage.open();
        app.loginPage.login("zzzzzzzzz", "Deep2000");
        app.mainPage.logout();

        app.loginPage.open();
        app.loginPage.login(AppConfig.loginUser, AppConfig.passwordUser);
        app.mainPage.openUsers();
        app.listUsersPage.deleteUserBySearchName("zzzzzzzzz");
    }

    @Test
    // 3960 Пользователи. Изменение 25+
    public void Q_changeUser25plus() {
        // В системе предварительно заведено минимум 27 пользователей
        UsersData userData = new UsersData()
                .withLogin("zzzzzzzzz").withName("zzzzzzzzz").withSurname("zzzzzzzzz").withFathername("zzzzzzzzz")
                .withPhone("Keys.NUMPAD9, Keys.NUMPAD9, Keys.NUMPAD9, Keys.NUMPAD9, Keys.NUMPAD9, Keys.NUMPAD9,Keys.NUMPAD9,Keys.NUMPAD9,Keys.NUMPAD9,Keys.NUMPAD9,Keys.NUMPAD9")
                .withEmail("zzzzzzzzz@email.com").withPassword("Deep2000").withDescription("zzzzzzzzz");
        app.mainPage.openUsers();
        app.listUsersPage.createButtonClick();
        app.createUsersPage.createUser(userData);
        app.createUsersPage.successMessageClick();

        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("zzzzzzzzz");
        app.listUsersPage.editRow1ListUsersClick();
        UsersData userData2 = new UsersData()
                .withLogin("zzzzzzzzz2").withName("zzzzzzzzz2").withSurname("zzzzzzzzz2").withFathername("zzzzzzzzz2")
                .withPhone("Keys.NUMPAD7, Keys.NUMPAD9, Keys.NUMPAD8, Keys.NUMPAD7, Keys.NUMPAD4, Keys.NUMPAD5,Keys.NUMPAD6,Keys.NUMPAD7,Keys.NUMPAD8,Keys.NUMPAD9,Keys.NUMPAD10")
                .withEmail("email3@email3.com").withPassword("Deep2000").withDescription("zzzzzzzzz2").withGroup("Администратор");
        app.createUsersPage.editUser(userData2);
        app.createUsersPage.saveButtonClick();
        Driver.refresh();

        // Проверяем корректность изменений значений полей в форме редактирования пользователей
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getLogin(), userData2.getLogin());
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getName(), userData2.getName());
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getSurname(), userData2.getSurname());
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getFathername(), userData2.getFathername());
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getEmail(), userData2.getEmail());
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getGroup(), userData2.getGroup());
        Assert.assertEquals(app.createUsersPage.infoFromEditUserForm().getDescription(), userData2.getDescription());

        // Проверяем корректность изменений значений полей в списке пользователей
        app.mainPage.openUsers();
        app.listUsersPage.searchUserByName("zzzzzzzzz2");
        Assert.assertEquals(app.listUsersPage.getLoginUsersList(), userData2.getLogin());
        Assert.assertEquals(app.listUsersPage.getNameUsersList(), userData2.getName());
        Assert.assertEquals(app.listUsersPage.getSurnameUsersList(), userData2.getSurname());
        Assert.assertEquals(app.listUsersPage.getEmailUsersList(), userData2.getEmail());
        Assert.assertEquals(app.listUsersPage.getActivityUsersList(), "Да");

        app.mainPage.openUsers();
        app.mainPage.logout();

        app.loginPage.open();
        app.loginPage.login("zzzzzzzzz2", "Deep2000");
        app.mainPage.logout();

        app.loginPage.open();
        app.loginPage.login(AppConfig.loginUser, AppConfig.passwordUser);
        app.mainPage.openUsers();
        app.listUsersPage.deleteUserBySearchName("zzzzzzzzz2");
    }
}
