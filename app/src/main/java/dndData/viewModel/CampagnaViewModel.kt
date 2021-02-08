package dndData.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import dndData.database.DNDdatabase
import dndData.entities.*
import dndData.repository.CampagnaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CampagnaViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Campagna>>
    val readGMData: LiveData<List<Campagna>>
    val readPGData: LiveData<List<Campagna>>
    private val repository: CampagnaRepository

    init {
        val CampagnaDAO = DNDdatabase.getDatabase(application).getCampagnaDAO()
        repository = CampagnaRepository(CampagnaDAO)
        readAllData = repository.readAllData
        readGMData = repository.readGMData
        readPGData = repository.readPGData
    }

    fun addCampagna(Campagna: Campagna){
        viewModelScope.launch(Dispatchers.IO){
            repository.addCampagna(Campagna)
        }
    }


    fun updateCampagna(Campagna: Campagna){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateCampagna(Campagna)
        }
    }

    fun deleteCampagna(Campagna: Campagna){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteCampagna(Campagna)
        }
    }

}