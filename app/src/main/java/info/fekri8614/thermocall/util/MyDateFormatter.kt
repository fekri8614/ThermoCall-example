package info.fekri8614.thermocall.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class MyDateFormatter {
    private val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy, HH:mm:ss", Locale.getDefault())

    fun getDateString(time: Long): String? = simpleDateFormat.format(time * 1000L)
    fun getDateString(time: Int) : String = simpleDateFormat.format(time * 1000L)

    fun getMinuteValue(dateTimeString: String): Int {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val dateTime = dateTimeString.substring(0, 19) + "Z"
            val formatter = DateTimeFormatter.ISO_DATE_TIME
            val zdt = ZonedDateTime.parse(dateTime, formatter)
            return zdt.minute
        } else {
            return 1
        }
    }
}