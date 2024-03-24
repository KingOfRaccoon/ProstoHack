import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.serilization)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    jvm("desktop")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        val ktorVersion = "2.3.2"
        val serializationVersion = "1.2.2"
        val coroutinesVersion = "1.5.0-native-mt"
        val desktopMain by getting
        val commonMain by getting

        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
            implementation("io.insert-koin:koin-android:3.5.3")
            implementation("io.ktor:ktor-client-cio:2.3.2")
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation("io.insert-koin:koin-compose:1.1.2")
            implementation("media.kamel:kamel-image:0.9.3")
            implementation("moe.tlaster:precompose:1.5.11")
            implementation("io.insert-koin:koin-core:3.2.0")
            implementation("com.russhwolf:multiplatform-settings-no-arg:1.1.1")
            implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
            implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
            implementation("io.ktor:ktor-client-json:$ktorVersion")
            implementation("io.ktor:ktor-client-auth:$ktorVersion")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")
            implementation("io.ktor:ktor-client-serialization:$ktorVersion")
            implementation("io.ktor:ktor-client-core:$ktorVersion")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
            implementation("sh.calvin.reorderable:reorderable:1.3.3")
        }

        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation("io.ktor:ktor-client-cio:2.3.2")
        }

        val iosX64Main by getting {
            dependencies {
                dependsOn(commonMain)
//                implementation("io.ktor:ktor-client-cio:2.3.2")
                implementation("io.ktor:ktor-client-darwin:2.3.2")
            }
        }
        val iosArm64Main by getting {
            dependencies {
                dependsOn(commonMain)
//                implementation("io.ktor:ktor-client-cio:2.3.2")
                implementation("io.ktor:ktor-client-darwin:2.3.2")
            }
        }
        val iosSimulatorArm64Main by getting {
            dependencies {
                dependsOn(commonMain)
//                implementation("io.ktor:ktor-client-cio:2.3.2")
                implementation("io.ktor:ktor-client-darwin:2.3.2")
            }
        }
    }
}

android {
    namespace = "ru.skittens.prostohack"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "ru.skittens.prostohack"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "ru.skittens.prostohack"
            packageVersion = "1.0.0"
        }
    }
}
