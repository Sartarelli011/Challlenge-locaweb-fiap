package br.com.fiap.locawebchallenge.shared.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.fiap.locawebchallenge.shared.models.User

@Dao
interface UserDAO {
    @Insert
    fun createUser(user: User)

    @Query("SELECT * FROM tbl_user")
    fun getAllUsers(): Array<User>

    @Query("SELECT * FROM tbl_user WHERE email = :email AND password = :password")
    fun getUser(email: String, password: String): User

    @Query("SELECT * FROM tbl_user WHERE id = :id")
    fun getUserById(id: Int): User
}