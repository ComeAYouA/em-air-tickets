plugins {
    id("emair.android.library")
    id("emair.androidX")
    id("emair.dagger")
}

android {
    namespace = "com.example.offers"
}

dependencies {
    implementation(libs.material)
    implementation(libs.joda.time)
    implementation(project(":core:network"))
    implementation(project(":core:ui"))
    implementation(project(":core:data"))
}