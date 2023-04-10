
import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.gradle.spotless.SpotlessPlugin
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.KtlintPlugin
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType
import org.jlleitschuh.gradle.ktlint.tasks.GenerateReportsTask

plugins {
//    id("com.android.library") version "7.4.0" apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.spotless)
    alias(libs.plugins.ktlint)
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
