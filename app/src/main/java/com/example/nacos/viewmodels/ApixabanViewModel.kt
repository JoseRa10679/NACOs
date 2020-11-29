package com.example.nacos.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ApixabanViewModel: ViewModel() {

    private val _miresultadoA: MutableLiveData<String>? by lazy { MutableLiveData<String>() }
    fun getResultadoA() = _miresultadoA as LiveData<String>
    fun setResultadoA(s: String){
        _miresultadoA?.value = s
    }

}