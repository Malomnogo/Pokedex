import com.malomnogo.pokedex.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidDaggerConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
            }

            dependencies {
                add("implementation", libs.findLibrary("dagger").get())
                add("ksp", libs.findLibrary("dagger-compiler").get())
                add("implementation", libs.findLibrary("javax-inject").get())
            }
        }
    }
}
