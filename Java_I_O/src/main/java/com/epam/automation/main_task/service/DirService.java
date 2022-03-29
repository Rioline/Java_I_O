package com.epam.automation.main_task.service;

import com.epam.automation.main_task.utils.MyWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class DirService {
    public static void getInfoAboutDirectory(String path, String outputFile, String separator, boolean isLast)
            throws FileNotFoundException {
        try {
            File file = new File(path);
            File[] files = file.listFiles();
            int countDir = (int) Arrays.stream(files)
                    .filter(File::isDirectory)
                    .count();
            int countFile = (int) Arrays.stream(files)
                    .filter(File::isFile)
                    .count();
            MyWriter.writeToFile(outputFile, true, separator + "--\uD83D\uDDC0" + file.getName());
            if (isLast) {
                separator = new StringBuilder(separator)
                        .replace(separator.length() - 1, separator.length(), " ")
                        .toString();
            }
            if (countDir != 0) {
                separator += "  |";
            } else if (countFile != 0) {
                separator += "   ";
            }
            for (File everyFile : files) {
                if (everyFile.isFile()) {
                    MyWriter.writeToFile(outputFile, true, separator + "   " + everyFile.getName());
                }
            }
            int dirNumber = 0;
            for (File eachFile : files) {
                if (eachFile.isDirectory()) {
                    dirNumber++;
                    getInfoAboutDirectory(eachFile.getAbsolutePath(), outputFile, separator, dirNumber == countDir);
                }
            }
        } catch (NullPointerException e) {
            throw new FileNotFoundException("Wrong path!");
        }
    }
}