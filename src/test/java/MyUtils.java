import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class MyUtils {
    public static String setEnvironmentVariable(String Key, String variable) throws ConfigurationException {
        PropertiesConfiguration config = new PropertiesConfiguration("./src/test/resources/config.properties");
        config.setProperty(Key,variable);
        config.save();
        return variable;
    }

}
