package org.xrame;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.xrame.util.SystemHelper;

import java.util.HashMap;

import static com.codeborne.selenide.Selenide.*;

public class Init {
    private HashMap<String, Object> stash;

    public Init(){
        stash = new HashMap<>();
    }

    public HashMap<String, Object> getStash() {
        return this.stash;
    }
    /**
     * Запуск драйвера
     */
    public void initBrowser() {
        SystemHelper.loadDriverProperties();
        if (System.getProperty("browser").equals("ie")) {
            Configuration.browser = WebDriverRunner.INTERNET_EXPLORER;
        }
        open("/");
    }

}
