    package br.com.fiap.locawebchallenge.shared.repository

    import android.content.Context
    import android.util.Log
    import br.com.fiap.locawebchallenge.shared.db.SQLiteDb
    import br.com.fiap.locawebchallenge.shared.models.User

    class UserRepository(context: Context) {
        private val db = SQLiteDb.getDatabase(context).userDAO()

        fun createUser(user: User) {
            db.createUser(user)
        }

        fun getAllUsers(): Array<User> {
            return db.getAllUsers()
        }

        fun getUser(email: String, password: String): User {
            return db.getUser(email, password)
        }

        fun getUserById(id: Int): User {
            return db.getUserById(id)
        }
    }