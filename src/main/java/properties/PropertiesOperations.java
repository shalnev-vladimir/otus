package properties;

import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.util.Properties;

public final class PropertiesOperations {

    private PropertiesOperations() {
    }

    private static final Properties PROP = new Properties();

    public static Properties getProp() {
        return PROP;
    }

    public static String getPropertyValueByKey(final String key) throws Exception {
        // load data from property file
        String propFilePath =
                System.getProperty("user.dir")
                        + "/src/main/resources/config.properties";
        FileInputStream fis = new FileInputStream(propFilePath);
        getProp().load(fis);

        // read data
        String value = getProp().get(key).toString();

        if (StringUtils.isEmpty(value)) {
            throw new NullPointerException("Value is not specified for "
                    + key + " in properties file");
        }
        return value;
    }
}
