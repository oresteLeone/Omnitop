package com.annoyingturtle.omnitop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import dndData.TipoScheda
import dndData.entities.Scheda
import dndData.viewModel.SchedaViewModel
import kotlinx.android.synthetic.main.activity_dnd_campagna_nuova_scheda.*
import kotlinx.android.synthetic.main.activity_nuova_nota.*
import java.lang.Exception

class DndCampagnaNuovaScheda : AppCompatActivity() {

    var idCampagna: Int = -1
    lateinit var mSchedaViewModel: SchedaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dnd_campagna_nuova_scheda)

        /** Action bar */
        setSupportActionBar(myToolbarNuovaScheda)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        var extras = intent.extras
        idCampagna = extras!!.getInt("idCampagna")

        mSchedaViewModel = ViewModelProvider(this).get(SchedaViewModel::class.java)
        ButtonInsertDataAddScheda.setOnClickListener(){
            insertSchedaDataToDatabase()

        }
    }

    private fun insertSchedaDataToDatabase() {
        val nomePersonaggio = NomePersonaggio_et.text.toString()
        val tipoScheda: TipoScheda = if(checkBoxPNG.isChecked) TipoScheda.PNG
            else
                TipoScheda.PG

        if(!inputCheck(nomePersonaggio)){
            val scheda = Scheda(0,idCampagna,nomePersonaggio,tipoScheda)
            try {
                mSchedaViewModel.addScheda(scheda)
            }catch (e: Exception){
                Toast.makeText(this, "ERRORE: La scheda non è stata salvata correttamente", Toast.LENGTH_LONG).show()
                return
            }
            Toast.makeText(this, "La scheda è stata salvata con successo", Toast.LENGTH_SHORT).show()
            navigateUpTo(Intent(this,DndCampagnaHome::class.java).putExtra("idItem", idCampagna ))
        }
        else
            Toast.makeText(this, "Riempi i campi necessari!", Toast.LENGTH_SHORT).show()
    }

    private fun inputCheck(nomePersonaggio: String?): Boolean {

        return TextUtils.isEmpty(nomePersonaggio)
    }
}