package com.sandbox

import org.gradle.api.Action
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project

class DbConnectionsExtension {

    public final NamedDomainObjectContainer<DbConnectionInfo> connections

    DbConnectionsExtension(Project project) {
        def objects = project.getObjects()
        this.connections = project.container(DbConnectionInfo) {
            name -> objects.newInstance(DbConnectionInfo.class, name)
        }
    }

    void connections(Action<NamedDomainObjectContainer<DbConnectionInfo>> action) {
        action.execute(connections)
    }
    //
    void connections(Closure<Object> closure) {
        connections.configure(closure)
    }

}
