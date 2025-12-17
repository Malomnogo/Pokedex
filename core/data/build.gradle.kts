plugins {
    id("pokedex.jvm.library")
    id("pokedex.ktlint")
    id("pokedex.kotlin.serialization")
}

dependencies {
    api(project(":core:network"))
    implementation(project(":core:common"))
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.retrofit.core)
    implementation(project(":core:model")) // Для HttpException
}
