package dndData.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import dndData.database.DNDdatabase
import dndData.entities.Pedina
import dndData.repository.PedinaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PedinaViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Pedina>>
    private val repository: PedinaRepository

    init {
        val PedinaDAO = DNDdatabase.getDatabase(application).getPedinaDAO()
        repository = PedinaRepository(PedinaDAO)
        readAllData = repository.readAllData
    }

    fun addPedina(Pedina: Pedina){
        viewModelScope.launch(Dispatchers.IO){
            repository.addPedina(Pedina)
        }
    }

    fun updatePedina(Pedina: Pedina){
        viewModelScope.launch(Dispatchers.IO){
            repository.updatePedina(Pedina)
        }
    }

    fun deletePedina(Pedina: Pedina){
        viewModelScope.launch(Dispatchers.IO){
            repository.deletePedina(Pedina)
        }
    }
}