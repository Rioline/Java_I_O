package com.epam.automation.main_task.service;

import java.io.File;
import java.io.FileNotFoundException;

public class Executor {
    public static void getData(String path){
        File file = new File(path);
        if (file.isDirectory()){
            try {
                DirService.getInfoAboutDirectory(path, path + "/output.txt","", false);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (file.isFile()){
            FileService.getInfoAboutFile(path);
        }
    }
}
