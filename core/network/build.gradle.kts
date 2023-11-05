import org.jetbrains.kotlin.konan.properties.Properties

// TODO: remove suppress
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt)
    kotlin("kapt")
}

android {
    namespace = "io.github.shofucchi.network"
    compileSdk = 33

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        val apiKey = Properties().readLocalProperties(key = "API_KEY")
        buildConfigField("String", "API_KEY", "\"${apiKey}\"")
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
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    implementation(libs.androidx.ktx)
    implementation(libs.retrofit2)
    implementation(libs.retrofit2.kotlin.serialization.converter)
    implementation(libs.okhttp3)
    implementation(libs.kotlinx.serialization)
    implementation(libs.kotlinx.coroutines)
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
}

fun Properties.readLocalProperties(key: String) = run {
    this.load(project.rootProject.file("local.properties").inputStream())
    return@run this.getProperty(key)
}