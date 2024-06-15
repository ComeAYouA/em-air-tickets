import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidXConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                add(
                    "implementation",
                    versionCatalog.findLibrary("androidx-core-ktx").get()
                )
                add(
                    "implementation",
                    versionCatalog.findLibrary("androidx-appcompat").get()
                )
                add(
                    "implementation",
                    versionCatalog.findLibrary("androidx-constraintlayout").get()
                )
                add(
                    "implementation",
                    versionCatalog.findLibrary("androidx-lifecycle-viewmodel-ktx").get()
                )
            }
        }
    }
}