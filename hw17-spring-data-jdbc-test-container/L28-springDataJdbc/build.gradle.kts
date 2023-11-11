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
