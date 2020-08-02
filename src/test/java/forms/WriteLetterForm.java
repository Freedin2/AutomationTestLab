package forms;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import pages.HomePage;
import ru.yandex.qatools.htmlelements.annotations.Name;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class WriteLetterForm {
    protected final static Logger LOG = Logger.getLogger(HomePage.class);

    public WriteLetterForm() {
        checkFormAppear();
    }

    @Name("Форма 'Написать письмо'")
    private SelenideElement formWriteLetter = $(".dimmer");

    @Name("Поле 'Кому:'")
    private SelenideElement whoField = $(byXpath("//input[@tabindex='100']"));

    @Name("Поле 'Тема:'")
    private SelenideElement themeField = $(byXpath("//input[@name='Subject']"));

    @Name("Локатор для Body письма")
    private SelenideElement letterField = $(byXpath("//div[@role='textbox']")); //  (´• ω •)ﾉ

    @Name("Кнопка 'Отправить'")
    private SelenideElement buttonSend = $(byXpath("//span[@class='button2__txt'][contains(.,'Отправить')]"));

    @Name("Статус отправки письма")
    private SelenideElement sendStatus = $(".layer__header");


    @Step("Проверка, что форма отправки письма открылась")
    private void checkFormAppear() {
        formWriteLetter.waitUntil(visible, 10000);
        LOG.info("Форма 'Написать письмо' открылась");
    }

    @Step("Заполняем поля Кому, Тема, вводим текст письма и нажимаем кнопку 'Отправить'")
    public void writeAndSendLetter(String who, String theme, String letter) {
        LOG.info("Кому: " + who);
        whoField.setValue(who);
        LOG.info("Тема: " + theme);
        themeField.setValue(theme);
        LOG.info("Текст письма: " + letter);
        letterField.setValue(letter);
        LOG.info("Нажимаем кнопку 'Отправить'");
        buttonSend.click();
    }

    public Boolean checkSendStatus() {
        if (sendStatus.getText().equals("Письмо отправлено"))
            return true;
        else
            return false;
    }
}
