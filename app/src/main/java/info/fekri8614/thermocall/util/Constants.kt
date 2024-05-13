package info.fekri8614.thermocall.util

import info.fekri8614.thermocall.model.data.ThermoCall
import info.fekri8614.thermocall.model.data.sensor.SensorTemperature

const val IS_USER_FIRST_TIME = "IsUsersFirstTime"

const val BASE_URL = "https://thermocall-api.rubikamp.org/api/v1/"
const val USER_ID = "0SGvUxSRWbXx17hLj9iWAIznLYp2";

const val KEY_USER_EMAIL = "keyUserEmail"
const val KEY_USER_PASSWORD = "keyUserPassword"

const val KEY_SHOW_SENSOR = "keyShowSensorItem"

val FAKE_SENSOR_DATA = listOf<ThermoCall>(
    ThermoCall(id ="user_id_001", label ="Label 1", max= 0, min =0, sensorId ="sensor_001", currentTemperature = SensorTemperature(0, "12:32 pm.")),
    ThermoCall(id ="user_id_002", label ="Label 2", max= 0, min =0, sensorId ="sensor_002", currentTemperature = SensorTemperature(0, "12:32 pm.")),
    ThermoCall(id ="user_id_003", label ="Label 3", max= 0, min =0, sensorId ="sensor_004", currentTemperature = SensorTemperature(0, "12:32 pm.")),
    ThermoCall(id ="user_id_004", label ="Label 4", max= 0, min =0, sensorId ="sensor_005", currentTemperature = SensorTemperature(0, "12:32 pm.")),
    ThermoCall(id ="user_id_005", label ="Label 5", max= 0, min =0, sensorId ="sensor_003", currentTemperature = SensorTemperature(0, "12:32 pm.")),
)
