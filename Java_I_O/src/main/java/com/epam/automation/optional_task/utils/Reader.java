package com.epam.automation.optional_task.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Reader {
    public static List<String> readFromFile(String fileName) {
        List<String> fileLines = null;
        try {
            FileReader fileReader = new FileReader(Paths.get(fileName)
                    .toFile());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            fileLines = bufferedReader.lines()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileLines;
    }
}
