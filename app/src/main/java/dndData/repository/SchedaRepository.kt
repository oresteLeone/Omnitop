package dndData.repository

import androidx.lifecycle.LiveData
import dndData.dao.SchedaDAO
import dndData.entities.Scheda

class SchedaRepository(private val SchedaDAO: SchedaDAO) {

    val readAllData: LiveData<List<Scheda>> = SchedaDAO.readAllData()

    suspend fun addScheda(Scheda: Scheda){
        SchedaDAO.addScheda(Scheda)
    }

    fun getSchedaFromID(id : Int): Scheda {
        return SchedaDAO.getSchedaFromID(id)
    }

    /*fun getSchedaCompletaFromID(id: Int): SchedaCompletaEntity{
        return SchedaDAO.getSchedaCompletaFromID(id)
    }*/

    suspend fun updateScheda(Scheda: Scheda){
        SchedaDAO.updateScheda(Scheda)
    }

    suspend fun deleteScheda(Scheda: Scheda){
        SchedaDAO.deleteScheda(Scheda)
    }


}