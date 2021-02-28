package dndData.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dndData.database.DNDdatabase
import dndData.entities.*
import dndData.relationData.SchedeCampagna
import dndData.repository.CampagnaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CampagnaViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Campagna>>
    val readDMData: LiveData<List<Campagna>>
    val readPGData: LiveData<List<Campagna>>
    /*lateinit var readAllSchedeFromCampagnaID: LiveData<List<Scheda>>*/

    var schedeCampagnaData: MutableLiveData<List<Scheda>> = lazy {
        MutableLiveData<List<Scheda>>()
    }.value

    private val repository: CampagnaRepository
    var singleLiveData: MutableLiveData<Campagna> = lazy {
        MutableLiveData<Campagna>()
    }.value

    init {
        val CampagnaDAO = DNDdatabase.getDatabase(application).getCampagnaDAO()
        repository = CampagnaRepository(CampagnaDAO)
        readAllData = repository.readAllData
        readDMData = repository.readDMData
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

    fun getSingleLiveData(): LiveData<Campagna> = singleLiveData

    fun getListaLiveDataScheda(): LiveData<List<Scheda>> = schedeCampagnaData

    fun getCampagnaFromID(id:Int){
        viewModelScope.launch(Dispatchers.IO){
            val item = repository.getCampagnaFromID(id)
            singleLiveData.postValue(item)
            var list = repository.readAllSchedeFromCampagnaID(id)
            schedeCampagnaData.postValue(list)
            /*readAllSchedeFromCampagnaID = repository.readAllSchedeFromCampagnaID(id)*/

        }
    }

}