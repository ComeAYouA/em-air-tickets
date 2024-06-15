plugins {
    id("emair.android.library")
    id("emair.androidX")
}

android {
    namespace = "com.example.places"
}

dependencies {
    implementation(libs.material)
    implementation(project(":core:ui"))
}