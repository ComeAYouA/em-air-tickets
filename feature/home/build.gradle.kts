plugins {
    id("emair.android.library")
    id("emair.androidX")
    id("emair.dagger")
}

android {
    namespace = "com.example.home"
}

dependencies {
    implementation(libs.material)
    implementation(libs.fragment.ktx)
    implementation(project(":core:ui"))
    implementation(project(":core:network"))
    implementation(project(":core:data"))
}