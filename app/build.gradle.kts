plugins {
    id("pokedex.android.application")
    id("pokedex.android.application.compose")
    id("pokedex.ktlint")
}

android {
    namespace = "com.malomnogo.pokedex"

    defaultConfig {
        applicationId = "com.malomnogo.pokedex"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
}

tasks.getByPath("preBuild").dependsOn("ktlintFormat")

dependencies {
    implementation(project(":core:ui"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
