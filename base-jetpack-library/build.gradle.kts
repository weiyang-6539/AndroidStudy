plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.dagger.hilt)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.fxbandroid.basejetpack"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    api(libs.androidx.appcompat)
    api(libs.androidx.constraintlayout)
    api(libs.androidx.recyclerview)
    api(libs.androidx.paging3)
    api(libs.androidx.core.ktx)
    api(libs.androidx.fragment.ktx)
    api(libs.material)
    api(libs.flexbox)
    api(libs.bundles.retrofit2.group) {
        exclude("xpp3", "xpp3")
        exclude("stax", "stax-api")
        exclude("stax", "stax")
    }
    api(libs.kotlinx.coroutines.android)
    api(libs.bundles.lifecycle.group.ktx)
    api(libs.bundles.navigation.group.ktx)
    api(libs.androidx.datastore.preferences)
    api(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    api(libs.glide)
    api(libs.wasabeef.glide.transformations)
    api(libs.gson)
    api(libs.eventbus)
    api(libs.baserecyclerviewadapterhelper4)
}