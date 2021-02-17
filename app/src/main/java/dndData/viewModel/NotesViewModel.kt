package dndData.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import dndData.database.DNDdatabase
import dndData.entities.Notes
import dndData.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Notes>>
    val readFavoriteData: LiveData<List<Notes>>
    var notaFound: Notes? = null
    var notaFromID: Int? = null
        set(value) {
            field = value
            notaFound=repository.getNotesFromID(field)
        }

    private val repository: NotesRepository

    init {
        val NotesDAO = DNDdatabase.getDatabase(application).getNotesDAO()
        repository = NotesRepository(NotesDAO)
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



}