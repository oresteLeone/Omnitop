package dndData.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import dndData.entities.*

@Dao
interface CampagnaDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCampagna(Campagna: Campagna)

    @Query("SELECT * FROM Campagna_table ORDER BY id DESC")
    fun readAllData(): LiveData<List<Campagna>>

    @Query("SELECT * FROM Campagna_table WHERE ruoloCampagna = 'DM' ORDER BY id DESC ")
    fun readDMData(): LiveData<List<Campagna>>

    @Query("SELECT * FROM Campagna_table WHERE ruoloCampagna = 'PG' ORDER BY id DESC ")
    fun readPGData(): LiveData<List<Campagna>>

    @Update
    suspend fun updateCampagna(Campagna: Campagna)

    @Delete
    suspend fun deleteCampagna(Campagna: Campagna)

    @Query("SELECT * FROM Campagna_table WHERE id= :id ORDER BY id DESC")
    suspend fun getCampagnaFromID(id: Int): Campagna


}