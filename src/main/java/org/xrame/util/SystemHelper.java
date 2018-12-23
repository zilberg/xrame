package org.xrame.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static java.lang.System.currentTimeMillis;

public class SystemHelper {
    /**
     * Загрузка настроек для Selenide
     */
    public static void loadDriverProperties(){
        ResourceBundle rb = ResourceBundle.getBundle("config");
        switch (rb.getString("browser")){
            case "chrome": setProperties(ResourceBundle.getBundle("chrome"));
            break;
            case "yandex": setProperties(ResourceBundle.getBundle("yandex"));
            break;
            case "ie": setProperties(ResourceBundle.getBundle("ie"));
            break;
            case "edge": setProperties(ResourceBundle.getBundle("edge"));
            break;
            default: setProperties(ResourceBundle.getBundle("chrome"));
        }
        setProperties(rb);
    }

    /**
     * Возврашает properties из файла
     * @param path - путь
     * @return properties from file
     */
    public static Properties getPropertiesFromFile(String path) {
        Properties pr = new Properties();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            pr.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pr;
    }

    /**
     * "Умная ожидалка"
     * @param condition ожидаемое условие
     * @param doing выполнять действие пока результат не удовлетиворить
     * @param second сколько секунд ждать
     * @param <T> результат действия
     * @return результат
     */
    public static <T> T waits(Supplier<T> doing, Predicate<T> condition, int second) {
        long start = currentTimeMillis();
        T obj = doing.get();
        while (!condition.test(obj) && ((((currentTimeMillis() - start) / 1000)) < second)) {
            obj = doing.get();
        }
        return obj;
    }

    private static void setProperties(ResourceBundle rb) {
        for(Enumeration<String> e = rb.getKeys(); e.hasMoreElements();) {
            String key = e.nextElement();
            System.setProperty(key, rb.getString(key));
        }
    }
}
