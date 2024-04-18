plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("kapt")
    alias(libs.plugins.hiltPlugin)
}

android {
    namespace = "com.example.criticalnewsfeedapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.criticalnewsfeedapp"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    flavorDimensions += "source"
    productFlavors {
        create("bbc") {
            dimension = "source"
            applicationIdSuffix = ".bbc"
            versionNameSuffix = "-bbc"

            resValue("string", "app_name", "BBC News Feed")
            resValue("string", "newsSource", "bbc-news")
            resValue("string", "newsSourceName", "BBC News")
        }
        create("cnn") {
            dimension = "source"
            applicationIdSuffix = ".cnn"
            versionNameSuffix = "-cnn"

            resValue("string", "app_name", "CNN News Feed")
            resValue("string", "newsSource", "cnn")
            resValue("string", "newsSourceName", "CNN News")

            resValue("color", "colorPrimary", "@color/dark_red")
            resValue("color", "colorPrimaryDark", "@color/dark_red")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"

    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.cardview)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.window)
    implementation(libs.androidx.window.core)
    implementation(libs.androidx.biometric)

    //http - rest
    implementation(libs.okHttp)
    implementation(libs.retrofit)
    implementation(libs.retrofitGsonConverter)
    implementation(libs.gson)

    //image loading
    implementation(libs.picasso)

    //dependency injection
    implementation(libs.hiltAndroid)
    implementation(libs.androidx.lifecycle.runtime.testing)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.espresso.contrib)
    kapt(libs.hiltCompiler)

    //Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    //Testing
    testImplementation(libs.junit)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.roboelectric)
    testImplementation(libs.androidx.test.core.ktx)
    testImplementation(libs.androidx.navigation.testing.ktx)
    testImplementation(libs.mokito.kotlin)

    androidTestImplementation(libs.androidx.core.ktx)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.navigation.testing.ktx)
}

kapt {
    correctErrorTypes = true
}
