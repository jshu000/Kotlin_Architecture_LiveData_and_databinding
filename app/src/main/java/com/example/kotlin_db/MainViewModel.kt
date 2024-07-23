package com.example.kotlin_db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/*class MainViewModel(val initialValue:Int) :ViewModel(){
    var count:Int =initialValue

    fun increment(){
        count++
    }
}*/
class MainViewModel() :ViewModel(){
    private val factsLiveDataObject =MutableLiveData<String>("This is a fact")

    val factsLiveData :LiveData<String>
        get() = factsLiveDataObject
    fun updateLiveData(){
        factsLiveDataObject.value="Another fact"
    }
}