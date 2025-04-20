plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.devtools.ksp")
    id ("kotlin-kapt")
}

android {
    namespace = "com.eraysirdas.countriesbook"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.eraysirdas.countriesbook"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures{
        dataBinding = true
        viewBinding = true

    }
}

dependencies {


    val retrofitVersion = ("2.9.0")
    val glideVersion = ("4.16.0")
    val rxJavaVersion = ("2.1.1")
    val roomVersion = ("2.7.0")
    val navVersion = ("2.8.9")
    val preferencesVersion = ("1.2.1")
    val lifecycleVersion = ("2.8.7")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")

    implementation ("androidx.room:room-runtime:$roomVersion")
    annotationProcessor ("androidx.room:room-compiler:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")
    implementation ("androidx.room:room-ktx:$roomVersion")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.1")

    implementation ("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation ("androidx.navigation:navigation-ui-ktx:$navVersion")
    implementation ("androidx.fragment:fragment-ktx:1.5.7")

    implementation ("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation ("com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion")

    implementation ("io.reactivex.rxjava2:rxjava:$rxJavaVersion")
    implementation ("io.reactivex.rxjava2:rxandroid:$rxJavaVersion")

    implementation ("com.github.bumptech.glide:glide:$glideVersion")

    implementation ("androidx.palette:palette-ktx:1.0.0")

    implementation ("androidx.preference:preference:$preferencesVersion")

    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01")

}