package com.training.bdd101.student;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.BeforeStories;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.training.bdd101.util.StepsDefinition;

/**
 *
 * In order,  JBehave executes BeforeStories, BeforeStory (related to file *.story), BeforeScenario.
 * BeforeStory et BeforeScenario are is the child Thread of BeforeStories.
 */

@StepsDefinition
public class StudentSteps {

    @BeforeScenario
    public void inializeScenario() {
        StudentContext.initialize();
    }

    @AfterScenario
    public void disposeScenario() {
        StudentContext.dispose();
    }


    @BeforeScenario
    public void BeforeScenario(){
        System.out.println("entering BeforeScenario ");
    }

    @BeforeStory
    public void BeforeStory(){
        System.out.println("entering BeforeStory ");
    }

    @BeforeStories
    public void BeforeStories(){
        System.out.println("entering BeforeStories ");
    }

    @AfterScenario(uponOutcome=AfterScenario.Outcome.SUCCESS)
    public void afterSuccessfulScenario() {
        System.out.println("entering AfterScenario.Outcome.SUCCESS ");
    }

    @AfterScenario(uponOutcome=AfterScenario.Outcome.FAILURE)
    public void afterFailedScenario() {
        System.out.println("entering AfterScenario.Outcome.FAILURE ");
    }


    @Given("The student has a mark $mark")
    public void givenTheStudentHasAMark(@Named("mark")int mark) {
        System.out.println("entering givenTheStudentHasAMark ");
        StudentContext.studentNotation().addFirstMark(mark);

        if (true) {
      //      throw  new RuntimeException("givenTheStudentHasAMark has an exception");
        }
    }


    @When("He has a mark $mark")
    public void whenHeHasAMark(@Named("mark")int mark) {
        StudentContext.studentNotation().addMark(mark);

        if (true) {
       //     throw  new RuntimeException("whenHeHasAMark has an exception");
        }
    }

    @Then("His average is $average")
    public void thenHisAverageIs(@Named("average")int average) {
        System.out.println("entering thenHisAverageIs  ");
        Integer expectedaverage =  Integer.valueOf(average);
        assertThat(StudentContext.studentNotation().getAverageMark(), equalTo(expectedaverage));


        if (true) {
        //    throw  new RuntimeException("thenHisAverageIs has an exception");
        }
    }

    @Then("The student has no misktake")
    public void assertNoErrorMessageIsDisplayed() {
        Exception lastError = StudentContext.getLastError();
        assertThat(lastError, nullValue());
    }

    @Then("He has $number marks")
    public void thenIlA2Notes(int number) {
      int numberOfMarks =   StudentContext.studentNotation().marks.size()   ;
        assertThat(numberOfMarks, equalTo(number));
    }


}
