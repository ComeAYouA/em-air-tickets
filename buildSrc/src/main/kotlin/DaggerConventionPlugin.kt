import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class DaggerConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager){
                apply("kotlin-kapt")
            }
            dependencies {
                add(
                    "implementation",
                    versionCatalog.findLibrary("dagger").get()
                )
                add(
                    "kapt",
                    versionCatalog.findLibrary("dagger-compiler").get()
                )
            }
        }
    }
}