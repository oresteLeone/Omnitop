package dndData.repository

import androidx.lifecycle.LiveData
import dndData.dao.CampagnaDAO
import dndData.entities.Campagna

class CampagnaRepository(private val CampagnaDAO: CampagnaDAO) {

    val readAllData: LiveData<List<Campagna>> = CampagnaDAO.readAllData()
    val readGMData: LiveData<List<Campagna>> = CampagnaDAO.readGMData()

    suspend fun addCampagna(Campagna: Campagna){
        CampagnaDAO.addCampagna(Campagna)
    }

}