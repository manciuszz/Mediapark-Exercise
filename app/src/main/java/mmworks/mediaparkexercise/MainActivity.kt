package mmworks.mediaparkexercise

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import mmworks.mediaparkexercise.ui.main.TabsAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var disposable: Disposable? = null
    private val apiServe by lazy {
        CarService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewpager_main.adapter = TabsAdapter(this, supportFragmentManager)
        tabs_main.setupWithViewPager(viewpager_main)

        consumeCars()
    }

    private fun consumeCars() {
        disposable = apiServe.getCars()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                        cars -> cars.forEach { car -> Log.d("[Car]", car.toString()) }
                },
                {
                        error -> Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                }
            )
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}