plugins {
    id("pokedex.jvm.library")
    id("pokedex.ktlint")
    id("pokedex.android.dagger")
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:model"))
    implementation(project(":core:common")) // Added for HandleError/ProvideResources
    
    implementation(libs.kotlinx.serialization.json) // Added for Json
    implementation(libs.retrofit.core) // Added for HttpException
}
