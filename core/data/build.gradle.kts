plugins {
    id("emair.android.library")
    id("emair.dagger")
    id("kotlinx-serialization")
}

android {
    namespace = "com.example.data"
}

dependencies {

    implementation(libs.androidx.core.ktx)
}