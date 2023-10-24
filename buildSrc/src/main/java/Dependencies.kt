object Versions {
    const val core = "1.12.0"
    const val lifecycle = "2.6.2"
    const val compose = "2023.10.01"
    const val junit = "4.13.2"
    const val test_ext_junit = "1.1.5"
    const val espresso = "3.5.1"
    const val activity_version = "1.8.0"
    const val navigation_version = "2.7.4"
    const val paging_version = "3.3.0-alpha02"
    const val room_version = "2.6.0"
    const val hilt_version = "2.48.1"
    const val hilt_navigation_compose_version = "1.0.0"
    const val work_version = "2.8.1"
    const val retrofit_version = "2.9.0"
    const val converter_gson = "2.10.1"
    const val data_store_preferences = "1.0.0"
    const val coroutines_version = "1.7.3"
    const val coil_version = "2.4.0"
    const val google_places_version = "3.2.0"
    const val google_maps_compose_version = "2.15.0"
    const val google_play_services_maps_version = "18.1.0"
    const val maps_ktx_version = "3.4.0"
    const val maps_utils_ktx_version = "3.2.1"
    const val play_services_location_version = "21.0.1"
    const val accompanist_version = "0.32.0"
    const val constraint_layout_version = "1.1.0-alpha13"
}

object AccompanistLibraries {
    const val permissions =
        "com.google.accompanist:accompanist-permissions:${Versions.accompanist_version}"
}

object GoogleMapsLibraries {
    const val play_services_location =
        "com.google.android.gms:play-services-location:${Versions.play_services_location_version}"

    const val places =
        "com.google.android.libraries.places:places:${Versions.google_places_version}"
    const val maps_compose =
        "com.google.maps.android:maps-compose:${Versions.google_maps_compose_version}"
    const val play_services_maps =
        "com.google.android.gms:play-services-maps:${Versions.google_play_services_maps_version}"
    const val maps_ktx = "com.google.maps.android:maps-ktx:${Versions.maps_ktx_version}"
    const val maps_utils_ktx =
        "com.google.maps.android:maps-utils-ktx:${Versions.maps_utils_ktx_version}"
}

object CoilLibraries {
    const val coil = "io.coil-kt:coil-compose:${Versions.coil_version}"
}

object CoroutinesLibraries {
    const val coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_version}"
    const val coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_version}"
}

object DataStoreLibraries {
    const val dataStore =
        "androidx.datastore:datastore-preferences:${Versions.data_store_preferences}"
}

object RetrofitLibraries {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    const val retrofit_converter_gson =
        "com.squareup.retrofit2:converter-gson:${Versions.retrofit_version}"
}

object WorkManagerLibraries {
    const val workManager = "androidx.work:work-runtime-ktx:${Versions.work_version}"
}

object HiltLibraries {
    const val hilt_android = "com.google.dagger:hilt-android:${Versions.hilt_version}"
    const val hilt_compiler = "com.google.dagger:hilt-compiler:${Versions.hilt_version}"
    const val hilt_navigation_compose =
        "androidx.hilt:hilt-navigation-compose:${Versions.hilt_navigation_compose_version}"
}

object RoomLibraries {
    const val room_runtime = "androidx.room:room-runtime:${Versions.room_version}"
    const val room_compiler = "androidx.room:room-compiler:${Versions.room_version}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.room_version}"
    const val room_paging = "androidx.room:room-paging:${Versions.room_version}"
}

object PagingLibraries {

    const val paging = "androidx.paging:paging-compose:${Versions.paging_version}"
}

object NavigationLibraries {
    const val navigation = "androidx.navigation:navigation-compose:${Versions.navigation_version}"
}

object ActivityLibraries {
    const val activity_compose = "androidx.activity:activity-compose:${Versions.activity_version}"
}


object ViewModelLibraries {
    const val lifecycle_viewmodel_ktx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycle_view_model_compose =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle}"
    const val lifecycle_runtime_compose =
        "androidx.lifecycle:lifecycle-runtime-compose:${Versions.lifecycle}"
}

object CoreLibraries {
    const val constraint_layout =
        "androidx.constraintlayout:constraintlayout-compose:${Versions.constraint_layout_version}"
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val compose = "androidx.compose:compose-bom:${Versions.compose}"
    const val compose_ui = "androidx.compose.ui:ui"
    const val compose_ui_graphics = "androidx.compose.ui:ui-graphics"
    const val compose_ui_tooling_preview = "androidx.compose.ui:ui-tooling-preview"
    const val compose_material_3 = "androidx.compose.material3:material3"
    const val junit = "junit:junit:${Versions.junit}"
    const val test_ext_junit = "androidx.test.ext:junit:${Versions.test_ext_junit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val compose_ui_test = "androidx.compose.ui:ui-test-junit4"
    const val compose_ui_tooling = "androidx.compose.ui:ui-tooling"
    const val compose_ui_test_manifest = "androidx.compose.ui:ui-test-manifest"
}