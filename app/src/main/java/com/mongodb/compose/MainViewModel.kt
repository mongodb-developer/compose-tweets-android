package com.mongodb.compose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _lastTweet = MutableLiveData<TweetDetails>()
    val lastTweet: LiveData<TweetDetails> = _lastTweet

    init {
        loadLastTweet()
    }


    private fun loadLastTweet() {
        _lastTweet.value = TweetDetails(
            authorName = "Mohit Sharma",
            handle = "@codeWithMohit",
            tweetContent = "The production-ready version of compose is here.\n" +
                    "#JetpackCompose",
            timeStamp = "2020-06-24T16:28:14.000Z"
        )
    }
}