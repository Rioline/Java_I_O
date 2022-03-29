package com.epam.automation.optional_task.task_six;

public enum TypeOfData {

    INTEGER("integer"),
    DOUBLE("double"),
    CHAR("char"),
    WORD("word");

    private final String name;

    TypeOfData(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
