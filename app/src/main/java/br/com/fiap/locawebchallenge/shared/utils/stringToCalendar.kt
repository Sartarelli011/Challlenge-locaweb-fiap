package br.com.fiap.locawebchallenge.shared.utils

import java.text.SimpleDateFormat
import java.util.*

fun stringToCalendar(dateString: String): Calendar? {
    val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val calendar = Calendar.getInstance()
    try {
        val date = format.parse(dateString)
        date?.let {
            calendar.time = it
            return calendar
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}
