package com.api.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {
    /**
     *
     * @param filePath
     * @return The content of the file as String
     * @throws RuntimeException If an error occurs while reading the file.
     */
    public static String readFileAsString(String filePath) {
        Path path = Paths.get(filePath);
        try {
            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            throw new RuntimeException("Error reading file at path: "+filePath);
        }
    }
}
