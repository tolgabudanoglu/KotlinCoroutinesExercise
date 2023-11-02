// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        mavenCentral()
    }
    val nav_version = "2.7.5"
    dependencies {
        classpath ("com.android.tools.build:gradle:3.5.4")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
    }
}

plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}