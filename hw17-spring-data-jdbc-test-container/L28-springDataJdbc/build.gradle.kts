import org.gradle.api.tasks.testing.logging.TestExceptionFormat

dependencies {
    dependencies {
        implementation ("ch.qos.logback:logback-classic")
        implementation ("org.flywaydb:flyway-core")
        implementation ("org.postgresql:postgresql")
        implementation ("com.google.code.findbugs:jsr305")

        implementation ("org.springframework.boot:spring-boot-starter-data-jdbc")
        testImplementation("com.h2database:h2")
        testImplementation("org.junit.jupiter:junit-jupiter-engine")
        testImplementation("org.junit.jupiter:junit-jupiter-params")
        testImplementation("org.assertj:assertj-core")

        testImplementation("org.testcontainers:junit-jupiter")
        testImplementation("org.testcontainers:postgresql")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging.showExceptions = true
    testLogging.exceptionFormat = TestExceptionFormat.FULL
    testLogging.events("started", "skipped", "passed", "failed")
    testLogging.showStandardStreams = true
    reports {
        junitXml.required.set(true)
        html.required.set(true)
    }
}
