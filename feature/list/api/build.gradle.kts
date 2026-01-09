plugins {
    id("pokedex.android.library")
    id("pokedex.ktlint")
    id("pokedex.kotlin.serialization")
}

android {
    namespace = "com.malomnogo.list.api"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:model"))
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.navigation3.runtime)
}
