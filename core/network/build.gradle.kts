plugins {
    id("pokedex.jvm.library")
    id("pokedex.ktlint")
    id("pokedex.kotlin.serialization")
}

dependencies {
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    
    implementation(libs.retrofit.core)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp.logging)
    implementation(project(":core:model"))
}
