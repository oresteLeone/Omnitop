package dndData.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import dndData.entities.Notes

@Dao
interface NotesDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNota(Nota: Notes)

    @Query("SELECT * FROM note_table ORDER BY id DESC")
    fun readAllData(): LiveData<List<Notes>>

    @Query("SELECT * FROM note_table WHERE preferito ORDER BY id DESC")
    fun readFavoriteData(): LiveData<List<Notes>>

    @Query("SELECT * FROM note_table WHERE id= :id ORDER BY id DESC")
    suspend fun getNotesFromID(id: Int): Notes

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNota(Nota: Notes)

    @Delete
    suspend fun deleteNota(Nota: Notes)
}