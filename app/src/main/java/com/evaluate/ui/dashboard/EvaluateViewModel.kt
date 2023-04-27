package com.evaluate.ui.dashboard

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class EvaluateViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text


    val symptomListLiveData1  = MutableLiveData<List<String>>().apply {
        value = arrayListOf("a","b","c","d")
    }

    val symptomListLiveData2  = MutableLiveData<List<String>>().apply {
        value = arrayListOf("a","b","c","d")
    }

    val symptom1:LiveData<List<String>> = symptomListLiveData1
    val symptom2:LiveData<List<String>> = symptomListLiveData2


}