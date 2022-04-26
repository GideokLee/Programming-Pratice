package com.ssafy.jetpack_02.live_data4

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val elapsedTime = TimerLiveData()

    fun getElapsedTime() = elapsedTime

}
