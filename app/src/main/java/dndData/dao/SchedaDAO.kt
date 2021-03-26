package dndData.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import dndData.entities.Scheda

@Dao
interface SchedaDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addScheda(Scheda: Scheda)

    @Query("SELECT * FROM scheda_table ORDER BY id DESC")
    fun readAllData(): LiveData<List<Scheda>>

    @Query("SELECT * FROM scheda_table WHERE id= :id ORDER BY id DESC")
    fun getSchedaFromID(id: Int): Scheda

    //@Query("SELECT * FROM schedaCompleta_table WHERE id= :id ORDER BY id DESC")
    //fun getSchedaCompletaFromID(id: Int): SchedaCompletaEntity

    @Update
    suspend fun updateScheda(Scheda: Scheda)

    @Delete
    suspend fun deleteScheda(Scheda: Scheda)


}