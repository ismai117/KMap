package org.ncgroup.kmap

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
actual fun GoogleMapView(
    modifier: Modifier,
    vararg googleMapViewEntries: GoogleMapViewEntries,
    zoom: Float
) {

    val latLng: LatLng? = if (googleMapViewEntries.isNotEmpty()){
        LatLng(googleMapViewEntries.first().lat, googleMapViewEntries.first().lng)
    }else{
        null
    }

    val cameraPositionState = rememberCameraPositionState {
        latLng?.let { position = CameraPosition.fromLatLngZoom(it, zoom) }
    }

    val mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                zoomGesturesEnabled = true
            )
        )
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = mapUiSettings
    ) {
        googleMapViewEntries.forEach { entry ->
            Marker(
                state = MarkerState(
                    position = LatLng(entry.lat, entry.lng)
                ),
                title = entry.title,
                snippet = entry.snippet
            )
        }
    }

}