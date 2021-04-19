package com.sandbox.timer

import org.gradle.api.Plugin
import org.gradle.api.Project

class TimerTaskPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.gradle.addListener(new TimerTaskListener())
    }
}
