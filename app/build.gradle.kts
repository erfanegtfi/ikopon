import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("com.google.secrets_gradle_plugin") version "0.6.1"
}


android {
    compileSdk = AndroidConfig.COMPILE_SDK
    buildToolsVersion = AndroidConfig.BUILD_TOOLS_VERSION

    defaultConfig {
        minSdk = AndroidConfig.MIN_SDK
        targetSdk = AndroidConfig.TARGET_SDK
        versionCode = AndroidConfig.VERSION_CODE
        versionName = AndroidConfig.VERSION_NAME
        applicationId = AndroidConfig.ID

        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName(BuildType.DEBUG) {
            isDebuggable = BuildTypeDebug.isDebuggable
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            buildConfigField("String", "SHARED_SECRET_KEY", gradleLocalProperties(rootDir).getProperty("SHARED_SECRET_KEY").toString())
        }

        maybeCreate(BuildType.STAGING)
        getByName(BuildType.STAGING) {
            isDebuggable = BuildTypeStage.isDebuggable
            isMinifyEnabled = BuildTypeStage.isMinifyEnabled
            buildConfigField("String", "SHARED_SECRET_KEY", gradleLocalProperties(rootDir).getProperty("SHARED_SECRET_KEY").toString())
        }

        getByName(BuildType.RELEASE) {
            isDebuggable = BuildTypeRelease.isDebuggable
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            buildConfigField("String", "SHARED_SECRET_KEY", gradleLocalProperties(rootDir).getProperty("SHARED_SECRET_KEY").toString())
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }


    packagingOptions {
        resources.excludes.add("META-INF/atomicfu.kotlin_module")
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    lint {
        // Eliminates UnusedResources false positives for resources used in DataBinding layouts
        isCheckGeneratedSources = true
        // Running lint over the debug variant is enough
        isCheckReleaseBuilds = false
        // See lint.xml for rules configuration
    }


    testOptions {
        unitTests {
            isIncludeAndroidResources = true
//            isReturnDefaultValues = true
        }
    }
}

dependencies {

    implementation(Dependencies.MULTIDEX)

    implementation(Dependencies.KOTLIN_STANDARD_LIBRARY)
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.APP_COMPAT)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.CONSTRAINT_LAYOUT)
    implementation(Dependencies.ARCH_CORE_ANDROIDX)

    implementation(Dependencies.LIFECYCLE_RUNTIME)
    implementation(Dependencies.LIFECYCLE_VIEW_MODEL_KTX)
    implementation(Dependencies.LIFECYCLE_LIVE_DATA)
    implementation(Dependencies.LIFECYCLE_VIEW_MODEL_SAVE_STATE)
    implementation(Dependencies.LIFECYCLE_COMMON)
    kapt(Dependencies.LIFECYCLE_COMPILER)

    implementation(Dependencies.COROUTINE_CORE)
    implementation(Dependencies.COROUTINE_ANDROID)

    implementation(Dependencies.ACTIVITY_KTX)
    implementation(Dependencies.FRAGMENT_KTX)
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.NAVIGATION_UI)
    implementation(Dependencies.ANDROIDX_LEGACY_SUPPORT)


    implementation(Dependencies.GLIDE)
    kapt(Dependencies.GLIDE)
    kapt(Dependencies.GLIDE_COMPILER)
    

    implementation(Dependencies.KOIN)

    implementation(Dependencies.CONVERTER_SCALAR)
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_JSON)
    implementation(Dependencies.OK_HTTP)
    implementation(Dependencies.OK_HTTP_LOGGER)

    implementation(Dependencies.ROOM_KTX)
    implementation(Dependencies.ROOM_RUNTIME)
    kapt(Dependencies.ROOM_COMPILER)


    implementation(Dependencies.TIMBER)
    implementation(Dependencies.DATA_STORE)
    implementation(Dependencies.EVENT_BUS)

    implementation(Dependencies.FCM)
    implementation(Dependencies.AUTH)
    implementation(Dependencies.LOCATION)
    implementation(Dependencies.MAP)

}