package com.sandbox

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class DbConnectionsTask extends DefaultTask {

    DbConnectionsExtension config

    @TaskAction
    void action() {
        config.connections.all { DbConnectionInfo info ->
            println "Connection ${info.name}: ${info.url.get()}"
        }
    }
}
