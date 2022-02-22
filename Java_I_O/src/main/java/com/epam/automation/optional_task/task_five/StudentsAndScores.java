package com.epam.automation.optional_task.task_five;

import com.epam.automation.optional_task.utils.Reader;
import com.epam.automation.optional_task.utils.Writer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.Objects;

/**
 * 5. В файле, содержащем фамилии студентов и их оценки, записать прописными буквами фамилии тех студентов,
 * которые имеют средний балл более 7.
 */

public class StudentsAndScores {

    public static void main(String[] args) {
        getStudentsToUpperCaseWithGoodScores("Java_I_O/src/input/task+5.txt",
                "Java_I_O/src/output/task-5.txt", 7);
    }

    public static void getStudentsToUpperCaseWithGoodScores(String inputFile, String outputFile, int comparableMark) {
        Reader.readFromFile(inputFile)
                .stream()
                .map(s -> {
                    StringBuilder stringScores = new StringBuilder();
                    StringBuilder stringName = new StringBuilder();
                    for (Character ch : s.toCharArray()) {
                        if (ch.toString()
                                .matches("(?ui:[a-zA-z])")) {
                            stringName.append(ch);
                        } else if (!ch.toString()
                                .matches("(?ui:[a-zA-Z])")) {
                            stringScores.append(ch);
                        }
                    }
                    int marksSum = 0;
                    int markCounter = 0;
                    String[] stringMarksArray = stringScores.toString()
                            .split(" ");
                    for (String mark : stringMarksArray) {
                        if (!Objects.equals(mark, "")) {
                            try {
                                int currentMark = Integer.parseInt(mark);
                                if (currentMark < 0 || currentMark > 10) {
                                    throw new NumberFormatException("Marks should be in range from 0 to 10");
                                }
                                marksSum += currentMark;
                                markCounter++;
                            } catch (NumberFormatException e) {
                                throw new NumberFormatException("Marks include incorrect values!");
                            }
                        }
                    }
                    BigDecimal averageMark = (BigDecimal.valueOf((double) marksSum / (double) markCounter))
                            .setScale(2, RoundingMode.HALF_UP);
                    if (averageMark.doubleValue() > comparableMark) {
                        stringName = new StringBuilder(stringName.toString()
                                .toUpperCase(Locale.ROOT));
                    }
                    return stringName.append(stringScores);
                })
                .forEach(s -> Writer.writeToFile(outputFile, true, s.toString()));
    }

}