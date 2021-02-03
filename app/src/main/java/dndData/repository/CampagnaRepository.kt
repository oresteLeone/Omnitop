package dndData.repository

import androidx.lifecycle.LiveData
import dndData.dao.CampagnaDAO
import dndData.entities.Campagna

class CampagnaRepository(private val CampagnaDAO: CampagnaDAO) {

    val readAllData: LiveData<List<Campagna>> = CampagnaDAO.readAllData()

    suspend fun addCampagna(Campagna: Campagna){
        CampagnaDAO.addCampagna(Campagna)
    }

}