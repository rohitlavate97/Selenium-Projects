package com.alchemist.utils;

import java.io.File;

public class CleanupUtil {

    public static void cleanDirectory(String path) {
        File dir = new File(path);

        if (!dir.exists()) {
            return;
        }

        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                cleanDirectory(file.getAbsolutePath());
            }
            file.delete();
        }
    }

    public static void cleanAll() {
        cleanDirectory("reports");
        cleanDirectory("screenshots");
        cleanDirectory("logs");
    }
}