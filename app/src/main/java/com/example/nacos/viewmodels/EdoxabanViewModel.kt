package com.example.nacos.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EdoxabanViewModel: ViewModel() {
    private val _miResultadoE: MutableLiveData<String>? by lazy { MutableLiveData<String>() }
    fun getResultadoE() = _miResultadoE as LiveData<String>
    fun setResulltadoE(s: String){
        _miResultadoE?.value =s
    }

}