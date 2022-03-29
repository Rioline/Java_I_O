package com.epam.automation.main_task.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class MyWriter {
    public static void writeToFile(String fileName, boolean append, String info) {
        try (FileWriter writer = new FileWriter(Paths.get(fileName)
                .toFile(), append)) {
            writer.write(info);
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
