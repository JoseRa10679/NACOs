package com.example.nacos.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DabigatranViewModel: ViewModel() {

    private val _miresultadoD: MutableLiveData<String>? by lazy { MutableLiveData<String>()}

    fun getResultadoD() = _miresultadoD as LiveData<String>
    fun setResultadoD(s: String){
        _miresultadoD?.value = s
    }

    private val _comentarios: MutableLiveData<String> by lazy { MutableLiveData<String>()}
    fun getComentarios() = _comentarios as LiveData<String>
    fun setComentarios(s: String){
        _comentarios.value = s
    }

}