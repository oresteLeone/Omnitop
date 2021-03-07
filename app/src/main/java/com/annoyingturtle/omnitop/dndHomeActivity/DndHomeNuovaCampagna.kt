package com.annoyingturtle.omnitop.dndHomeActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.annoyingturtle.omnitop.R
import dndData.RuoloGiocatore
import dndData.entities.Campagna
import dndData.viewModel.CampagnaViewModel
import kotlinx.android.synthetic.main.activity_dnd_home_nuova_campagna.*
import kotlinx.android.synthetic.main.activity_dnd_home_nuova_campagna.ButtonInsertDataAddCampagna
import kotlinx.android.synthetic.main.activity_dnd_home_nuova_campagna.checkBoxDM
import kotlinx.android.synthetic.main.activity_dnd_home_nuova_campagna.descrizioneCam_et
import kotlinx.android.synthetic.main.activity_dnd_home_nuova_campagna.titoloCam_et

class DndHomeNuovaCampagna : AppCompatActivity() {

    private lateinit var mCampagnaViewModel: CampagnaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dnd_home_nuova_campagna)

        /** Action bar */
        setSupportActionBar(myToolbarNuovaCampagna)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)


        mCampagnaViewModel = ViewModelProvider(this).get(CampagnaViewModel::class.java)
        ButtonInsertDataAddCampagna.setOnClickListener {
            insertCampagnaDataToDatabase()
        }

    }

    private fun insertCampagnaDataToDatabase() {

        val titoloCampagna = titoloCam_et.text.toString()
        val ruoloCampagna = if (checkBoxDM.isChecked) RuoloGiocatore.DM
                            else RuoloGiocatore.PG
        val descrizioneCampagna = descrizioneCam_et.text?.toString()

        if(!inputcheck(titoloCampagna)){
            val Campagna = Campagna(0, titoloCampagna, ruoloCampagna, descrizioneCampagna)//, null)
            mCampagnaViewModel.addCampagna(Campagna)
            Toast.makeText(this, "Campagna aggiunta con successo!", Toast.LENGTH_SHORT).show()
            navigateUpTo(Intent(this, DndHome::class.java))
        }
        else{
            Toast.makeText(this, "Titolo Campagna non può essere vuoto!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputcheck(titoloCampagna: String?): Boolean{

        return (TextUtils.isEmpty(titoloCampagna))

    }
}