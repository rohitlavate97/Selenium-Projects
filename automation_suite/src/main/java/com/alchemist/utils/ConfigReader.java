package com.alchemist.utils;

import java.io.FileInputStream;
import java.util.Properties;
import com.alchemist.constants.FrameworkConstants;

public class ConfigReader {

    private static Properties prop;

    static {
        try {
            prop = new Properties();
            prop.load(new FileInputStream(FrameworkConstants.CONFIG_PATH));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config file");
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }
}
