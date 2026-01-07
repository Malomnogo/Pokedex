plugins {
    id("pokedex.jvm.library")
    id("pokedex.ktlint")
    id("pokedex.kotlin.serialization")
}

dependencies {
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)

    implementation(project(":feature:list:domain"))
    implementation(project(":core:data"))
    implementation(project(":core:common"))
    implementation(libs.kotlinx.coroutines.core)
    implementation(project(":core:model"))
    implementation(libs.androidx.paging.common)

    testImplementation(libs.androidx.paging.common)
    testImplementation(libs.androidx.paging.testing)
}
