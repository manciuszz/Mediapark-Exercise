package mmworks.mediaparkexercise.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.listview_car.view.*
import mmworks.mediaparkexercise.APIModel
import mmworks.mediaparkexercise.R

class Car2CardAdapter(private val context: Context, private val carList: List<APIModel.Car>) : BaseAdapter() {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return carList.size
    }

    override fun getItem(position: Int): APIModel.Car {
        return carList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder") // TODO: Doesn't Kotlin Extensions cache findViewById stuff?
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = inflater.inflate(R.layout.listview_car, parent,false)
        val car = getItem(position)

        rowView.card_car_title.text = car.model.title
        rowView.card_car_plate.text = car.plateNumber
        rowView.card_car_address.text = car.location.address
        if ((car.model.photoUrl).isNotEmpty())
            Picasso.with(context).load(car.model.photoUrl).into(rowView.card_car_photo)
        return rowView
    }

}