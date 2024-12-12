package com.example.kotlin_db

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.kotlin_db.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        lifecycle.addObserver(Observer())
        Log.d("jashwant","Mainactivity oncreate")

        //mainViewModel = ViewModelProvider(this,MainViewModelFactory(20)).get(MainViewModel::class.java)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        // android:text="@{mainViewModel.quoteLiveDataObject}"
        /* Below function can be removed by this statement but for livedata we need to pass the owner(this-> mainactivity)
        mainViewModel.quoteLiveDataObject.observe(this,Observer{
            binding.quotetext.text=it
        })
         */

        binding.mainViewModel = mainViewModel

        binding.lifecycleOwner = this

        /* This function is replaced by android:onClick="@{()->mainViewModel.updateLiveData()}" in axtivity_main.xml
        binding.btnupdate.setOnClickListener{
            mainViewModel.updateLiveData()
        }
        */



    }
}