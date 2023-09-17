import org.gradle.plugins.ide.idea.model.IdeaLanguageLevel

plugins {
    idea
    `java-library`
}

idea {
    project {
        languageLevel = IdeaLanguageLevel(17)
    }
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

allprojects {
    group = "ru.otus"

    repositories {
        mavenLocal()
        mavenCentral()
    }
}


dependencies {
    implementation("org.slf4j:slf4j-simple:2.0.5")
    implementation("org.flywaydb:flyway-core:9.22.1")
    implementation("org.postgresql:postgresql:42.6.0")
    implementation("org.ehcache:ehcache:3.10.8")
}
