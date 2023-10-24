plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.complexsoft.location.presentation"
    compileSdk = 34

    defaultConfig {
        minSdk = 27

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":common:utils"))
    implementation(project(":location:domain"))
    implementation(project(":location:data"))
    //Accompanist
    implementation(AccompanistLibraries.permissions)
    //GoogleMaps
    implementation(GoogleMapsLibraries.places)
    implementation(GoogleMapsLibraries.maps_compose)
    implementation(GoogleMapsLibraries.play_services_maps)
    implementation(GoogleMapsLibraries.maps_ktx)
    implementation(GoogleMapsLibraries.maps_utils_ktx)
    //Coil
    implementation(CoilLibraries.coil)
    //Coroutines
    implementation(CoroutinesLibraries.coroutines)
    implementation(CoroutinesLibraries.coroutines_android)
    //DataStore
    implementation(DataStoreLibraries.dataStore)
    //Retrofit
    implementation(RetrofitLibraries.retrofit)
    implementation(RetrofitLibraries.retrofit_converter_gson)
    //WorkManager
    implementation(WorkManagerLibraries.workManager)
    //Hilt
    implementation(HiltLibraries.hilt_android)
    ksp(HiltLibraries.hilt_compiler)
    implementation(HiltLibraries.hilt_navigation_compose)
    //Room
    implementation(RoomLibraries.room_runtime)
    ksp(RoomLibraries.room_compiler)
    implementation(RoomLibraries.room_ktx)
    implementation(RoomLibraries.room_paging)
    //Paging
    implementation(PagingLibraries.paging)
    //Navigation
    implementation(NavigationLibraries.navigation)
    //Activity
    implementation(ActivityLibraries.activity_compose)
    //ViewModel
    implementation(ViewModelLibraries.lifecycle_viewmodel_ktx)
    implementation(ViewModelLibraries.lifecycle_view_model_compose)
    implementation(ViewModelLibraries.lifecycle_runtime_compose)
    //Core
    implementation(CoreLibraries.constraint_layout)
    implementation(CoreLibraries.core)
    implementation(CoreLibraries.lifecycle)
    implementation(platform(CoreLibraries.compose))
    implementation(CoreLibraries.compose_ui)
    implementation(CoreLibraries.compose_ui_graphics)
    implementation(CoreLibraries.compose_ui_tooling_preview)
    implementation(CoreLibraries.compose_material_3)
    testImplementation(CoreLibraries.junit)
    androidTestImplementation(CoreLibraries.test_ext_junit)
    androidTestImplementation(CoreLibraries.espresso)
    androidTestImplementation(platform(CoreLibraries.compose))
    androidTestImplementation(CoreLibraries.compose_ui_test)
    debugImplementation(CoreLibraries.compose_ui_tooling)
    debugImplementation(CoreLibraries.compose_ui_test_manifest)
}