package dndData.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import dndData.entities.Mappa

@Dao
interface MappaDAO {

    @Query("SELECT * FROM mappa_table ORDER BY id DESC")
    fun readAllData(): LiveData<List<Mappa>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMappa(Mappa: Mappa)

    @Update
    suspend fun updateMappa(Mappa: Mappa)

    @Delete
    suspend fun deleteMappa(Mappa: Mappa)
}