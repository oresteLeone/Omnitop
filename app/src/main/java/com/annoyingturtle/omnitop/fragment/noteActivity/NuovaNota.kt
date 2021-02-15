package com.annoyingturtle.omnitop.fragment.noteActivity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import com.annoyingturtle.omnitop.*
import dndData.RuoloGiocatore
import dndData.entities.Notes
import dndData.viewModel.NotesViewModel
import kotlinx.android.synthetic.main.activity_nuova_nota.*
import java.lang.Exception
import java.sql.SQLException

class NuovaNota() : AppCompatActivity() {



    private lateinit var mNotaViewModel : NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuova_nota)

        /** Action bar */
        setSupportActionBar(myToolbarCreaNota)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        mNotaViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
        ButtonInsertDataAddNote.setOnClickListener(){
            insertNoteDataToDatabase()
        }

    }

    private fun insertNoteDataToDatabase(){
        val titoloNota = titoloNota.text.toString()
        val testoNota = testoNota.text.toString()
        val preferito : Boolean = checkBoxPreferito.isChecked
        val ruoloGiocatore : RuoloGiocatore = if(checkBoxGM.isChecked) RuoloGiocatore.DM
        else
            RuoloGiocatore.PG

        if(imputCheck(titoloNota, testoNota)){
            val nota = Notes(0, titoloNota = titoloNota, corpoNota =  testoNota, preferito = preferito, ruoloNota = ruoloGiocatore, Campagnaid = null)
            try {
                mNotaViewModel.addNota(nota)
            }catch (e : Exception)
            {
                Toast.makeText(this, "ERRORE: La nota non è stata salvata correttamente", Toast.LENGTH_LONG).show()
                return
            }
            Toast.makeText(this, "La nota è stata salvata con successo", Toast.LENGTH_SHORT).show()
        }
        else
            Toast.makeText(this, "Riempi i campi necessari!", Toast.LENGTH_SHORT).show()

    }

    private fun imputCheck(titoloNota: String?, testoNota : String?): Boolean {

        return !(TextUtils.isEmpty(titoloNota) && TextUtils.isEmpty(testoNota))
    }

    override fun getSupportParentActivityIntent(): Intent? {
        super.getSupportParentActivityIntent()
        return parentMetod()
    }

    override fun getParentActivityIntent(): Intent? {
        super.getParentActivityIntent()
        return parentMetod()
    }

    private fun parentMetod(): Intent {
        var intento = Intent()
        var extras = intent.extras
        var goToIntent = extras?.getString("goto")

        if(goToIntent.equals("HomeActivity")){
            intento = Intent(this, HomeActivity::class.java)

            intento.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intento.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP

        }
        else if(goToIntent.equals("DndSchedaActivity")){
            intento = Intent(this, DndSchedaActivity::class.java)

            intento.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intento.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP

        }
        else if(goToIntent.equals("DndHome")){
            intento = Intent(this, DndHome::class.java)

            intento.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intento.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP

        }else if(goToIntent.equals("DndCampagnaHome")){
            intento = Intent(this, DndCampagnaHome::class.java)

            intento.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intento.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP

        }

        return intento
    }


}