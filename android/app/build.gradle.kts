plugins {
    alias(libs.plugins.android.application)
    id("org.jetbrains.kotlin.plugin.compose") version "2.3.20" // esta versao ja gerencia a compatibilidade do compilador automaticamente com a versao do Kotlin.
}

android {
    namespace = "com.escolanovaeratech.babytracker"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.escolanovaeratech.babytracker"
        minSdk = 24
        targetSdk = 36
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
    buildFeatures {
        // Ativando o Compose no projeto.
        compose = true
        viewBinding = true
    }



    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Gerenciamento de versões via BoM (Bill of Materials)
    val composeBom = platform("androidx.compose:compose-bom:2026.03.01")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    // Dependências principais do Compose (As bibliotecas abaixo usarão as versões definidas pelo BoM automaticamente)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.tooling.preview)
    debugImplementation(libs.androidx.ui.tooling)

    // Integração com Activity e Lifecycle
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}