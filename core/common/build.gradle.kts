plugins {
    id("pokedex.jvm.library")
    id("pokedex.ktlint")
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.annotation)
    
    // Koin
    implementation(platform(libs.koin.bom)) // BOM обязателен для управления версиями
    implementation(libs.koin.core)
}
