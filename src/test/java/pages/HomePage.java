package pages;

import com.codeborne.selenide.SelenideElement;
import forms.WriteLetterForm;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import ru.yandex.qatools.htmlelements.annotations.Name;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    protected final static Logger LOG = Logger.getLogger(HomePage.class);

    public HomePage() {
        checkPageAppear();
    }

    @Name("Кнопка 'Написать письмо'")
    private SelenideElement buttonWriteLetter = $(byXpath("//span[contains(.,'Написать письмо')]"));


    @Step("Проверка, что страница почтового ящика открылась")
    private void checkPageAppear() {
        buttonWriteLetter.waitUntil(visible, 10000);
        LOG.info("Страница почтового ящика открылась");
    }

    @Step("Нажимаем на кнопку 'Написать письмо'")
    public WriteLetterForm openWriteLetter() {
        buttonWriteLetter.click();
        return new WriteLetterForm();
    }

}
