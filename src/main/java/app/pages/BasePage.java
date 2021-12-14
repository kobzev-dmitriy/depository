package app.pages;
import app.AppConfig;
import com.codeborne.selenide.Selenide;
import helpers.Driver;
import helpers.Trim;

public abstract class BasePage {

    protected String pageUrl;

    public BasePage(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public void open() {
        String url = Trim.rtrim(AppConfig.baseUrl, "/") + "/" + Trim.ltrim(pageUrl, "/");
        Selenide.open(url);
        Driver.wait(1);
    }
}
