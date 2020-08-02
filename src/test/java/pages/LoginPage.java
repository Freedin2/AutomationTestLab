package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.testng.Assert;
import ru.yandex.qatools.htmlelements.annotations.Name;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    protected final static Logger LOG = Logger.getLogger(LoginPage.class);

    @Name("поле 'Логин'")
    private SelenideElement loginField = $(byId("mailbox:login"));

    @Name("поле 'Домен'")
    public SelenideElement domainField = $(byId("mailbox:domain"));//$(byId("mailbox:domain"));

    @Name("поле 'Пароль'")
    private SelenideElement passwordField = $(byId("mailbox:password"));

    @Name("Кнопка 'Ввести пароль'")
    private SelenideElement enterButton = $(byXpath("//input[@value='Ввести пароль']"));

    @Name("Сообщение о неверной авторизации 'Неправильный логин или пароль'")
    private SelenideElement authErrorMessage = $(byId("mailbox:error"));

    public HomePage userLogInToPage(String user, String domain, String password, String url) {
        open(url);
        if (WebDriverRunner.url().toLowerCase().contains(url)) {
            Selenide.clearBrowserCookies();
            Selenide.refresh();
        }
        logIn(user, domain, password);
        return new HomePage();
    }

    @Step("Авторизация")
    public void logIn(String user, String domain, String password) {
        LOG.info("Логинимся в систему пользователем: " + user + domain);
        Selenide.refresh();
        LOG.info("Вводим логин " + user);
        loginField.waitUntil(Condition.visible, 5000).setValue(user);
        LOG.info("Вводим домен " + domain);
        if (!domainField.getSelectedText().equals(domain))
            domainField.selectOptionContainingText(domain);
        Assert.assertTrue($(domainField).getSelectedText().equals(domain));
        enterButton.click();
        LOG.info("Вводим пароль " + password);
        passwordField.waitUntil(Condition.visible, 5000).setValue(password);
        enterButton.click();
    }

}