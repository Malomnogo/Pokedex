plugins {
    id("pokedex.android.library")
    id("pokedex.android.library.compose")
    id("pokedex.ktlint")
    id("pokedex.android.dagger")
}

android {
    namespace = "com.malomnogo.ui"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    // Compose dependencies needed for WebImage.kt
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.material3) // Pulls in foundation
    implementation(libs.coil.compose)
    
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
