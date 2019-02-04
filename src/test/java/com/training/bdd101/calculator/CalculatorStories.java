package com.training.bdd101.calculator;

import org.junit.runner.RunWith;

import com.github.valfirst.jbehave.junit.monitoring.JUnitReportingRunner;
import com.training.bdd101.AbstractBDDStories;

@RunWith(JUnitReportingRunner.class)
public class CalculatorStories extends AbstractBDDStories {

    @Override
	protected String getStoryPath() {
        return "**/*calculator.story";
    }

    @Override
	protected String getTestBasePackage() {
		return "com.training.bdd101.calculator";
    }



}
