package dndData.repository

import androidx.lifecycle.LiveData
import dndData.dao.NotesDAO
import dndData.entities.Notes

class NotesRepository(private val NotesDAO: NotesDAO, id: Int? = null){

    val readAllData: LiveData<List<Notes>> = NotesDAO.readAllData()
    val readFavoriteData: LiveData<List<Notes>> = NotesDAO.readFavoriteData()

    fun getNotesFromID(id: Int?): Notes? = NotesDAO.getNotesFromID(id = null)

    suspend fun addNota(Nota: Notes){
        NotesDAO.addNota(Nota)
    }

    suspend fun updateNota(Nota: Notes){
        NotesDAO.updateNota(Nota)
    }

    suspend fun deleteNota(Nota: Notes){
        NotesDAO.deleteNota(Nota)
    }


}
