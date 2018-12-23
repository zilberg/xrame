package org.xrame;

import cucumber.api.Scenario;
import cucumber.api.java8.Ru;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xrame.page.Login;
import org.xrame.page.Gmail;

import java.util.Properties;

import static com.codeborne.selenide.WebDriverRunner.isIE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.xrame.util.SystemHelper.getPropertiesFromFile;
import static org.xrame.util.SystemHelper.waits;

public class GMailSteps  implements Ru {
    private static Logger LOG = LoggerFactory.getLogger(GMailSteps.class);
    private Init init;
    private Login login;
    private Gmail gmail;

    public GMailSteps(Init init, Login login, Gmail gmail) {
        this.init = init;
        this.login = login;
        this.gmail = gmail;

        Before((Scenario scenario) -> {
            LOG.info("Инициализация веб драйвера {}");
            init.initBrowser();
        });

        After((Scenario scenario) -> {
            if(isIE()){
                LOG.info("Убиваем процессы ie");
                Runtime.getRuntime().exec("taskkill /IM iexplore.exe /F");
            }
        });

        Допустим("Вход в почту по логину и паролю", () -> {
            Properties prop = getPropertiesFromFile(System.getProperty("user.dir") + "\\src\\test\\resources\\user\\User1.properties");
            String user = prop.getProperty("login");
            init.getStash().put("user", user);
            String pwd = prop.getProperty("password");
            System.out.println();
            login.signIn()
                    .setLogin(user)
                    .next()
                    .setPassword(pwd)
                    .next();
        });

        Допустим("Проверка успешной авторизации", () -> {
            assertTrue( waits(() -> login.getTitle(), i -> !i.equals("Gmail"), 30).contains(init.getStash().get("user") + " - Gmail"));
        });

        Допустим("Выход из аккаунта", () -> {
            gmail.clickAccountIcon((String) init.getStash().get("user"))
                 .logOut();

        });

        Допустим("Вход в почту по логину:{string}", (String user) -> {
            login.signIn()
                 .changeAccount()
                 .setLogin(user)
                 .next();
        });
        Допустим("Проверка ошибки {string}", (String error) -> {
            assertEquals(error, waits(() -> login.getStatusAfterErrorLogin(), i -> !i.equals(""), 10));
        });



    }
}
