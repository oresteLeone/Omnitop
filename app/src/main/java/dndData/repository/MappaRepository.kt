package dndData.repository

import androidx.lifecycle.LiveData
import dndData.dao.MappaDAO
import dndData.entities.Mappa

class MappaRepository(private val MappaDAO: MappaDAO){

    val readAllData: LiveData<List<Mappa>> = MappaDAO.readAllData()

    suspend fun addMappa(Mappa: Mappa){
        MappaDAO.addMappa(Mappa)
    }
    suspend fun updateMappa(Mappa: Mappa){
        MappaDAO.updateMappa(Mappa)
    }
    suspend fun deleteMappa(Mappa: Mappa){
        MappaDAO.deleteMappa(Mappa)
    }

}