package com.example.challenge4.data.datasource.local

import androidx.lifecycle.LiveData
import com.example.challenge4.data.datasource.local.room.dao.UserDao
import com.example.challenge4.data.datasource.local.room.entity.UserEntity

interface UserDataSource {
    fun insertUser(userEntity: UserEntity)

    fun updateUser(userEntity: UserEntity)

    fun deleteUser(userEntity: UserEntity)

    fun getUserById(userId: Int): LiveData<UserEntity>

    fun getUserByEmailAndPassword(email: String, password: String): LiveData<UserEntity?>
}

class UserDbDataSource(private val userDao: UserDao) : UserDataSource {

    companion object {
        private var instance: UserDbDataSource? = null

        fun getInstance(userDao: UserDao): UserDbDataSource =
            instance ?: synchronized(this) {
                instance ?: UserDbDataSource(userDao)
            }
    }

    override fun insertUser(userEntity: UserEntity) {
        userDao.insertUser(userEntity)
    }

    override fun updateUser(userEntity: UserEntity) {
        userDao.updateUser(userEntity)
    }

    override fun deleteUser(userEntity: UserEntity) {
        userDao.deleteUser(userEntity)
    }

    override fun getUserById(userId: Int): LiveData<UserEntity> {
        return userDao.getUserById(userId)
    }

    override fun getUserByEmailAndPassword(email: String, password: String): LiveData<UserEntity?> {
        return userDao.getUserByEmailAndPassword(email, password)
    }

}