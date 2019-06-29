package mmworks.mediaparkexercise.ui.main

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import mmworks.mediaparkexercise.ICarListener
import mmworks.mediaparkexercise.APIModel

class MapFragment : SupportMapFragment(), OnMapReadyCallback, ICarListener {
    private lateinit var mMap: GoogleMap
    private var carsList: List<APIModel.Car> = listOf()

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        displayCars()
    }

    override fun update(cars: List<APIModel.Car>) {
        carsList = cars
        displayCars()
    }

    private fun displayCars() {
        carsList.forEachIndexed { index, car ->
            if (index == 0) mMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(LatLng(car.location.latitude, car.location.longitude), 10f, 0f, 45f)))
            mMap.addMarker(MarkerOptions().position(LatLng(car.location.latitude, car.location.longitude)).title(car.plateNumber))
        }
    }

}