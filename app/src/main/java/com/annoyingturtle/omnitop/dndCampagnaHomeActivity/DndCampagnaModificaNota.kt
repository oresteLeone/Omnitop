package com.annoyingturtle.omnitop.dndCampagnaHomeActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.annoyingturtle.omnitop.R
import dndData.RuoloGiocatore
import dndData.entities.Notes
import dndData.viewModel.NotesViewModel
import kotlinx.android.synthetic.main.activity_dnd_campagna_modifica_nota.*
import java.lang.Exception

class DndCampagnaModificaNota : AppCompatActivity() {

    private lateinit var mNotaViewModel : NotesViewModel
    var extras : Bundle? = null
    var idNota: Int = -1
    lateinit var notaToDelete: Notes
    var campagnaid: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dnd_campagna_modifica_nota)

        setSupportActionBar(myToolbarModificaNota)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        /**Visualizzazione a schermo dati attuali*/

        extras = intent?.extras
        idNota = extras!!.getInt("idNota")

        mNotaViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)

        if (idNota > -1) {

            mNotaViewModel.getNotesFromID(idNota)
            showNoteData()
            mNotaViewModel.getSingleLiveData().observe(this, Observer {
                notaToDelete = Notes(idNota, it.Campagnaid,it.titoloNota,it.corpoNota,it.preferito,it.ruoloNota)
                campagnaid = it.Campagnaid
            })
        }

        ButtonInsertDataUpdateNota.setOnClickListener() {
            mNotaViewModel.getSingleLiveData().observe(this, Observer {
                UpdateNoteDataToDatabase(it.ruoloNota)
            })

        }

    }


    fun showNoteData(){
        mNotaViewModel.getSingleLiveData().observe(this, Observer {
            titoloNota.text = Editable.Factory.getInstance().newEditable(it.titoloNota)
            testoNota.text = Editable.Factory.getInstance().newEditable(it.corpoNota)
            checkBoxPreferito.isChecked = it.preferito
        })
    }

    private fun UpdateNoteDataToDatabase(ruoloGiocatore: RuoloGiocatore){
        val titoloNota = titoloNota.text.toString()
        val testoNota = testoNota.text.toString()
        val preferito : Boolean = checkBoxPreferito.isChecked


        if(inputCheck(titoloNota, testoNota)){
            val nota = Notes(idNota, campagnaid, titoloNota, testoNota, preferito, ruoloGiocatore)
            try {
                mNotaViewModel.updateNota(nota)
            }catch (e : Exception)
            {
                Toast.makeText(this, "ERRORE: La nota non è stata aggiornata correttamente", Toast.LENGTH_LONG).show()
                return
            }
            Toast.makeText(this, "La nota è stata aggiornata con successo", Toast.LENGTH_SHORT).show()
            navigateUpTo(Intent(this, DndCampagnaHome::class.java).putExtra("idCampagna", campagnaid ))
        }
        else
            Toast.makeText(this, "Riempi i campi necessari!", Toast.LENGTH_SHORT).show()

    }

    private fun inputCheck(titoloNota: String?, testoNota : String?): Boolean {

        return !(TextUtils.isEmpty(titoloNota) && TextUtils.isEmpty(testoNota))
    }

    fun deleteSingleNota(notaToDelete: Notes){


        val builder = AlertDialog.Builder(this)
        builder.setPositiveButton("Si"){ _, _ ->
            mNotaViewModel.getSingleLiveData().removeObservers(this)
            mNotaViewModel.deleteNota(notaToDelete)
            Toast.makeText(this, "Nota eliminata con successo!", Toast.LENGTH_SHORT).show()
            navigateUpTo(Intent(this, DndCampagnaHome::class.java).putExtra("idCampagna", campagnaid ))

        }
        builder.setNegativeButton("No"){ _, _ ->

        }
        builder.setTitle("Eliminazione Nota ${notaToDelete.titoloNota}")
        builder.setMessage("Sei sicuro di voler cancellare la nota ${notaToDelete.titoloNota}?")
        builder.create().show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        var inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_con_pulsante_cancella, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.cancellaNotaBtn -> deleteSingleNota(notaToDelete)

            else -> onBackPressed()
        }
        return true
    }
}