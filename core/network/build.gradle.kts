plugins {
    id("pokedex.jvm.library")
    id("pokedex.ktlint")
    id("pokedex.kotlin.serialization")
    id("pokedex.android.dagger")
}

dependencies {
    api(libs.retrofit.core) // Changed to api
    api(libs.kotlinx.serialization.json) // Changed to api
    api(libs.retrofit.kotlin.serialization) // Changed to api
    api(libs.okhttp.logging) // Changed to api
    implementation(project(":core:model"))
}
