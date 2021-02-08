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

    @Query("SELECT * FROM Campagna_table WHERE ruoloCampagna = 'GM' ORDER BY id DESC ")
    fun readGMData(): LiveData<List<Campagna>>

    @Query("SELECT * FROM Campagna_table WHERE ruoloCampagna = 'PG' ORDER BY id DESC ")
    fun readPGData(): LiveData<List<Campagna>>


   /* @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addScheda(Scheda: Scheda)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMappa(Mappa: Mappa)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPedina(Pedina: Pedina)*/

    @Update
    suspend fun updateCampagna(Campagna: Campagna)


   /* @Update
    suspend fun updateScheda(Scheda: Scheda)

    @Update
    suspend fun updateMappa(Mappa: Mappa)

    @Update
    suspend fun updatePedina(Pedina: Pedina)
*/
    @Delete
    suspend fun deleteCampagna(Campagna: Campagna)


    /*@Delete
    suspend fun deleteScheda(Scheda: Scheda)

    @Delete
    suspend fun deleteMappa(Mappa: Mappa)

    @Delete
    suspend fun deletePedina(Pedina: Pedina)*/



}