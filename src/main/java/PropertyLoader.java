import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {

    private static Properties props = new Properties();

    static {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream resourceStream = loader.getResourceAsStream("data.properties")) {
            props.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String loadProperty(String propertyName){
        String property = props.getProperty(propertyName);
        return (property == null) ? "" : property;
    }
}
