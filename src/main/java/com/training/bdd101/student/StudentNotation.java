package com.training.bdd101.student;

import java.util.ArrayList;
import java.util.List;

public class StudentNotation {

    public List<Integer> marks = new ArrayList<Integer>();

    public void addFirstMark(int newMark) {
        System.out.println("entering addFirstMark ");
        marks.clear();
        marks.add(newMark);
    }

    public void addMark(int newMark) {
        marks.add(newMark);
    } 

    public int getAverageMark() {

        Double moyenne = marks.stream().mapToDouble(Integer::doubleValue).average().orElse(0d);
        return moyenne.intValue();
    }

}
