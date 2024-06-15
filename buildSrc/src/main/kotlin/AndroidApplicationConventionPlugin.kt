import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            androidApp().apply {
                compileSdk = Versions.compileSdk

                defaultConfig {
                    minSdk = Versions.minSdk
                    lint.targetSdk = Versions.targetSdk
                    versionCode = 1
                    versionName = "1.0"
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }

                buildTypes{
                    release {
                        isMinifyEnabled = false
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro"
                        )
                    }
                }

                compileOptions {
                    sourceCompatibility = Versions.javaVersion
                    targetCompatibility = Versions.javaVersion
                }

                buildFeatures {
                    viewBinding = true
                }
            }

            dependencies {
                add(
                    "testImplementation",
                    versionCatalog.findLibrary("junit").get()
                )
                add(
                    "androidTestImplementation",
                    versionCatalog.findLibrary("androidx-test-ext-junit").get()
                )
                add(
                    "androidTestImplementation",
                    versionCatalog.findLibrary("espresso-core").get()
                )
            }
        }
    }
}