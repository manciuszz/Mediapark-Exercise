package mmworks.mediaparkexercise

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

interface ICarListener {
    fun update(cars: List<Model.Car>)
}

interface CarService {

    @GET("mobile/public/availablecars")
    fun getCars(): Observable<List<Model.Car>>

    companion object {
        private const val BASE_URL = "https://development.espark.lt/api/"

        fun create(): CarService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(CarService::class.java)
        }
    }

}