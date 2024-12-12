package com.example.kotlin_db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDAO {

    @Insert
    suspend fun insertcontact(contact:Contact)

    @Update
    suspend fun updatecontact(contact:Contact)

    @Delete
    suspend fun deletecontact(contact:Contact)

    @Query("SELECT * FROM contact")
    fun getContacts() : LiveData<List<Contact>>

}