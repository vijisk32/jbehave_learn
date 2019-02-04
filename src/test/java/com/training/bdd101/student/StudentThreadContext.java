package com.training.bdd101.student;

import com.training.bdd101.student.StudentNotation;

public class StudentThreadContext {

    private static ThreadLocal<StudentThreadContext> threadContext = new ThreadLocal<>();

    public static StudentThreadContext context() {
        return threadContext.get();
    }

    public static StudentNotation studentNotation() {
        return context().getStudentNotation();
    }

    public static void initialize() {
        // one does not rely on ThreadLocal#initialValue()
        // so that one is sure only initialize create a new instance
        threadContext.set(new StudentThreadContext());
    }
    public static void dispose () {
        threadContext.remove();
    }


    private final StudentNotation studentNotation;
    private Exception lastError;

    public StudentThreadContext() {
        studentNotation = new StudentNotation();
    }

    public StudentNotation getStudentNotation() {
        return studentNotation;
    }

    public void setLastError(Exception lastError) {
        this.lastError = lastError;
    }

    public Exception getLastError() {
        return lastError;
    }



}
