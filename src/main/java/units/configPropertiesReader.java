package units;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class configPropertiesReader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = configPropertiesReader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("не найден config.properties в resources");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("ошибка загрузки config.properties", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
