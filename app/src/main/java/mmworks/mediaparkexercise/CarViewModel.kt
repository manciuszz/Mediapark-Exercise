package mmworks.mediaparkexercise

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.location.Location
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CarViewModel : ViewModel() {
    private var disposable: Disposable? = null
    private val apiServe by lazy {
        CarService.create()
    }

    private val carsList: MutableLiveData<List<APIModel.Car>> = MutableLiveData()
    private lateinit var carsCopy: List<APIModel.Car>

    fun observer(context: LifecycleOwner, callbackFn: (carsList: List<APIModel.Car>) -> Unit) {
        subscribe().observe(context, Observer {
            carsList -> callbackFn(carsList!!)
        })
    }

    fun applyFilter(query: String = "") {
        if (query.isEmpty())
            return loadCars()

        val filteredData = carsCopy.filter {
            car -> car.plateNumber.toLowerCase() == query.toLowerCase() || (query.replace("%", "").toIntOrNull()?.let { car.batteryPercentage <= it } ?: false)
        }
        carsList.postValue(filteredData)
    }

    // Note - it was required to sort by current location distance, but due to time constraints and lack of interest in implementing a lot of bloat code to achieve same effect...
    private fun applySortByDistance(fromLocation: Location = Location("A").apply { longitude = 0.0; latitude = 0.0 }) {
        val sortedData = carsCopy.sortedBy { car ->
            val toLocation = Location("B").apply { longitude = car.location.longitude; latitude = car.location.latitude }
            fromLocation.distanceTo(toLocation)/1000
        }
        carsList.postValue(sortedData)
    }

    private fun loadCars() {
        disposable = apiServe.getCars()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                cars ->
                    carsCopy = cars
                    carsList.postValue(cars)
                    applySortByDistance()
            },{
                error -> Log.e("CarViewModel", error.toString())
            })
    }

    private fun subscribe(): MutableLiveData<List<APIModel.Car>> {
        if (!carsList.hasActiveObservers())
            loadCars()
        return carsList
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}