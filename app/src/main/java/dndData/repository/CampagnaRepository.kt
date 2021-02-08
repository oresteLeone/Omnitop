package dndData.repository

import androidx.lifecycle.LiveData
import androidx.room.Update
import dndData.dao.CampagnaDAO
import dndData.entities.*

class CampagnaRepository(private val CampagnaDAO: CampagnaDAO) {

    val readAllData: LiveData<List<Campagna>> = CampagnaDAO.readAllData()
    val readGMData: LiveData<List<Campagna>> = CampagnaDAO.readGMData()
    val readPGData: LiveData<List<Campagna>> = CampagnaDAO.readPGData()

    suspend fun addCampagna(Campagna: Campagna){
        CampagnaDAO.addCampagna(Campagna)
    }



    /*suspend fun addScheda(Scheda: Scheda){
        CampagnaDAO.addScheda(Scheda)
    }

    suspend fun addMappa(Mappa: Mappa){
        CampagnaDAO.addMappa(Mappa)
    }

    suspend fun addPedina(Pedina: Pedina){
        CampagnaDAO.addPedina(Pedina)
    }*/


    suspend fun updateCampagna(Campagna: Campagna){
        CampagnaDAO.updateCampagna(Campagna)
    }



    /*suspend fun updateScheda(Scheda: Scheda){
        CampagnaDAO.updateScheda(Scheda)
    }

    suspend fun updateMappa(Mappa: Mappa){
        CampagnaDAO.updateMappa(Mappa)
    }

    suspend fun updatePedina(Pedina: Pedina){
        CampagnaDAO.updatePedina(Pedina)
    }*/

    suspend fun deleteCampagna(Campagna: Campagna){
        CampagnaDAO.deleteCampagna(Campagna)
    }



    /*suspend fun deleteScheda(Scheda: Scheda){
        CampagnaDAO.deleteScheda(Scheda)
    }

    suspend fun deleteMappa(Mappa: Mappa){
        CampagnaDAO.deleteMappa(Mappa)
    }

    suspend fun deletePedina(Pedina: Pedina){
        CampagnaDAO.deletePedina(Pedina)
    }*/

}