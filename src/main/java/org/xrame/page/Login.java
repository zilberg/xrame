package org.xrame.page;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.title;

public class Login {
    /**
     * "Войти"
     * @return
     */
    public Login signIn(){
        $(By.xpath("//div[@class='gmail-nav__nav-links-wrap']/a[@data-g-label='Sign in']")).click();
        return this;
    }

    /**
     * Ввод логина
     * @param user логин
     * @return
     */
    public Login setLogin(String user) {
        $(By.xpath("//input[@type='email']")).setValue("vasilev7vas@gmail.com").setValue(user);
        return this;
    }

    /**
     * Ввод пароля
     * @param pwd пароль
     * @return
     */
    public Login setPassword(String pwd) {
        $(By.xpath("//input[@type='password']")).setValue(pwd);
        return this;
    }

    /**
     * Жмем "Далее"
     * @return
     */
    public Login next() {
        $(By.xpath("//div[@role='button']/descendant::span[contains(text(), 'Далее')]")).click();
        return this;
    }

    /**
     * Получить заголовок страницы
     * @return заголовок страницы
     */
    public String getTitle(){
        return title();
    }

    /**
     * Текст ошибки при неправильном вводе логина
     * @return ошибка
     */
    public String getStatusAfterErrorLogin(){
        return $(By.xpath("//content/descendant::div[12]")).text();
    }

    public Login changeAccount(){
        $(By.xpath("//div/p[contains(text(), 'Сменить аккаунт')]")).click();
        return this;
    }
}
