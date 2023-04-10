

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "io.osemwota.bankd"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-opt-in=kotlin.RequiresOptIn",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
        )
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.version.get()
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    sourceSets {
        getByName("main") {
            java.srcDir("src/main/kotlin")
        }
        getByName("test") {
            java.srcDir("src/test/kotlin")
        }
        getByName("androidTest") {
            java.srcDir("src/androidTest/kotlin")
        }
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
        unitTests.all { test ->
//            test.useJUnitPlatform()
            test.testLogging {
                events("passed","skipped", "failed")
            }
        }
    }
    namespace = "io.osemwota.bankd"

}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.lifecycle.runtime)
    implementation(libs.activity.compose)
    implementation(libs.material3)
    implementation(libs.material3.window)
    implementation(libs.nav)
    implementation(libs.timber)

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
    implementation(libs.androidx.hilt.compose)

    // Tests
    testImplementation(libs.junit4)
    testImplementation(libs.truth)
    testImplementation(libs.junit5.api)
    testImplementation(libs.kotlinx.coroutines.test)

    testRuntimeOnly(libs.junit5.engine)

    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.espresso.contrib)
    androidTestImplementation(libs.arch.core)
    androidTestImplementation(libs.compose.ui.junit)
    androidTestImplementation(libs.compose.ui.test)
    androidTestImplementation(libs.truth)

    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)
}
