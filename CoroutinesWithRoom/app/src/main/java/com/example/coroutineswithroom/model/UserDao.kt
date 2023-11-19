package com.example.coroutineswithroom.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user:User):Long
    @Query("SELECT * FROM user WHERE userName = :userName")
    fun getUser(userName:String):User
    @Query("DELETE FROM user WHERE id = :id")
    fun deleteUser(id:Long)


}