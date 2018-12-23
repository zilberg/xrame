# language: ru

@all @login
Функция: Авторизация в gmail.
    @success
    Сценарий: Успешная авторизация
        * Вход в почту по логину и паролю
        * Проверка успешной авторизации
        * Выход из аккаунта
    @fail
    Сценарий: Вход по несуществующим адресом электронной почты
        * Вход в почту по логину:"werqew323@ml.ru"
        * Проверка ошибки "Не удалось найти аккаунт Google"