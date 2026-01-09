plugins {
    id("pokedex.jvm.library")
    id("pokedex.ktlint")
    id("pokedex.kotlin.serialization")
    id("pokedex.android.dagger")
}

dependencies {
    api(libs.retrofit.core)
    api(libs.kotlinx.serialization.json)
    api(libs.retrofit.kotlin.serialization)
    api(libs.okhttp.logging)
    implementation(project(":core:model"))
}
