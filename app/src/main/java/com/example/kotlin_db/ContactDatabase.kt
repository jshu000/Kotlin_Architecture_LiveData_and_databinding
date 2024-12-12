package com.example.kotlin_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Contact::class],version =1)
@TypeConverters(Converters::class)
abstract class ContactDatabase : RoomDatabase(){

    abstract  fun contactDao() : ContactDAO


    //Singleton Pattern with Thread Safe
    companion object{
        // Volatile ensure that every time a instance is fetched. It get the latest data from RAM directly instead of caches.
        @Volatile
        private var INSTANCE: ContactDatabase? =null

        fun getdatabase(context: Context):ContactDatabase{
            if(INSTANCE==null){

                //There are chances that this can be executed in multiple threads
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ContactDatabase::class.java,
                        "contactDB")
                        .build()

                }
            }
            return INSTANCE!!
        }
    }

}