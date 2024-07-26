package info.fekri8614.thermocall.util

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class MyDateFormatter {
    private val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy, HH:mm:ss", Locale.getDefault())

    fun getDateString(time: Long): String? = simpleDateFormat.format(time * 1000L)
    fun getDateString(time: Int) : String = simpleDateFormat.format(time * 1000L)

    fun getMinuteValue(dateTimeString: String): String {
        Log.i("DateFormatter", dateTimeString)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val zonedDateTime = ZonedDateTime.parse(dateTimeString)
            val formatter = DateTimeFormatter.ofPattern("HH:mm")
            val formattedTime = zonedDateTime.format(formatter)
            return formattedTime
        } else {
            return "1"
        }
    }
}