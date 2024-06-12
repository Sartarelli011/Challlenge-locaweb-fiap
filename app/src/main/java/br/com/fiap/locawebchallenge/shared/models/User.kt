package br.com.fiap.locawebchallenge.shared.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Index

@Entity(tableName = "tbl_user", indices = [Index(value = ["email"], unique = true)])
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String = "",
    val email: String = "",
    val password: String = ""
)