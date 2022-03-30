//package com.revature.contentprovider.model.dao
//
//import androidx.lifecycle.LiveData
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import com.revature.contentprovider.model.datamodels.User
//
//@Dao
//interface UserDAO {
//
//    @Query("SELECT * FROM User")
//    fun fetchAllUsers(): LiveData<List<User>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertUser(user: User)
//
//    @Query("DELETE FROM User WHERE id =:id")
//    suspend fun deleteUserById(id: Int)
//
//    @Query("SELECT FROM User WHERE id =:id")
//    suspend fun selectUserById(id: Int)
//
//}