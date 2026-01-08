plugins {
    id("pokedex.jvm.library")
    id("pokedex.ktlint")
    id("pokedex.android.dagger")
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
}
