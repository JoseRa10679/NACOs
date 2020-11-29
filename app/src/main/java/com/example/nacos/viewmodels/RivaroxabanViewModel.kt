package com.example.nacos.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RivaroxabanViewModel: ViewModel() {

    private val _miResultadoR: MutableLiveData<String>? by lazy { MutableLiveData<String>() }
    fun getResultado() = _miResultadoR as LiveData<String>
    fun setResultado(s: String){
        _miResultadoR?.value = s
    }
}