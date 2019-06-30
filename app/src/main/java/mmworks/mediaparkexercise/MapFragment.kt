package mmworks.mediaparkexercise

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : SupportMapFragment(), OnMapReadyCallback {
    private lateinit var carsViewModel: CarViewModel
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        carsViewModel = ViewModelProviders.of(activity as MainActivity).get(CarViewModel::class.java)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        carsViewModel.observer(this, { carsList -> displayCars(carsList) })
    }

    private fun displayCars(carsList: List<APIModel.Car>) {
        mMap.clear()

        val mOpts = MarkerOptions()
        carsList.forEachIndexed { index, car ->
            val carCoordinates = LatLng(car.location.latitude, car.location.longitude)
            if (index == 0) mMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(carCoordinates, 10f, 0f, 45f)))
            mMap.addMarker(mOpts.position(carCoordinates).title(car.plateNumber))
        }
    }

}