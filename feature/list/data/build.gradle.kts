plugins {
    id("pokedex.jvm.library")
    id("pokedex.ktlint")
    id("pokedex.kotlin.serialization")
}

dependencies {
    implementation(project(":feature:list:domain"))
    implementation(project(":core:data"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))

    implementation(libs.kotlinx.coroutines.core)
}
