package com.epam.automation.main_task.service;

import com.epam.automation.main_task.utils.MyReader;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

public class FileService {
    public static void getInfoAboutFile(String path) {
        List<String> fileLines = MyReader.readFromFile(path);
        if (fileLines.size() != 0) {
            int dirCount = (int) (fileLines.stream()
                    .filter(o1 -> o1.contains("|--"))
                    .count() + 1);
            int filesCount = (int) (fileLines.stream()
                    .filter(o1 -> !o1.contains("|--"))
                    .count() - 1);
            BigDecimal averageCount = (BigDecimal.valueOf((double) filesCount / (double) dirCount))
                    .setScale(2, RoundingMode.HALF_UP);
            BigDecimal averageFilesNames = null;
            Optional<Integer> filesNamesLength = fileLines.stream()
                    .filter(o1 -> !o1.contains("--\uD83D\uDDC0"))
                    .map(o1 -> {
                        while (o1.charAt(0) == ' ' || o1.charAt(0) == '|') {
                            o1 = new StringBuilder(o1).replace(0, 1, "")
                                    .toString();
                        }
                        return o1.length();
                    })
                    .reduce(Integer::sum);
            if (filesNamesLength.isPresent()) {
                averageFilesNames = BigDecimal.valueOf(((double) filesNamesLength.get() - fileLines.get(0)
                                .length()) / (double) filesCount)
                        .setScale(2, RoundingMode.HALF_UP);
            }
            System.out.println("Count of folders = " + dirCount);
            System.out.println("Count of files = " + filesCount);
            System.out.println("Average count of files in folder = " + averageCount);
            System.out.println("Average length of name of files = " + averageFilesNames);
        } else {
            System.out.println("File is empty!");
        }
    }
}
