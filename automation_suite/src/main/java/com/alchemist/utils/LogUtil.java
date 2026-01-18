package com.alchemist.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtil {

    private static final Logger log = LogManager.getLogger(LogUtil.class);

    // Log messages
    public static void info(String message) {
        log.info(message);
    }

    public static void error(String message) {
        log.error(message);
    }

    public static void error(String message, Throwable t) {
        log.error(message, t);
    }

    // Utility to get log file paths
    public static String getInfoLogPath() {
        return "logs/automation-info.log";
    }

    public static String getErrorLogPath() {
        return "logs/automation-error.log";
    }
}
