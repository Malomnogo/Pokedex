plugins {
    id("pokedex.jvm.library")
    id("pokedex.ktlint")
    id("pokedex.kotlin.serialization")
    id("pokedex.android.dagger")
}

dependencies {
    implementation(project(":feature:list:domain"))
    implementation(project(":core:data"))
    implementation(project(":core:common"))
    implementation(libs.kotlinx.coroutines.core)
    implementation(project(":core:model"))
    implementation(libs.androidx.paging.common)

    testImplementation(libs.androidx.paging.common)
    testImplementation(libs.androidx.paging.testing)
}
