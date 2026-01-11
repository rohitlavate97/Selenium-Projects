package com.alchemist.framework.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static final Properties configProps = new Properties();
    private static final Properties secretProps = new Properties();

    static {
        // Load config.properties (required)
        try (InputStream is = ConfigManager.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (is == null) throw new RuntimeException("config.properties not found in src/main/resources");
            configProps.load(is);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }

        // Load secrets.properties (optional but recommended for local)
        try (InputStream is = ConfigManager.class.getClassLoader().getResourceAsStream("secrets.properties")) {
            if (is != null) {
                secretProps.load(is);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load secrets.properties", e);
        }
    }

    private ConfigManager() {}

    public static String baseUrl() { return configProps.getProperty("baseUrl"); }
    public static String scope() { return configProps.getProperty("scope"); }
    public static String tokenPath() { return configProps.getProperty("tokenPath"); }
    public static String courseDetailsPath() { return configProps.getProperty("courseDetailsPath"); }

    // Priority: ENV -> secrets.properties
    public static String clientId() {
        String env = System.getenv("RSA_CLIENT_ID");
        if (env != null && !env.isBlank()) return env;

        String v = secretProps.getProperty("clientId");
        if (v == null || v.isBlank()) throw new IllegalStateException("clientId missing (set RSA_CLIENT_ID env OR secrets.properties)");
        return v;
    }

    public static String clientSecret() {
        String env = System.getenv("RSA_CLIENT_SECRET");
        if (env != null && !env.isBlank()) return env;

        String v = secretProps.getProperty("clientSecret");
        if (v == null || v.isBlank()) throw new IllegalStateException("clientSecret missing (set RSA_CLIENT_SECRET env OR secrets.properties)");
        return v;
    }
}
