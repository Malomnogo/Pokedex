import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.malomnogo.buildlogic"

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.compose.compiler.gradlePlugin)
    implementation(libs.ktlint.gradlePlugin)
    implementation(libs.kotlin.serialization.gradlePlugin)
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "pokedex.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "pokedex.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "pokedex.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "pokedex.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("ktlint") {
            id = "pokedex.ktlint"
            implementationClass = "KtlintConventionPlugin"
        }
        register("jvmLibrary") {
            id = "pokedex.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
        register("kotlinSerialization") {
            id = "pokedex.kotlin.serialization"
            implementationClass = "KotlinSerializationConventionPlugin"
        }
    }
}
