plugins {
    id("com.android.application")
    id("com.google.gms.google-services") // Google services plugin
}

android {
    namespace = "com.medalert"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.medalert"
        minSdk = 24
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    // Enable Google Maps SDK for Android
    buildFeatures {
        viewBinding = true // Optional if you want to use ViewBinding
    }
}

dependencies {
    // Google Play Services (Required for Google Maps)
    implementation ("com.google.android.gms:play-services-maps:18.0.2") // Google Maps SDK
    implementation ("com.google.android.gms:play-services-location:21.3.0") // Location services

    // Google Places API
    implementation ("com.google.android.libraries.places:places:2.7.0") // Places API for nearby places search

    // Firebase Libraries (Firebase SDK for analytics, authentication, database, etc.)
    implementation ("com.google.firebase:firebase-bom:33.6.0")
    implementation ("com.google.firebase:firebase-analytics")
    implementation ("com.google.firebase:firebase-auth")
    implementation ("com.google.firebase:firebase-database")

    // Testing Libraries
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}
