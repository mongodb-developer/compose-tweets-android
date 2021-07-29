package com.mongodb.compose

import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

data class TweetDetails(
    val authorName: String,
    val handle: String,
    val tweetContent: String,
    val timeStamp: String
)


fun TweetDetails.formatDate(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
    return LocalDateTime.parse(timeStamp, formatter).toLocalDate().toString()
}

fun TweetDetails.formatTime(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
    return LocalDateTime.parse(timeStamp, formatter).toLocalTime().toString()
}