package com.training.bdd101.student;

import com.training.bdd101.student.StudentNotation;

public class StudentContext {

    public static StudentNotation studentNotation() {
        if (studentNotation==null) {
            studentNotation = new StudentNotation();
        }
        return studentNotation;
    }

    public static void initialize() {
        // one does not rely on ThreadLocal#initialValue()
        // so that one is sure only initialize create a new instance
        //threadContext.set(new StudentContext());
    }
    public static void dispose () {
        //threadContext.remove();
    }


    private static StudentNotation studentNotation;
    private static Exception lastError;

    public StudentContext() {
        studentNotation = new StudentNotation();
    }

    public StudentNotation getStudentNotation() {
        return studentNotation;
    }

    public void setLastError(Exception lastError) {
        StudentContext.lastError = lastError;
    }

    public static Exception getLastError() {
        return lastError;
    }



}
