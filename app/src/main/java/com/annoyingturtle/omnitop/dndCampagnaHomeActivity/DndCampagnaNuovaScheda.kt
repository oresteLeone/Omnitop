package com.annoyingturtle.omnitop.dndCampagnaHomeActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.annoyingturtle.omnitop.R
import dndData.LvlCompetenza
import dndData.TipoScheda
import dndData.entities.Scheda
import dndData.utilData.Dettagli
import dndData.utilData.Incantatore
import dndData.utilData.Money
import dndData.utilData.Statistiche
import dndData.viewModel.SchedaViewModel
import kotlinx.android.synthetic.main.activity_dnd_campagna_nuova_scheda.*
import java.lang.Exception

class DndCampagnaNuovaScheda : AppCompatActivity() {

    var idCampagna: Int = -1
    lateinit var mSchedaViewModel: SchedaViewModel
    var extras: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dnd_campagna_nuova_scheda)

        /** Action bar */
        setSupportActionBar(myToolbarNuovaScheda)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        extras = intent.extras
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

            /**Valore per le statistiche della scheda*/

            val statistiche = Statistiche(0,0,0,0,0.0,2,0,0,
                0,0,0,0,0,false,false,false,false,false,false,
                0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                0,0,0,0)

            /**Valore per Incantatore */
            var incantatore = Incantatore("","",0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0)



            /** Valore per dettagli*/

            val dettagli = Dettagli("","","","","",0,0,"")



            /** Valore per le monete della scheda*/
            val moneteTotali = Money(0,0,0,0,0)


            val scheda = Scheda(0,idCampagna,nomePersonaggio,tipoScheda, statistiche, incantatore, dettagli, moneteTotali)
            try {
                mSchedaViewModel.addScheda(scheda)
            }catch (e: Exception){
                Toast.makeText(this, "ERRORE: La scheda non è stata salvata correttamente", Toast.LENGTH_LONG).show()
                return
            }
            Toast.makeText(this, "La scheda è stata salvata con successo", Toast.LENGTH_SHORT).show()
            navigateUpTo(Intent(this, DndCampagnaHome::class.java).putExtra("idCampagna", idCampagna ))
        }
        else
            Toast.makeText(this, "Riempi i campi necessari!", Toast.LENGTH_SHORT).show()
    }

    private fun inputCheck(nomePersonaggio: String?): Boolean {

        return TextUtils.isEmpty(nomePersonaggio)
    }

    override fun onSupportNavigateUp(): Boolean {

       return navigateUpTo(Intent(this, DndCampagnaHome::class.java).putExtra("idCampagna", idCampagna ))
    }

}