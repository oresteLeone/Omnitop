package dndData.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dndData.entities.Campagna

@Dao
interface CampagnaDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCampagna(Campagna: Campagna)

    @Query("SELECT * FROM Campagna_table ORDER BY id DESC")
    fun readAllData(): LiveData<List<Campagna>>
}