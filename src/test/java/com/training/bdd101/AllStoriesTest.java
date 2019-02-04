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
import org.jbehave.core.steps.NullStepMonitor;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.junit.runner.RunWith;

import com.github.valfirst.jbehave.junit.monitoring.JUnitReportingRunner;
import com.training.bdd101.util.Springs;
import com.training.bdd101.util.UTF8StoryLoader;

/**
 * Spring is used here.
 */ 
@RunWith(JUnitReportingRunner.class)
public class AllStoriesTest extends JUnitStories {

    private final CrossReference xref = new CrossReference();

    public AllStoriesTest() {
        configuredEmbedder() 
                .embedderControls()//
                .doGenerateViewAfterStories(true)//
                .doIgnoreFailureInStories(false)//
                .doIgnoreFailureInView(true)//
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
                .withRelativeDirectory("bdd101-reports")                ;
        return new MostUsefulConfiguration() //
                .useStoryLoader(new UTF8StoryLoader(embeddableClass)) //
                .useStoryReporterBuilder(storyReporter) //
                .usePendingStepStrategy(new FailingUponPendingStep())
             //   .usePendingStepStrategy(new PassingUponPendingStep())
                .doDryRun(true)
                .useStepMonitor(new NullStepMonitor()) ;
    }

    @Override
    protected List<String> storyPaths() {
        URL searchInURL = codeLocationFromClass(this.getClass());
        return new StoryFinder().findPaths(searchInURL, "**/*.story", "**/fail/*");
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new SpringStepsFactory(configuration(),
                Springs.createAnnotatedContextFromBasePackages("com.training.bdd101"));
    }
}
