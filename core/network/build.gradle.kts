plugins {
    id("pokedex.jvm.library")
    id("pokedex.ktlint")
    id("pokedex.kotlin.serialization")
}

dependencies {
    implementation(project(":core:model"))
    implementation(libs.retrofit.core)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp.logging)
}
