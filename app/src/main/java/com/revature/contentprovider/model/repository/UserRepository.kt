//package com.revature.contentprovider.model.repository
//
//import android.app.Application
//import androidx.lifecycle.LiveData
//import com.revature.contentprovider.model.dao.UserDAO
//import com.revature.contentprovider.model.datamodels.User
//import com.revature.contentprovider.model.datamodels.UserDatabase
//
//class UserRepository(application: Application) {
//
//    private var userDAO: UserDAO
//
//    init {
//        val database = UserDatabase.getDatabase(application)
//        userDAO = database.userDAO()
//    }
//    val readAllUsers: LiveData<List<User>> = userDAO.fetchAllUsers()
//
//    suspend fun deleteUserById(id: Int) {
//        userDAO.deleteUserById(id)
//    }
//    suspend fun insertUser(user: User) {
//        userDAO.insertUser(user)
//    }
//    suspend fun selectUserById(id: Int) {
//        userDAO.selectUserById(id)
//    }
//}