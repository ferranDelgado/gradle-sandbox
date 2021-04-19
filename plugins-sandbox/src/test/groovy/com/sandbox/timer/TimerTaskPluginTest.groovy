package com.sandbox.timer

import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.GradleRunner
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

class TimerTaskPluginTest extends Specification {

    @Rule
    public final TemporaryFolder testProjectDir = new TemporaryFolder()

    def "Apply Test"() {
        expect:
        File build_gradle = testProjectDir.newFile('build.gradle')
        println build_gradle.path
        build_gradle << """
            plugins { 
                id "com.sandbox.timer.timer-task" 
            }
            """
        BuildResult result = gradle(testProjectDir.root, "build")
        println result.output
        result.output =~ "Task timings"
        result.output =~ /0:\t\d+ms\t:buildEnvironment/
    }


    private BuildResult gradle(boolean isSuccessExpected, File rootFolder, String[] arguments = ['tasks']) {
        arguments += '--stacktrace'
        def runner = GradleRunner.create()
                                 .withArguments(arguments)
                                 .withProjectDir(rootFolder)
                                 .withPluginClasspath()
                                 .withDebug(true)
                                 .forwardOutput()
        return isSuccessExpected ? runner.build() : runner.buildAndFail()
    }

    private BuildResult gradle(File rootFolder, String[] arguments = ['tasks']) {
        gradle(true, rootFolder, arguments)
    }
}
