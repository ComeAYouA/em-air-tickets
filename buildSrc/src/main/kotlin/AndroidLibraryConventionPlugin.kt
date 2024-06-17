import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            androidLib().apply {
                compileSdk = Versions.compileSdk

                defaultConfig {
                    minSdk = Versions.minSdk
                    lint.targetSdk = Versions.targetSdk
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
                    dataBinding = true
                }
            }

            dependencies {
                add(
                    "implementation",
                    versionCatalog.findLibrary("androidx-navigation-fragment-ktx").get()
                )
                add(
                    "implementation",
                    versionCatalog.findLibrary("androidx-navigation-ui-ktx").get()
                )
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