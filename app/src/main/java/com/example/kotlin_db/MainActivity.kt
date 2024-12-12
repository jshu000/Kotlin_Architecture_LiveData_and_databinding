package com.example.kotlin_db

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var database: ContactDatabase
    lateinit var mainViewModel: MainViewModel
    lateinit var txtcount: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycle.addObserver(Observer())
        Log.d("jashwant","Mainactivity oncreate")

        mainViewModel = ViewModelProvider(this,MainViewModelFactory(20)).get(MainViewModel::class.java)
        txtcount=findViewById(R.id.counter)
        setText()

        database = ContactDatabase.getdatabase(this)

        GlobalScope.launch {
            database.contactDao().insertcontact(Contact(0,"Jashwant","9999"))
        }


    }
    fun setText(){
        txtcount.text = mainViewModel.count.toString()
    }
    fun increment(view: View){
        mainViewModel.increment();
        setText()
    }
    fun getData(view :View){
        database.contactDao().getContacts().observe(this, Observer {
            Log.d("jashwant",it.toString())
        })
    }
}