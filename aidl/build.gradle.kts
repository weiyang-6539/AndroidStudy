plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.aidl"
    compileSdk = COMPILE_SDK
    defaultConfig {
        applicationId = "com.example.aidl"
        minSdk = MIN_SDK
        targetSdk = TARGET_SDK
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        val release by getting {
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
    buildFeatures {
        aidl = true
    }
}

dependencies {
    implementation(project(":base"))
}