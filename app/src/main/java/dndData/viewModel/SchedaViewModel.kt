package dndData.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dndData.database.DNDdatabase
import dndData.entities.Scheda
import dndData.repository.SchedaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SchedaViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Scheda>>
    private val repository: SchedaRepository

    var singleLiveData : MutableLiveData<Scheda> = lazy {
        MutableLiveData<Scheda>()
    }.value


    /*ar singleLiveDataCompleta : MutableLiveData<SchedaCompletaEntity> = lazy {
        MutableLiveData<SchedaCompletaEntity>()
    }.value*/


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


    fun getSingleLiveData(): LiveData<Scheda> = singleLiveData

    //fun getSingleLiveDataCompleta(): LiveData<SchedaCompletaEntity> = singleLiveDataCompleta

    fun getSchedaFromID(id : Int){
        viewModelScope.launch(Dispatchers.IO) {
            val item = repository.getSchedaFromID(id)
            singleLiveData.postValue(item)
        }
    }

    /*fun getSchedaCompletaFromID(id: Int){
        viewModelScope.launch(Dispatchers.IO){
            val item = repository.getSchedaCompletaFromID(id)
            singleLiveDataCompleta.postValue(item)
        }
    }*/


}