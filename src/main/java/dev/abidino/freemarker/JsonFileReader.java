package dev.abidino.freemarker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class JsonFileReader {

    public static String readJsonFile(String fileName) {
        String filePath = "/templates/" + fileName;
        try (InputStream inputStream = JsonFileReader.class.getResourceAsStream(filePath)) {
            if (inputStream == null) {
                throw new RuntimeException("File not found: " + filePath);
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                return reader.lines().collect(Collectors.joining("\n"));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while reading json file: " + filePath, e);
        }
    }
}