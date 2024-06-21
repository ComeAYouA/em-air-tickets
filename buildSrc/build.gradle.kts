import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.libsDirectory
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}


dependencies{
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.gradle.build.tools)
    implementation(libs.javapoet)
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "17"
}


gradlePlugin{
    plugins {
        register("androidApplicationConvention") {
            id = "emair.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibraryConvention") {
            id = "emair.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidXConvention") {
            id = "emair.androidX"
            implementationClass = "AndroidXConventionPlugin"
        }
        register("DaggerConvention") {
            id = "emair.dagger"
            implementationClass = "DaggerConventionPlugin"
        }
        register("JvmConvention") {
            id = "emair.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
    }
}