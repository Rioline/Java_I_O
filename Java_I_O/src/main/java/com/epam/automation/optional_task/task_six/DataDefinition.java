package com.epam.automation.optional_task.task_six;

/**
 * 6. Файл содержит символы, слова, целые числа и числа с плавающей запятой. Определить все данные,
 * тип которых вводится из командной строки.
 */

import com.epam.automation.optional_task.utils.Reader;
import com.epam.automation.optional_task.utils.Writer;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataDefinition {

    public static void main(String[] args) {
        getDataDefinition("Java_I_O/src/input/task+6.txt", "Java_I_O/src/output/task-6.txt");
    }

    public static void getDataDefinition(String inputFile, String outputFile) {
        String typeOfData = getFromCommandLine("Input the type of data : integer,double,char,word >>>: ")
                .toLowerCase(Locale.ROOT);
        Map<String, TypeOfData> dataTypes = new HashMap<>();
        dataTypes.put(TypeOfData.INTEGER.getName(), TypeOfData.INTEGER);
        dataTypes.put(TypeOfData.DOUBLE.getName(), TypeOfData.DOUBLE);
        dataTypes.put(TypeOfData.CHAR.getName(), TypeOfData.CHAR);
        dataTypes.put(TypeOfData.WORD.getName(), TypeOfData.WORD);
        Reader.readFromFile(inputFile)
                .stream()
                .map(string -> {
                    StringBuilder stringBuilder = new StringBuilder();
                    Pattern pattern = null;
                    if (dataTypes.get(typeOfData) == null) {
                        throw new IllegalArgumentException("Wrong value of data type!");
                    }
                    switch (dataTypes.get(typeOfData)) {
                        case INTEGER:
                            pattern = Pattern.compile("\\-?(?<![.,])\\b[0-9]+\\b(?![\\.\\,][0-9])");
                            break;
                        case DOUBLE:
                            pattern = Pattern.compile("\\-?\\d+[\\.\\,]\\d+");
                            break;
                        case CHAR:
                            pattern = Pattern.compile("(?<!\\S)[\\[\\]\\\\\\^\\$\\|\\?\\*\\+\\(\\)#~`<>{}!@№;:\\/\\&+A-zА-я]?[^А-яA-z\\d]");
                            break;
                        case WORD:
                            pattern = Pattern.compile("\\b[A-zА-я]{2,}\\b");
                            break;
                        default:
                            throw new IllegalArgumentException("Wrong data type exception!");
                    }
                    Matcher matcher = pattern.matcher(string);
                    while (matcher.find()) {
                        stringBuilder.append(string.substring(matcher.start(), matcher.end())
                                .replace(" ", ""));
                        stringBuilder.append(" ");
                    }
                    return stringBuilder;

                })
                .forEach(stringBuilder -> Writer.writeToFile(outputFile, true, stringBuilder.toString()));
    }

    public static String getFromCommandLine(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String inputFromCmd = scanner.nextLine();
        return inputFromCmd;
    }

}
