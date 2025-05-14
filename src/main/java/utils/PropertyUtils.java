package utils;

import common.enums.PropertyValues;

public class PropertyUtils {

    public static String getLogin() {
        return getSystemProperty(PropertyValues.LOGIN);
    }

    public static String getPassword() {
        return getSystemProperty(PropertyValues.PASSWORD);
    }

    protected static String getSystemProperty(PropertyValues property) {
        return System.getProperty(property.getPropertyValue());
    }

    /*private static String getProperty(String key, PropertyValues propertyValues) {
        Properties properties = new Properties();
        try {
            InputStream stream = PropertyUtils.class.getResourceAsStream(propertyValues.getPropertyValue());
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }*/
}
