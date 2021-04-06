package com.sandbox

import org.gradle.api.Plugin
import org.gradle.api.Project

class DbConnectionsPlugin  implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println "Creating connections"
        project.extensions.create('dbConfig', DbConnectionsExtension, project)
        DbConnectionsExtension dbConfig = project.dbConfig
        project.tasks.create('db', DbConnectionsTask) {
            group = "sandbox"
            config = dbConfig
        }
    }
}
