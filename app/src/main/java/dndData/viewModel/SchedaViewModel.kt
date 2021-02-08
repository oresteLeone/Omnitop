package dndData.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import dndData.database.DNDdatabase
import dndData.entities.Scheda
import dndData.repository.SchedaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SchedaViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Scheda>>
    private val repository: SchedaRepository

    init {
        val SchedaDAO = DNDdatabase.getDatabase(application).getSchedaDAO()
        repository = SchedaRepository(SchedaDAO)
        readAllData = repository.readAllData

    }

    fun addScheda(Scheda: Scheda){
        viewModelScope.launch(Dispatchers.IO){
            repository.addScheda(Scheda)
        }
    }

    fun updateScheda(Scheda: Scheda){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateScheda(Scheda)
        }
    }

    fun deleteScheda(Scheda: Scheda){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteScheda(Scheda)
        }
    }


}