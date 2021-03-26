package dndData.repository

import androidx.lifecycle.LiveData
import dndData.dao.PedinaDAO
import dndData.entities.Pedina

class PedinaRepository(private val PedinaDAO: PedinaDAO) {

    val readAllData: LiveData<List<Pedina>> = PedinaDAO.readAllData()

    suspend fun addPedina(Pedina: Pedina){
        PedinaDAO.addPedina(Pedina)
    }

    suspend fun updatePedina(Pedina: Pedina){
        PedinaDAO.updatePedina(Pedina)
    }
    suspend fun deletePedina(Pedina: Pedina){
        PedinaDAO.deletePedina(Pedina)
    }

}