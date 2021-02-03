package dndData.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import dndData.database.DNDdatabase
import dndData.entities.Campagna
import dndData.repository.CampagnaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CampagnaViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<Campagna>>
    private val repository: CampagnaRepository

    init {
        val CampagnaDAO = DNDdatabase.getDatabase(application).getCampagnaDAO()
        repository = CampagnaRepository(CampagnaDAO)
        readAllData = repository.readAllData
    }

    fun addCampagna(Campagna: Campagna){
        viewModelScope.launch(Dispatchers.IO){
            repository.addCampagna(Campagna)
        }
    }
}