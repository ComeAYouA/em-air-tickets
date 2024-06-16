plugins {
    id("emair.android.library")
    id("emair.dagger")
    id("kotlinx-serialization")
}

android {
    namespace = "com.example.network"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.retrofit)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp)
}