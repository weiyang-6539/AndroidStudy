plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
}

android {
    namespace = "com.wyang.study"
    compileSdk = COMPILE_SDK

    defaultConfig {
        applicationId = "com.wyang.study"
        minSdk = MIN_SDK
        targetSdk = TARGET_SDK
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        viewBinding = true
        aidl = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs += "-Xjvm-default=all"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    productFlavors {}
}

dependencies {
//    implementation(fileTree(include(["*.jar"]), dir("libs")))
    implementation(project(":base"))
    implementation(files("libs/pinyin4j-2.5.0.jar"))

//    testImplementation 'junit:junit:4.13.2'
//    androidTestImplementation 'androidx.test.ext:junit:1.1.5'
//    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'
    implementation(libs.multidex)

    //LeakCanary
    debugImplementation(libs.leakcanary.android)
//    debugImplementation 'com.squareup.leakcanary:leakcanary-android:1.6.3'
//    debugImplementation 'com.squareup.leakcanary:leakcanary-support-fragment:1.6.3'
//    releaseImplementation 'com.squareup.leakcanary:leakcanary-android-no-op:1.6.2
}
