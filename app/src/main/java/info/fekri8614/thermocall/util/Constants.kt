package info.fekri8614.thermocall.util

import info.fekri8614.thermocall.R
import info.fekri8614.thermocall.model.data.ThermoCall

const val IS_USER_FIRST_TIME = "IsUsersFirstTime"

const val BASE_URL = "https://thermocall-api.rubikamp.org/api/v1/"

const val KEY_USER_NAME = "keyUserName"

val FAKE_SENSOR_DATA = listOf<ThermoCall>(
    ThermoCall(id ="user_id_001", label ="Label 1", max= 0, min =0, sensorId ="sensor_001"),
    ThermoCall(id ="user_id_002", label ="Label 2", max= 0, min =0, sensorId ="sensor_002"),
    ThermoCall(id ="user_id_003", label ="Label 3", max= 0, min =0, sensorId ="sensor_004"),
    ThermoCall(id ="user_id_004", label ="Label 4", max= 0, min =0, sensorId ="sensor_005"),
    ThermoCall(id ="user_id_005", label ="Label 5", max= 0, min =0, sensorId ="sensor_003"),
)

const val NOTIFICATION_CHANNEL_ID = "DEFAULT_NOTIFICATION_CHANNEL"
const val REQUEST_CODE_POST_NOTIFICATION_PERMISSION = 1001
