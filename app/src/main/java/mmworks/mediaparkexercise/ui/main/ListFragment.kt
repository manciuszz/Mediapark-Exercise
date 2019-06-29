package mmworks.mediaparkexercise.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_list.*
import mmworks.mediaparkexercise.ICarListener
import mmworks.mediaparkexercise.APIModel
import mmworks.mediaparkexercise.R

class ListFragment : Fragment(), ICarListener {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun update(cars: List<APIModel.Car>) {
        val context = requireContext()
        val adapter = Car2CardAdapter(context, cars)
        carsList.adapter = adapter

//        carsList.onItemClickListener = AdapterView.OnItemClickListener {
//            parent, view, position, id ->
//                Toast.makeText(context,"Position :$position", Toast.LENGTH_SHORT).show()
//        }
    }

}
