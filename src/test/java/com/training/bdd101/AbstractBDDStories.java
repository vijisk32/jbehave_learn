package com.training.bdd101;


import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.HTML_TEMPLATE;
import static org.jbehave.core.reporters.Format.STATS;

import java.net.URL;
import java.util.List;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.NullStepMonitor;

import com.training.bdd101.calculator.CalculatorSteps;
import com.training.bdd101.student.StudentSteps;
import com.training.bdd101.util.UTF8StoryLoader;


/**
 * 
 * Advice: have a AbstractBDDStories class to organize your projects and the generic behaviors.
 * The package names, the story files and the steps can be configured here.
 * 
 * The JUnitStories runner makes integration with tools easier.
 * 
 * Sources: 
 * http://jbehave.org/reference/latest/maven-goals.html
 * https://github.com/jbehave/jbehave-core/tree/master/jbehave-rest
 * https://github.com/reportportal/example-java-jbehave/blob/master/pom.xml
 *
 */
public abstract class AbstractBDDStories extends JUnitStories {

    private final CrossReference xref = new CrossReference();

    /* The number of Thread must be 1 so that IntelliJ shows the status correctly (Tests in Green).
       and so that AfterScenario.Outcome.SUCCESS is taken into account after the scenario.
    */
    public AbstractBDDStories() {
        configuredEmbedder()//
                .embedderControls()//
                .doGenerateViewAfterStories(true)//
                .doIgnoreFailureInStories(false)//
                .doIgnoreFailureInView(false)//
                .doVerboseFailures(true)//
                .useThreads(1)//
                .useStoryTimeouts("6000");
    }

    @Override
    public Configuration configuration() {
        Class<? extends Embeddable> embeddableClass = this.getClass();
        URL codeLocation = codeLocationFromClass(embeddableClass);
        StoryReporterBuilder storyReporter = //
                new StoryReporterBuilder() //
                        .withCodeLocation(codeLocation) //
                        .withDefaultFormats() //
                        .withFormats(CONSOLE, //
                                HTML_TEMPLATE, STATS) //
                        .withFailureTrace(true) //
                        .withFailureTraceCompression(true) //
                        .withCrossReference(xref)
                        .withRelativeDirectory("bdd-reports");
        return new MostUsefulConfiguration() //
                .useStoryLoader(new UTF8StoryLoader(embeddableClass)) //
                .useStoryReporterBuilder(storyReporter) //
                .usePendingStepStrategy(new FailingUponPendingStep())
                //   .usePendingStepStrategy(new PassingUponPendingStep())
                .doDryRun(false)
                .useStepMonitor(new NullStepMonitor()) ;
    }

    protected String getStoryPath() {
        return "**/*.story";
    }

    @Override
    protected List<String> storyPaths() {
        URL searchInURL = codeLocationFromClass(this.getClass());
        return new StoryFinder().findPaths(searchInURL, getStoryPath(), "**/fail/*");
    }

    /**
     * @return the base package name
     */
    protected String getTestBasePackage() {
        return "com.training.bdd101";
    }

    /**
     * Returns the Instance of StepsFactory
     */
    @Override
    public InjectableStepsFactory stepsFactory() {

        return new InstanceStepsFactory(configuration(),
                new CalculatorSteps(), new StudentSteps()
        );
    }

}
