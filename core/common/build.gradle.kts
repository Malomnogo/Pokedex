plugins {
    id("pokedex.jvm.library")
    id("pokedex.ktlint")
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.annotation)
}
