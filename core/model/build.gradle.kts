import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    id("org.jlleitschuh.gradle.ktlint") version "14.0.1"
}

kotlin {
    jvmToolchain(17)
}

ktlint {
    android = true
    ignoreFailures = false
    reporters {
        reporter(ReporterType.PLAIN)
        reporter(ReporterType.CHECKSTYLE)
        reporter(ReporterType.SARIF)
    }
}
