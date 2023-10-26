package com.example.challenge4.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.challenge4.data.datasource.local.UserDbDataSource
import com.example.challenge4.data.datasource.local.room.dao.UserDao
import com.example.challenge4.domain.model.User
import com.example.challenge4.domain.repository.AuthRepository
import com.example.challenge4.presentation.utils.AppExecutors
import com.example.challenge4.data.mapper.DataMapper

class AuthRepositoryImpl private constructor(
    private val userDbDataSource: UserDbDataSource,
    private val appExecutors: AppExecutors
) : AuthRepository {

    override fun insertUser(user: User) {
        appExecutors.diskIO.execute { userDbDataSource.insertUser(DataMapper.userDomainToUserEntity(user)) }
    }

    override fun updateUser(user: User) {
        appExecutors.diskIO.execute { userDbDataSource.updateUser(DataMapper.userDomainToUserEntity(user)) }
    }

    override fun deleteUser(user: User) {
        appExecutors.diskIO.execute { userDbDataSource.deleteUser(DataMapper.userDomainToUserEntity(user)) }
    }

    override fun getUserById(userId: Int): LiveData<User> {
        return userDbDataSource.getUserById(userId).map { DataMapper.userEntityToUserDomain(it) }
    }

    override fun getUserByEmailAndPassword(
        email: String,
        password: String
    ): LiveData<User?> {
        return userDbDataSource.getUserByEmailAndPassword(email, password)
            .map { DataMapper.userLoginEntityToUserDomain(it) }
    }


    companion object {
        @Volatile
        private var instance: AuthRepositoryImpl? = null

        fun getInstance(
            userDbDataSource: UserDbDataSource,
            appExecutors: AppExecutors
        ): AuthRepositoryImpl =
            instance ?: synchronized(this) {
                instance ?: AuthRepositoryImpl(userDbDataSource, appExecutors)
            }
    }

}