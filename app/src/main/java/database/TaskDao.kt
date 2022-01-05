package database

import androidx.room.*


@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPerson(person: Task)

}