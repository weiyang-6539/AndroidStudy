plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    namespace = "com.w6539android.base"
    compileSdk = COMPILE_SDK

    defaultConfig {
        minSdk = MIN_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    buildFeatures {
        viewBinding = true
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs += "-Xjvm-default=all"
    }
}

dependencies {
    api(libs.androidx.appcompat)
    api(libs.androidx.constraintlayout)
    api(libs.androidx.recyclerview)
    api(libs.androidx.paging3)
    api(libs.material)
    api(libs.flexbox)
    api(libs.retrofit2.retrofit)
    api(libs.converter.gson)
    api(libs.converter.scalars)
    api(libs.converter.simplexml) {
        exclude("xpp3", "xpp3")
        exclude("stax", "stax-api")
        exclude("stax", "stax")
    }
    api(libs.okhttp3.logging.interceptor)
    api(libs.androidx.core.ktx)
    api(libs.androidx.fragment.ktx)
    api(libs.kotlinx.coroutines.android)
    api(libs.bundles.lifecycle.group.ktx)
    api(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    api(libs.glide)
    api(libs.gson)
    api(libs.eventbus)
    api(libs.baserecyclerviewadapterhelper4)
}