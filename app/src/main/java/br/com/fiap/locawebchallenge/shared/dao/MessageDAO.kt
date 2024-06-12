package br.com.fiap.locawebchallenge.shared.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.fiap.locawebchallenge.shared.models.Message
import br.com.fiap.locawebchallenge.shared.models.User

@Dao
interface MessageDAO {
    @Insert
    fun sendMessage(message: Message)

    @Query("UPDATE tbl_message SET status = 'DELETED' WHERE id=:id")
    fun deleteMessage(id: Int)

    @Delete
    fun deleteMessageForce(message: Message)

    @Query("UPDATE tbl_message SET wasRead = 1 WHERE id=:id")
    fun visualize(id: Int): Unit

    @Query("SELECT * FROM tbl_message WHERE id = :id")
    fun getMessage(id: Int): Message

    @Query("UPDATE tbl_message SET status = 'SPAM' WHERE id=:id")
    fun setSpam(id: Int)

    @Query("SELECT * FROM tbl_message WHERE recipient = :recipient AND status = 'MAIL'")
    fun getMessagesReceived(recipient: String): Array<Message>

    @Query("SELECT * FROM tbl_message WHERE sender = :sender AND status != 'DELETED'")
    fun getMessagesSent(sender: String): Array<Message>

    @Query("SELECT * FROM tbl_message WHERE recipient = :recipient AND status = 'SPAM'")
    fun getMessagesSpam(recipient: String): Array<Message>

    @Query("SELECT * FROM tbl_message WHERE recipient = :recipient AND status = 'DELETED'")
    fun getMessagesDeleted(recipient: String): Array<Message>
}