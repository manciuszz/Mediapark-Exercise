package mmworks.mediaparkexercise.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_list.*
import mmworks.mediaparkexercise.*

class ListFragment : Fragment() {
    private lateinit var carsViewModel: CarViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        carsViewModel = ViewModelProviders.of(activity as MainActivity).get(CarViewModel::class.java)
        carsViewModel.observer(this, { displayCars(it) })
    }

    private fun displayCars(carsList: List<APIModel.Car>) {
        val context = requireContext()
        val adapter = Car2CardAdapter(context, carsList)
        car_listview.adapter = adapter

//        carsList.onItemClickListener = AdapterView.OnItemClickListener {
//                parent, view, position, id ->
//            Toast.makeText(context,"Position :$position", Toast.LENGTH_SHORT).show()
//        }
    }
}
