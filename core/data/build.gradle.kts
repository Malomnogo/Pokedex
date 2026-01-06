plugins {
    id("pokedex.jvm.library")
    id("pokedex.ktlint")
    id("pokedex.kotlin.serialization")
}

dependencies {
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)

    api(project(":core:network"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.retrofit.core)

    testImplementation(libs.junit)
    testImplementation(libs.okhttp.logging)
}
