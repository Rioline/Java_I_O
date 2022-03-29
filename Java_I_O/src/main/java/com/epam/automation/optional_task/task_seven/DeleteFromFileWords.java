package com.epam.automation.optional_task.task_seven;

/**
 * 7. Из файла удалить все слова, содержащие от трех до пяти символов, но при этом из каждой строки должно быть
 * удалено только максимальное четное количество таких слов.
 */

import com.epam.automation.optional_task.utils.Reader;
import com.epam.automation.optional_task.utils.Writer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeleteFromFileWords {

    public static void main(String[] args) {
        getFileWithoutSomeWords("Java_I_O/src/input/task+7.txt", "Java_I_O/src/output/task-7.txt");
    }

    public static void getFileWithoutSomeWords(String inputFile, String outputFile) {

        Reader.readFromFile(inputFile)
                .stream()
                .map(string -> {
                    Pattern pattern = Pattern.compile("\\b[A-zА-я]{3,5}\\b");
                    Matcher matcher = pattern.matcher(string);
                    StringBuilder newString = new StringBuilder(string);
                    int words = 0;
                    while (matcher.find()) {
                        words++;
                    }
                    int wordCounter = (words % 2 == 0) ? words : words - 1;
                    matcher = pattern.matcher(string);
                    int difference = 0;
                    while (matcher.find() && wordCounter > 0) {
                        newString.replace(matcher.start() - difference, matcher.end() - difference, "");
                        difference += (matcher.end() - matcher.start());
                        wordCounter--;
                    }
                    return newString;
                })
                .forEach(s -> Writer.writeToFile(outputFile, true, s.toString()));
    }

}
