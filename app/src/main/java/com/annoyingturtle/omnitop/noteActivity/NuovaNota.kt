package com.annoyingturtle.omnitop.noteActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.annoyingturtle.omnitop.*
import com.annoyingturtle.omnitop.dndCampagnaHomeActivity.DndCampagnaHome
import com.annoyingturtle.omnitop.dndHomeActivity.DndHome
import com.annoyingturtle.omnitop.dndSchedaActivity.DndSchedaActivity
import dndData.RuoloGiocatore
import dndData.entities.Notes
import dndData.viewModel.NotesViewModel
import kotlinx.android.synthetic.main.activity_nuova_nota.*
import java.lang.Exception

class NuovaNota() : AppCompatActivity() {

    var extras: Bundle? = null

    private lateinit var mNotaViewModel : NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuova_nota)

        extras = intent?.extras

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
        val titoloNota = titoloNota_et.text.toString()
        val testoNota = testoNota_et.text.toString()
        val preferito : Boolean = checkBoxPreferito.isChecked
        val ruoloGiocatore : RuoloGiocatore = if(checkBoxDM.isChecked) RuoloGiocatore.DM
        else
            RuoloGiocatore.PG

        if(inputCheck(titoloNota, testoNota)){
            val nota = Notes(0, null, titoloNota = titoloNota, corpoNota =  testoNota, preferito = preferito, ruoloNota = ruoloGiocatore )
            try {
                mNotaViewModel.addNota(nota)
            }catch (e : Exception)
            {
                Toast.makeText(this, "ERRORE: La nota non è stata salvata correttamente", Toast.LENGTH_LONG).show()
                return
            }
            Toast.makeText(this, "La nota è stata salvata con successo", Toast.LENGTH_SHORT).show()
            navigateUpTo(parentMetod())
        }
        else
            Toast.makeText(this, "Riempi i campi necessari!", Toast.LENGTH_SHORT).show()

    }

    private fun inputCheck(titoloNota: String?, testoNota : String?): Boolean {

        return !(TextUtils.isEmpty(titoloNota) && TextUtils.isEmpty(testoNota))
    }

    /** Funzioni per la navigazione all'attività precedente*/

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

        var goToIntent = extras?.getString("goto")

        when {
            goToIntent.equals("HomeActivity") -> {
                intento = Intent(this, HomeActivity::class.java)

                intento.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                intento.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP

            }
            goToIntent.equals("DndSchedaActivity") -> {
                intento = Intent(this, DndSchedaActivity::class.java).putExtra("idScheda", extras?.getInt("idScheda"))

                intento.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                intento.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP

            }
            goToIntent.equals("DndHome") -> {
                intento = Intent(this, DndHome::class.java)

                intento.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                intento.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP

            }
            goToIntent.equals("DndCampagnaHome") -> {
                intento = Intent(this, DndCampagnaHome::class.java).putExtra("idCampagna", extras?.getInt("idCampagna"))

                intento.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                intento.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP

            }
        }

        return intento
    }


}