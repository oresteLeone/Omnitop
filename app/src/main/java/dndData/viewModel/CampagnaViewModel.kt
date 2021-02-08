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

   /* fun addScheda(Scheda: Scheda){
        viewModelScope.launch(Dispatchers.IO){
            repository.addScheda(Scheda)
        }
    }



    fun addMappa(Mappa: Mappa){
        viewModelScope.launch(Dispatchers.IO){
            repository.addMappa(Mappa)
        }
    }

    fun addPedina(Pedina: Pedina){
        viewModelScope.launch(Dispatchers.IO){
            repository.addPedina(Pedina)
        }
    }*/

    fun updateCampagna(Campagna: Campagna){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateCampagna(Campagna)
        }
    }

   /* fun updateScheda(Scheda: Scheda){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateScheda(Scheda)
        }
    }



    fun updateMappa(Mappa: Mappa){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateMappa(Mappa)
        }
    }

    fun updatePedina(Pedina: Pedina){
        viewModelScope.launch(Dispatchers.IO){
            repository.updatePedina(Pedina)
        }
    }*/

    fun deleteCampagna(Campagna: Campagna){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteCampagna(Campagna)
        }
    }

    /*fun deleteScheda(Scheda: Scheda){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteScheda(Scheda)
        }
    }



    fun deleteMappa(Mappa: Mappa){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteMappa(Mappa)
        }
    }

    fun deletePedina(Pedina: Pedina){
        viewModelScope.launch(Dispatchers.IO){
            repository.deletePedina(Pedina)
        }
    }
*/
}