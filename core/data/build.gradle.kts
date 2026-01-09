plugins {
    id("pokedex.jvm.library")
    id("pokedex.ktlint")
    id("pokedex.android.dagger")
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:model"))
    implementation(project(":core:common"))

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.core)
}
