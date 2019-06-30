package mmworks.mediaparkexercise

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.content.Context
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

    private fun subscribe(): MutableLiveData<List<APIModel.Car>> {
        if (!carsList.hasActiveObservers())
            loadCars()
        return carsList
    }

    fun observer(context: LifecycleOwner, callback: (carsList: List<APIModel.Car>) -> Unit) {
        subscribe().observe(context, Observer {
            carsList -> callback(carsList!!)
        })
    }

    private fun loadCars() {
        disposable = apiServe.getCars()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                cars -> carsList.postValue(cars)
            },{
                error -> Log.d("CarViewModel", error.toString())
            })
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}