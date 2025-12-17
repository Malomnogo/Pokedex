plugins {
    id("pokedex.jvm.library")
    id("pokedex.ktlint")
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}
