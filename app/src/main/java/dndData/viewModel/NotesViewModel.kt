package dndData.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dndData.database.DNDdatabase
import dndData.entities.Notes
import dndData.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class NotesViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Notes>>
    val readFavoriteData: LiveData<List<Notes>>



    var singleLiveData: MutableLiveData<Notes> = lazy {
        MutableLiveData<Notes>()
    }.value

    private val repository: NotesRepository

    init {
        val notesDAO = DNDdatabase.getDatabase(application).getNotesDAO()
        repository = NotesRepository(notesDAO)
        readAllData = repository.readAllData
        readFavoriteData = repository.readFavoriteData

    }

    fun addNota(Nota: Notes){
        viewModelScope.launch(Dispatchers.IO){
            repository.addNota(Nota)
        }
    }

    fun updateNota(Nota: Notes){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateNota(Nota)
        }
    }

    fun deleteNota(Nota: Notes){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteNota(Nota)
        }
    }


    fun getSingleLiveData(): LiveData<Notes> = singleLiveData


    fun getNotesFromID(id:Int){
        viewModelScope.launch(Dispatchers.IO){
            val item = repository.getNotesFromID(id)
            singleLiveData.postValue(item)
        }
    }
}