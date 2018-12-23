package org.xrame.page;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Gmail {
    /**
     * Кликаем по иконке аккаунта
     * @param login логин пользователя
     * @return
     */
    public Gmail clickAccountIcon(String login){
        $(By.xpath("//a[contains(@aria-label,'(" + login + ")')][@role='button']")).click();
        return this;
    }

    /**
     * Выход из почты
     * @return
     */
    public Gmail logOut() {
        $(By.xpath("//div[contains(@aria-label,'Информация об аккаунте')]/descendant::div/a[contains(text(),'Выйти')]")).click();
        return this;
    }

}
