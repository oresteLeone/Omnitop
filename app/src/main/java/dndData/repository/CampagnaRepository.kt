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

    suspend fun updateCampagna(Campagna: Campagna){
        CampagnaDAO.updateCampagna(Campagna)
    }

    suspend fun deleteCampagna(Campagna: Campagna){
        CampagnaDAO.deleteCampagna(Campagna)
    }

}