package com.training.bdd101.student;

import org.junit.runner.RunWith;

import com.github.valfirst.jbehave.junit.monitoring.JUnitReportingRunner;
import com.training.bdd101.AbstractBDDStories;

@RunWith(JUnitReportingRunner.class)
public class StudentStories extends AbstractBDDStories {

    @Override
	protected String getStoryPath() {
        return "**/*student.story";
    }

    @Override
	protected String getTestBasePackage() {
        return "com.training.bdd101.student";
    }
}
