package org.ncgroup.kmap

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import cocoapods.GoogleMaps.GMSAdvancedMarker.Companion.markerImageWithColor
import cocoapods.GoogleMaps.GMSCameraPosition
import cocoapods.GoogleMaps.GMSMapView
import cocoapods.GoogleMaps.GMSMapView.Companion.mapWithFrame
import cocoapods.GoogleMaps.GMSMarker
import cocoapods.GoogleMaps.animateToZoom
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreLocation.CLLocationCoordinate2DMake
import platform.UIKit.UIColor


@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun GoogleMapView(
    modifier: Modifier,
    vararg googleMapViewEntries: GoogleMapViewEntries,
    zoom: Float
) {
    UIKitView(
        factory = {
            val camera: GMSCameraPosition? = if (googleMapViewEntries.isNotEmpty()){
                GMSCameraPosition.cameraWithLatitude(
                    latitude = googleMapViewEntries.first().lat,
                    longitude = googleMapViewEntries.first().lng,
                    zoom = zoom
                )
            }else{
                null
            }

            val mapView =  GMSMapView()
            camera?.let { mapWithFrame(frame = mapView.frame, camera = it) }
            mapView.settings.zoomGestures = true
            mapView.settings.consumesGesturesInView = true
            googleMapViewEntries.forEach { entry ->
                GMSMarker().apply {
                    this.position = CLLocationCoordinate2DMake(
                        latitude = entry.lat,
                        longitude = entry.lng
                    )
                    this.title = entry.title
                    this.snippet = entry.snippet
                    markerImageWithColor(UIColor.redColor)
//                    this.markerImageWithColor(color = UIColor.redColor)
                }.map = mapView
            }
            mapView
        },
        modifier = modifier,
        onRelease = {
            it.removeFromSuperview()
        }
    )
}