package com.example.challenge4.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.challenge4.data.datasource.local.room.dao.UserDao
import com.example.challenge4.domain.model.User
import com.example.challenge4.domain.repository.AuthRepository
import com.example.challenge4.presentation.utils.AppExecutors
import com.example.challenge4.data.mapper.DataMapper

class AuthRepositoryImpl private constructor(
    private val userDao: UserDao,
    private val appExecutors: AppExecutors
) : AuthRepository {

    override fun insertUser(user: User) {
        appExecutors.diskIO.execute { userDao.insertUser(DataMapper.userDomainToUserEntity(user)) }
    }

    override fun updateUser(user: User) {
        appExecutors.diskIO.execute { userDao.updateUser(DataMapper.userDomainToUserEntity(user)) }
    }

    override fun deleteUser(user: User) {
        appExecutors.diskIO.execute { userDao.deleteUser(DataMapper.userDomainToUserEntity(user)) }
    }

    override fun getUserById(userId: Int): LiveData<User> {
        return userDao.getUserById(userId).map { DataMapper.userEntityToUserDomain(it) }
    }

    override fun getUserByEmailAndPassword(
        email: String,
        password: String
    ): LiveData<User?> {
        return userDao.getUserByEmailAndPassword(email, password)
            .map { DataMapper.userLoginEntityToUserDomain(it) }
    }


    companion object {
        @Volatile
        private var instance: AuthRepositoryImpl? = null

        fun getInstance(
            userDao: UserDao,
            appExecutors: AppExecutors
        ): AuthRepositoryImpl =
            instance ?: synchronized(this) {
                instance ?: AuthRepositoryImpl(userDao, appExecutors)
            }
    }

}