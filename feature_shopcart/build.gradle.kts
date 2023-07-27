plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "br.gohan.feature_carrinho"
    compileSdk = 33

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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
}

dependencies {
    implementation(AndroidX.core)
    implementation(Compose.material3)
    implementation(platform(Compose.bom))
    implementation(Compose.ui)
    implementation(Compose.tooling)
    implementation(Compose.material3)
    implementation(Compose.lifecycle)

    implementation(Coil.compose)
    implementation(Coil.core)

    implementation(Koin.android)
    implementation(Koin.compose)

    implementation(project(Project.appCore))
    implementation(AndroidX.lifecycle)

    val retrofit_version = "2.9.0"
    implementation("com.squareup.retrofit2:converter-gson:$retrofit_version")
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
}