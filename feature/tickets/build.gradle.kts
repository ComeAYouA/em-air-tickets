plugins {
    id("emair.android.library")
    id("emair.androidX")
    id("emair.dagger")
}

android {
    namespace = "com.example.tickets"
}

dependencies {
    implementation(libs.material)
    implementation(project(":core:ui"))
    implementation(project(":core:network"))
}