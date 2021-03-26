package dndData.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import dndData.entities.Pedina

@Dao
interface PedinaDAO {

    @Query("SELECT * FROM pedina_table ORDER BY id DESC")
    fun readAllData(): LiveData<List<Pedina>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPedina(Pedina: Pedina)

    @Update
    suspend fun updatePedina(Pedina: Pedina)

    @Delete
    suspend fun deletePedina(Pedina: Pedina)

}