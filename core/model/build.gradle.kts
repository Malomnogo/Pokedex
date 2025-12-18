plugins {
    id("pokedex.jvm.library")
    id("pokedex.ktlint")
    id("pokedex.kotlin.serialization") // <-- Добавляем этот плагин
}

dependencies {
    // implementation(libs.kotlinx.serialization.json) // Это уже есть внутри плагина, можно убрать или оставить
}
