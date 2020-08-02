import forms.WriteLetterForm;
import helpers.BaseSelenideTest;
import helpers.Config;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;


@Epic("Тестирование")
@Feature("Тест1")


public class Test1 extends BaseSelenideTest {


    String url = Config.getInstance().getMailUrl();

    String user = Config.getInstance().getMailUser();

    String password = Config.getInstance().getMailPassword();

    String domain = Config.MAIL_BK_DOMAIN;

    LoginPage loginPage = new LoginPage();
    HomePage homePage;
    WriteLetterForm writeLetterForm;


    @TmsLink("Тест1")
    @Test(description = "Написать тестовый проект с использованием Java, Selenium, jUnit/TestNG и Page object паттерна и любого сборщика. " +
            "Тест должен уметь следующее: залогиниться на mail.ru; написать письмо любого содержания c заполнением поля Body (текста самого письма); " +
            "отправить письмо. Проверка доставки письма не нужна, только отправка."
    )
    public void Test1() {
        LOG.info("Шаг №1");
        LOG.info("Пользователь заходит на сайт mail.ru и логинится на нем");
        homePage = loginPage.userLogInToPage(user, domain, password, url);

        LOG.info("Шаг №2");
        LOG.info("Нажимаем на кнопку 'Написать письмо'");
        writeLetterForm = homePage.openWriteLetter();

        LOG.info("Шаг №3");
        LOG.info("Заполняем поля Кому, Тема и вводим текст письма");
        writeLetterForm.writeAndSendLetter(user + domain, "Тестовое письмо", " (^˵◕ω◕˵^) ");

        LOG.info("Шаг №4");
        LOG.info("Проверяем, отправилось ли письмо");
        Assert.assertTrue(writeLetterForm.checkSendStatus());

        LOG.info("Тест пройден!");
    }
}