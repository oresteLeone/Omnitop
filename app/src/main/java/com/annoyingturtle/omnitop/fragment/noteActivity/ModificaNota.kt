package com.annoyingturtle.omnitop.fragment.noteActivity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.annoyingturtle.omnitop.*
import dndData.entities.Notes
import dndData.viewModel.NotesViewModel
import kotlinx.android.synthetic.main.activity_modifica_nota.*

class ModificaNota() : AppCompatActivity() {

    private lateinit var mNotaViewModel : NotesViewModel

    var intento = Intent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modifica_nota)

        setSupportActionBar(myToolbarModificaNota)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        /**Visualizzazione a schermo dati attuali*/

        var extras = intent.extras
        var idNota : Int? = extras?.getInt("idItem")
        println(idNota)

        mNotaViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)

        //mNotaViewModel.getSingleLiveData()
        if (idNota != null) {
            mNotaViewModel.getNotesFromID(idNota)
        }

        //idNota?.let { mNotaViewModel.getNotesFromID(it) }



        titoloNota.setText(mNotaViewModel.getSingleLiveData().value?.titoloNota)
        testoNota.setText(mNotaViewModel.getSingleLiveData().value?.corpoNota)



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
        var extras = intent.extras
        var goToIntent = extras?.getString("goto")

        when {
            goToIntent.equals("HomeActivity") -> {
                intento = Intent(this@ModificaNota, HomeActivity::class.java)

                intento.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                intento.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP

            }
            goToIntent.equals("DndSchedaActivity") -> {
                intento = Intent(this@ModificaNota, DndSchedaActivity::class.java)

                intento.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                intento.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP

            }
            goToIntent.equals("DndHome") -> {
                intento = Intent(this@ModificaNota, DndHome::class.java)

                intento.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                intento.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP

            }
            goToIntent.equals("DndCampagnaHome") -> {
                intento = Intent(this@ModificaNota, DndCampagnaHome::class.java)

                intento.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                intento.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP

            }
        }

        return intento
    }


}