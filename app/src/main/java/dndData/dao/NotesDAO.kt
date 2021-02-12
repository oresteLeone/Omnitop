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

    /*
    @Query("SELECT * FROM note_table WHERE campagna_id= :Campagna_id ORDER BY id DESC")
    fun getNotesFromCampagna(Campagna_id: Int): LiveData<List<Notes>>
    forse non si può fare qui
    */

    @Update
    suspend fun updateNota(Nota: Notes)

    @Delete
    suspend fun deleteNota(Nota: Notes)
}