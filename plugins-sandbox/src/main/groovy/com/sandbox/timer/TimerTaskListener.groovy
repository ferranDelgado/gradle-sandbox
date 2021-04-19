package com.sandbox.timer

import java.time.Duration
import java.time.Instant
import org.gradle.BuildListener
import org.gradle.BuildResult
import org.gradle.api.Task
import org.gradle.api.execution.TaskActionListener
import org.gradle.api.initialization.Settings
import org.gradle.api.invocation.Gradle

class TimerTaskListener implements TaskActionListener, BuildListener {

    @Override
    void buildStarted(Gradle gradle) {

    }

    private List<String> sortedTasks = []
    private Map<String, Instant> inits = [:]
    private Map<String, Duration> timings = [:]

    @Override
    void settingsEvaluated(Settings settings) {

    }

    @Override
    void projectsLoaded(Gradle gradle) {

    }

    @Override
    void projectsEvaluated(Gradle gradle) {

    }

    @Override
    void buildFinished(BuildResult result) {
        println "Task timings:"
        sortedTasks.eachWithIndex { String taskName, int i ->
            def duration = timings[taskName]
            println "$i:\t${formatDurationTerse(duration.toMillis())}\t${taskName}"
        }
    }

    @Override
    void beforeActions(Task task) {
        sortedTasks << task.path
        inits[task.path] = Instant.now()
    }

    @Override
    void afterActions(Task task) {
        timings[task.path] = Duration.between(inits[task.path], Instant.now())
    }

    static String formatDurationTerse(long elapsedTimeInMs) {
        StringBuilder result = new StringBuilder()
        if (elapsedTimeInMs > 3600000L) {
            result.append(elapsedTimeInMs / 3600000).append("h ")
        }

        if (elapsedTimeInMs > 60000L) {
            result.append(elapsedTimeInMs % 3600000 / 60000).append("m ")
        }

        if (elapsedTimeInMs >= 1000) {
            result.append(elapsedTimeInMs % 60000 / 1000).append("s")
        } else {
            result.append(elapsedTimeInMs).append("ms")
        }

        return result.toString()
    }
}
