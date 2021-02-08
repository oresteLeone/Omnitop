package dndData.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import dndData.database.DNDdatabase
import dndData.entities.Mappa
import dndData.repository.MappaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MappaViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Mappa>>
    private val repository: MappaRepository

    init {
        val MappaDAO = DNDdatabase.getDatabase(application).getMappaDAO()
        repository = MappaRepository(MappaDAO)
        readAllData = repository.readAllData

    }

    fun addMappa(Mappa: Mappa){
        viewModelScope.launch(Dispatchers.IO){
            repository.addMappa(Mappa)
        }
    }

    fun updateMappa(Mappa: Mappa){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateMappa(Mappa)
        }
    }

    fun deleteMappa(Mappa: Mappa){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteMappa(Mappa)
        }
    }
}