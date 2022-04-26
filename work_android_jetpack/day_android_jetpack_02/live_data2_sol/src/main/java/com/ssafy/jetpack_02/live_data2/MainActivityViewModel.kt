package com.ssafy.jetpack_02.live_data2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    // 사용자의 클릭수를 세는 변수
    private val _count = MutableLiveData<Int>().apply {
        value = 0
    }

    // 외부에서 접근 가능한 변수 선언
    val count: LiveData<Int>
        get() = _count

    // 사용자가 클릭 했을 때 클릭수를 증가시키는 메서드
    fun increaseCount() {
        _count.value = (_count.value ?: 0) + 1
    }

    fun decreaseCount() {
        _count.value = (_count.value ?: 0) - 1
    }

    // Transformation을 사용하면 LiveData가 변경되는 것에 따라 대응가능함
    val timesText = Transformations.map(count) {
        "$it x 2 = ${it * 2}"
    }
}