package com.annoyingturtle.omnitop.dndCampagnaHomeActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.annoyingturtle.omnitop.R
import dndData.RuoloGiocatore
import dndData.entities.Notes
import dndData.viewModel.CampagnaViewModel
import dndData.viewModel.NotesViewModel
import kotlinx.android.synthetic.main.activity_dnd_campagna_home_nuova_nota.*
import kotlinx.android.synthetic.main.activity_dnd_campagna_home_nuova_nota.checkBoxPreferito
import kotlinx.android.synthetic.main.activity_dnd_campagna_home_nuova_nota.testoNota_et
import kotlinx.android.synthetic.main.activity_dnd_campagna_home_nuova_nota.titoloNota_et
import kotlinx.android.synthetic.main.activity_dnd_campagna_nuova_scheda.*
import java.lang.Exception

class DndCampagnaHomeNuovaNota : AppCompatActivity() {

    var idCampagna: Int = -1
    lateinit var mNoteViewModel: NotesViewModel
    lateinit var mCampagnaViewModel: CampagnaViewModel
    var extras: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dnd_campagna_home_nuova_nota)

        /** Action bar */
        setSupportActionBar(myToolbarNuovaScheda)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        extras = intent.extras
        idCampagna = extras!!.getInt("idCampagna")

        mNoteViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
        mCampagnaViewModel = ViewModelProvider(this).get(CampagnaViewModel::class.java)
        mCampagnaViewModel.getCampagnaFromID(idCampagna)
        ButtonInsertDataAddNoteToCampagna.setOnClickListener(){
            mCampagnaViewModel.getSingleLiveData().observe(this, Observer {
                insertNoteDataToDatabase(it.ruoloCampagna)
            })

        }
    }

    private fun insertNoteDataToDatabase(ruoloGiocatore: RuoloGiocatore) {

        val titoloNota = titoloNota_et.text.toString()
        val testoNota = testoNota_et.text.toString()
        val preferito : Boolean = checkBoxPreferito.isChecked

        if(inputCheck(titoloNota)){
            val nota = Notes(0, idCampagna, titoloNota, testoNota, preferito, ruoloGiocatore )
            try {
                mNoteViewModel.addNota(nota)
            }catch (e : Exception)
            {
                Toast.makeText(this, "ERRORE: La nota non è stata salvata correttamente", Toast.LENGTH_LONG).show()
                return
            }
            Toast.makeText(this, "La nota è stata salvata con successo", Toast.LENGTH_SHORT).show()
            navigateUpTo(Intent(this, DndCampagnaHome::class.java).putExtra("idCampagna", idCampagna ))
        }
        else
            Toast.makeText(this, "Riempi i campi necessari!", Toast.LENGTH_SHORT).show()

    }

    private fun inputCheck(titoloNota: String?): Boolean {

        return !(TextUtils.isEmpty(titoloNota))
    }

    override fun onSupportNavigateUp(): Boolean {

        return navigateUpTo(Intent(this, DndCampagnaHome::class.java).putExtra("idCampagna", idCampagna ))
    }
}