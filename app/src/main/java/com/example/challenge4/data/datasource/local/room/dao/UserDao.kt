package com.example.challenge4.data.datasource.local.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.challenge4.data.datasource.local.room.entity.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(userEntity: UserEntity)

    @Update
    fun updateUser(userEntity: UserEntity)

    @Delete
    fun deleteUser(userEntity: UserEntity)

    @Query("SELECT * FROM user WHERE id = :userId")
    fun getUserById(userId: Int): LiveData<UserEntity>

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    fun getUserByEmailAndPassword(email: String, password: String): LiveData<UserEntity?>
}