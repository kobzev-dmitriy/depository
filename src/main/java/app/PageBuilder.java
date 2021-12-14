package app;

import app.pages.LoginPage;
import app.pages.MainPage;
import app.pages.Users.CreateUsersPage;
import app.pages.Users.ListUsersPage;

public class PageBuilder {

    public static LoginPage buildLoginPage() {
        return new LoginPage("/#/");
    }

    public static MainPage buildMainPage() {
        return new MainPage("/#/");
    }

    public static CreateUsersPage buildCreateUsersPage() {
        return new CreateUsersPage("/#/user/new");
    }

    public static ListUsersPage buildListUsersPage() {
        return new ListUsersPage("/#/user");
    }
}
