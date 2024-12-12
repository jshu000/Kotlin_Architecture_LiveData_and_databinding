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
    val quoteLiveDataObject =MutableLiveData("This is a fact")


    fun updateLiveData(){
        quoteLiveDataObject.value="Another fact"
    }
}