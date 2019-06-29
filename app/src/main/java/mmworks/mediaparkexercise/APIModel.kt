package mmworks.mediaparkexercise

object APIModel {
    data class Car(
        val batteryEstimatedDistance: Double,
        val batteryPercentage: Int,
        val id: Int,
        val isCharging: Boolean,
        val location: Location,
        val model: Model,
        val plateNumber: String
    )

    data class Model(
        val id: Int,
        val photoUrl: String,
        val title: String
    )

    data class Location(
        val address: String,
        val id: Int,
        val latitude: Double,
        val longitude: Double
    )
}