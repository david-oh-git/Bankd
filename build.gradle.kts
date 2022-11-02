
import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.gradle.spotless.SpotlessPlugin
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.KtlintPlugin
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType
import org.jlleitschuh.gradle.ktlint.tasks.GenerateReportsTask

plugins {
    id("com.android.application") version "7.2.0-rc01" apply false
    id("com.android.library") version "7.2.0-rc01" apply false
    id("org.jetbrains.kotlin.android") version "1.6.21" apply false
    alias(libs.plugins.spotless)
    alias(libs.plugins.ktlint)
}

buildscript {

    dependencies {
        classpath(libs.hilt.plugin)
    }
}

subprojects {
    apply<KtlintPlugin>()
    apply<SpotlessPlugin>()

    configure<SpotlessExtension> {

        format("xml") {
            target("**/res/**/*.xml")
            indentWithSpaces(4)
            trimTrailingWhitespace()
        }

        kotlin {
            target("**/*.kt")
            targetExclude("$buildDir/**/*.kt")
            targetExclude("bin/**/*.kt")

            trimTrailingWhitespace()
            indentWithSpaces()

            ktlint(libs.versions.ktlint.get())
            licenseHeader("spotless/copyright.kt")
        }
        kotlinGradle {
            target("*.gradle.kts")
            ktlint(libs.versions.ktlint.get())
        }
    }

    configure<KtlintExtension>() {

        version.set(rootProject.libs.versions.ktlint.get())
        debug.set(true)
        verbose.set(true)
        android.set(false)
        outputToConsole.set(true)
        outputColorName.set("RED")
        enableExperimentalRules.set(true)
        ignoreFailures.set(false)

        reporters {
            reporter(ReporterType.HTML)
            reporter(ReporterType.CHECKSTYLE)
        }
        filter {
            exclude("**/generated/**")
            exclude("**/build/**")
            include("**/kotlin/**")
        }
    }

    tasks.withType<GenerateReportsTask> {
        reportsOutputDirectory.set(
            project.layout.buildDirectory.dir("reports/$name")
        )
    }
}

// tasks.register("clean", Delete::class) {
//    delete(rootProject.buildDir)
// }
