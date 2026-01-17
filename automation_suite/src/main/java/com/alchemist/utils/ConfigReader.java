package com.alchemist.utils;

import java.io.FileInputStream;
import java.util.Properties;
import com.alchemist.constants.FrameworkConstants;
import com.alchemist.exceptions.FrameworkException;

public class ConfigReader {

    private static Properties prop;

    public static void loadEnv(String env) {
        try {
            prop = new Properties();
            String path = "src/main/resources/config-" + env.toLowerCase() + ".properties";
            prop.load(new FileInputStream(path));
        } catch (Exception e) {
            throw new FrameworkException("Failed to load config for environment: " + env, e);
        }
    }

    public static String get(String key) {
        if(prop == null) {
            throw new FrameworkException("Config not loaded. Call loadEnv(env) first!");
        }
        return prop.getProperty(key);
    }
}
