package helpers;

import ru.yandex.qatools.properties.PropertyLoader;
import ru.yandex.qatools.properties.annotations.Property;
import ru.yandex.qatools.properties.annotations.Resource;

@Resource.Classpath("test.properties")
public class Config {
    private static Config config;

    private Config() {
        PropertyLoader.populate(this);
    }

    public static Config getInstance() {
        if (config == null) {
            config = new Config();
        }
        return config;
    }

    public String getBrowser() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        return (browser.equals("firefox")) ? "marionette" : "chrome";
    }

    public static final String MAIL_BK_DOMAIN = "@bk.ru";
    public static final String MAIL_MAIL_DOMAIN = "@mail.ru";
    public static final String MAIL_INBOX_DOMAIN = "@inbox.ru";
    public static final String MAIL_LIST_DOMAIN = "@list.ru";

    @Property("mailUrl")
    private String mailUrl;

    @Property("mailUser")
    private String mailUser;

    @Property("mailPassword")
    private String mailPassword;

    public String getMailUrl() {
        return mailUrl;
    }

    public String getMailUser() {
        return mailUser;
    }

    public String getMailPassword() {
        return mailPassword;
    }
}
