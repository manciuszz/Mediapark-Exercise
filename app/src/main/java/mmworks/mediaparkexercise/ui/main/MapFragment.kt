package mmworks.mediaparkexercise.ui.main

import android.util.Log
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import mmworks.mediaparkexercise.ICarListener
import mmworks.mediaparkexercise.Model

class MapFragment : SupportMapFragment(), OnMapReadyCallback, ICarListener {
    override fun update(cars: List<Model.Car>) {
        Log.d("MapFragment", cars.toString())
    }

    private lateinit var mMap: GoogleMap

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}