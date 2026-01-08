plugins {
    id("pokedex.jvm.library")
    id("pokedex.ktlint")
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:model"))
}
