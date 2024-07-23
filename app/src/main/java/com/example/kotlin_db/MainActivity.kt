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
    lateinit var database: ContactDatabase
    lateinit var mainViewModel: MainViewModel
    lateinit var txtcount: TextView
    lateinit var binding: ActivityMainBinding

    private val factsTextView :TextView
        get() =findViewById(R.id.factview)
    private val btnupdate :TextView
        get() =findViewById(R.id.btnupdate)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

/*
        val t1=findViewById<TextView>(R.id.text1)
        val t2=findViewById<TextView>(R.id.text2)
        t1.text="text1 is showing"
        t2.text="text2   is showing"
*/

        binding.text1.text="text1 is showing"
        binding.text2.text="text2   is showing"

        val quoteObj = Quote("Jashwant","Rahul")
        binding.quote =quoteObj

        lifecycle.addObserver(Observer())
        Log.d("jashwant","Mainactivity oncreate")

        //mainViewModel = ViewModelProvider(this,MainViewModelFactory(20)).get(MainViewModel::class.java)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mainViewModel.factsLiveData.observe(this,Observer{
            factsTextView.text =it
        })
        btnupdate.setOnClickListener{
            mainViewModel.updateLiveData()
        }

        txtcount=findViewById(R.id.counter)
        /*setText()*/

        database = Room.databaseBuilder(applicationContext,ContactDatabase::class.java,"contactDB").build()

        GlobalScope.launch {
            database.contactDao().insertcontact(Contact(0,"Jashwant","9999"))
        }


    }/*
    fun setText(){
        txtcount.text = mainViewModel.count.toString()
    }
    fun increment(view: View){
        mainViewModel.increment();
        setText()
    }*/
    fun getData(view :View){
        database.contactDao().getContact().observe(this, Observer {
            Log.d("jashwant",it.toString())
        })
    }
}