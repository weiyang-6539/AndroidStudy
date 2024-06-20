plugins {
    id("com.android.application") version "8.3.1" apply false
    id("org.jetbrains.kotlin.android") version "2.0.0" apply false
    id("com.android.library") version "8.3.1" apply false
    id("com.google.devtools.ksp") version "2.0.0-1.0.21"
}

buildscript {
    dependencies {
        classpath(libs.gradle)
        //kotlin插件
        classpath(libs.kotlin.gradle.plugin)
        //Hilt插件
        classpath(libs.dagger.hilt.android.gradle.plugin)
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}
