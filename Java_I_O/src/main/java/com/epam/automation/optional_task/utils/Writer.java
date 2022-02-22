package com.epam.automation.optional_task.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class Writer {
    public static void writeToFile(String fileName, boolean append, String info) {
        try {
            File file = new File(fileName);
            if (file.exists()){
                file.delete();
                file.createNewFile();
            }
            if (!file.exists()){
                new File(fileName.substring(0,fileName.lastIndexOf("/"))).mkdirs();
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(Paths.get(fileName)
                    .toFile(), append);
            writer.write(info);
            if (append) {
                writer.write("\n");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
