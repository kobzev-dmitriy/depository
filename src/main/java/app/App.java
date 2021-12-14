package app;

import app.pages.LoginPage;
import app.pages.MainPage;
import app.pages.Users.CreateUsersPage;
import app.pages.Users.ListUsersPage;

public class App {

    public LoginPage loginPage;
    public MainPage mainPage;
    public CreateUsersPage createUsersPage;
    public ListUsersPage listUsersPage;

    public App() {
        loginPage = PageBuilder.buildLoginPage();
        mainPage = PageBuilder.buildMainPage();
        createUsersPage = PageBuilder.buildCreateUsersPage();
        listUsersPage = PageBuilder.buildListUsersPage();
    }
}
