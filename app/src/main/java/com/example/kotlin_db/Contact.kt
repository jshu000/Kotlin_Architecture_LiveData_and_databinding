package com.example.kotlin_db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "contact")
data class Contact(

    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val name: String,
    val phone: String,
    val createdDate:Date,    //we cant able to save date object directly so we had converted it to long
    val isActive:Int
)
