package com.example.kotlin_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [Contact::class],version =2)
@TypeConverters(Converters::class)
abstract class ContactDatabase : RoomDatabase(){

    abstract  fun contactDao() : ContactDAO


    //Singleton Pattern with Thread Safe
    companion object{

        val migration_1_2 = object : Migration(1,2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE contact ADD COLUMN isActive INTEGER NOT NULL DEFAULT(1) ")
            }

        }

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
                        .addMigrations(migration_1_2)
                        .build()

                }
            }
            return INSTANCE!!
        }
    }

}