package com.sandbox

import javax.inject.Inject
import org.gradle.api.provider.Property

abstract class DbConnectionInfo {
    private final String name

    @Inject
    DbConnectionInfo(String name) {
        this.name = name
    }

    String getName() {
        return name
    }

    abstract Property<String> getUrl();
}