plugins {
    id("pokedex.jvm.library")
    id("pokedex.ktlint")
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.annotation)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
}
