package br.com.fiap.locawebchallenge.shared.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.fiap.locawebchallenge.shared.dao.MessageDAO
import br.com.fiap.locawebchallenge.shared.dao.UserDAO
import br.com.fiap.locawebchallenge.shared.models.Message
import br.com.fiap.locawebchallenge.shared.models.User

@Database(entities = [Message::class, User::class], version = 6)
abstract class SQLiteDb : RoomDatabase() {
    abstract fun messageDAO(): MessageDAO
    abstract fun userDAO(): UserDAO

    companion object {
        private lateinit var instance : SQLiteDb

        fun getDatabase(context: Context): SQLiteDb {
            if(!::instance.isInitialized){
                instance = Room.databaseBuilder(
                    context,
                    SQLiteDb::class.java,
                    "locaweb_db"
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
            }
            return instance
        }
    }
}