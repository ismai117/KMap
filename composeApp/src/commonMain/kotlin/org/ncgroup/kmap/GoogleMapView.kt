package org.ncgroup.kmap

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


data class GoogleMapViewEntries(
    val title: String,
    val snippet: String,
    val lat: Double,
    val lng: Double,
)

@Composable
expect fun GoogleMapView(
    modifier: Modifier = Modifier,
    vararg googleMapViewEntries: GoogleMapViewEntries,
    zoom: Float = 10f
)