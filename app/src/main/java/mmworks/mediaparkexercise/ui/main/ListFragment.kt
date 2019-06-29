package mmworks.mediaparkexercise.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_list.*
import mmworks.mediaparkexercise.ICarListener
import mmworks.mediaparkexercise.Model
import mmworks.mediaparkexercise.R

class ListFragment : Fragment(), ICarListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun update(cars: List<Model.Car>) {
        val adapter = ArrayAdapter(requireContext(), R.layout.listview_car, cars)
        carsList.adapter = adapter
    }

}
