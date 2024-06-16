plugins {
    id("emair.android.application")
    id("emair.androidX")
    id("emair.dagger")
}

android {
    namespace = "com.example.emair"
}

dependencies {

    implementation(libs.material)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.retrofit)


    implementation(project(":core:ui"))
    implementation(project(":core:network"))
    implementation(project(":core:data"))
    implementation(project(":feature:tickets"))
    implementation(project(":feature:hotels"))
    implementation(project(":feature:places"))
    implementation(project(":feature:profile"))
    implementation(project(":feature:subscriptions"))
}