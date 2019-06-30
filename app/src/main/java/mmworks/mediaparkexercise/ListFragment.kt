package mmworks.mediaparkexercise

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {
    private lateinit var carsViewModel: CarViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        carsViewModel = ViewModelProviders.of(activity as MainActivity).get(CarViewModel::class.java)
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        carsViewModel.observer(this, { carsList -> displayCars(carsList) })
        applySearchBar()
    }

    private fun applySearchBar() {
        car_search_filter_view.run {
            visibility = View.VISIBLE
            queryHint = context.resources.getString(R.string.search_query_hint)
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    if (newText.isEmpty())
                        carsViewModel.applyFilter()
                    return false
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    carsViewModel.applyFilter(query)
                    return false
                }
            })
        }
    }

    private fun displayCars(carsList: List<APIModel.Car>) {
        val context = requireContext()
        val adapter = Car2CardAdapter(context, carsList)
        car_listview.adapter = adapter

//        car_listview.onItemClickListener = AdapterView.OnItemClickListener {
//                parent, view, position, id ->
//            Toast.makeText(context,"Position :$position", Toast.LENGTH_SHORT).show()
//        }
    }
}
