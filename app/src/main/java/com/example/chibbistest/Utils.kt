package com.example.chibbistest

import android.text.Spannable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


fun String.getRangeSpannableString(startIndex: Int, endIndex: Int, size: Float): SpannableString {
    return SpannableString(this).also { it.setSpan(RelativeSizeSpan(size), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE) }
}

fun parseDateToSimpleFormat(stringDate: String): String {
    val date =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault()).parse(stringDate)
    val dateFormat: DateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.ENGLISH)
    return if (date != null) {
        dateFormat.format(date)
    } else ""

}