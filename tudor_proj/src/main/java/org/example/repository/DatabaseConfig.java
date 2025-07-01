package org.example.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseConfig {
    private static Properties properties = new Properties();
    static {
        try(FileInputStream fis = new FileInputStream("/Users/tudoriliescu/IdeaProjects/project-java-I-Tudor/tudor_proj/src/main/resources/dbconfig.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getDbUrl() {
        return properties.getProperty("db.url");
    }

    public static String getDbUsername() {
        return properties.getProperty("db.username");
    }

    public static String getDbPassword() {
        return properties.getProperty("db.password");
    }
}
