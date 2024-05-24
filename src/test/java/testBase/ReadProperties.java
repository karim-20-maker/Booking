package testBase;

import utilities.PropertiesLoader;


public class ReadProperties {
    public static final String URL = getPropertyFromEnv("URL");



    private static String getPropertyFromEnv(String propertyName) {
        return System.getProperty(propertyName, PropertiesLoader.readEnvFile(propertyName));
    }



}
