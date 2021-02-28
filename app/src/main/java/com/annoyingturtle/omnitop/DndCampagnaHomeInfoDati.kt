package com.annoyingturtle.omnitop

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
import dndData.RuoloGiocatore
import dndData.entities.Campagna
import dndData.viewModel.CampagnaViewModel
import kotlinx.android.synthetic.main.activity_dnd_campagna_home.*
import kotlinx.android.synthetic.main.activity_dnd_campagna_home_info_dati.*
import java.lang.Exception

class DndCampagnaHomeInfoDati : AppCompatActivity() {

    var idCampagna: Int = -1
    lateinit var mCampagnaViewModel: CampagnaViewModel
    lateinit var extras: Bundle
    lateinit var campagnaToDelete: Campagna


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dnd_campagna_home_info_dati)

        /**Action Bar */
        setSupportActionBar(myToolbarCampagnaDati)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        extras = intent.extras!!
        idCampagna = extras!!.getInt("idCampagna")

        mCampagnaViewModel = ViewModelProvider(this).get(CampagnaViewModel::class.java)
        mCampagnaViewModel.getCampagnaFromID(idCampagna)
        showCampagnaData()
        mCampagnaViewModel.getSingleLiveData().observe(this, Observer {
            campagnaToDelete = Campagna(idCampagna,it.titoloCampagna,it.ruoloCampagna,it.descrizione)
        })

        ButtonUpdateDataCampagna.setOnClickListener(){
            UpdateCampagnaDataToDatabase()
        }

    }

    private fun UpdateCampagnaDataToDatabase() {
        val titoloCampagna = NomeCampagna_et.text.toString()
        val descrizioneCampagna = DescrizioneCampagna_et.text.toString()
        val ruoloCampagna : RuoloGiocatore = if(checkBoxDM.isChecked)   RuoloGiocatore.DM
                                            else
                                                RuoloGiocatore.PG

        if (!inputCheck(titoloCampagna)){
            val campagnaUpdate = Campagna(idCampagna,titoloCampagna,ruoloCampagna,descrizioneCampagna)

            try {
                mCampagnaViewModel.updateCampagna(campagnaUpdate)

            }catch (e : Exception){
                Toast.makeText(this, "ERRORE: La Campagna non è stata aggiornata correttamente", Toast.LENGTH_LONG).show()
                return
            }

            Toast.makeText(this, "La Campagna è stata aggiornata con successo", Toast.LENGTH_SHORT).show()
            navigateUpTo(Intent(this, DndCampagnaHome::class.java).putExtra("idCampagna", idCampagna ))

        }
        else{
            Toast.makeText(this, "Nome Campagna vuoto!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun inputCheck(titoloCampagna: String?): Boolean {
        return (TextUtils.isEmpty(titoloCampagna))
    }

    private fun showCampagnaData() {
        mCampagnaViewModel.getSingleLiveData().observe(this, Observer {
            NomeCampagna_et.text = Editable.Factory.getInstance().newEditable(it.titoloCampagna)
            DescrizioneCampagna_et.text = Editable.Factory.getInstance().newEditable(it.descrizione)
            checkBoxDM.isChecked = it.ruoloCampagna == RuoloGiocatore.DM
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        var inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_con_pulsante_cancella, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.cancellaNotaBtn -> deleteCampagna(campagnaToDelete)

            else -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteCampagna(campagnaToDelete: Campagna) {
        val builder = AlertDialog.Builder(this)
        builder.setPositiveButton("Si"){ _, _ ->
            mCampagnaViewModel.getSingleLiveData().removeObservers(this)
            mCampagnaViewModel.deleteCampagna(campagnaToDelete)
            Toast.makeText(this, "Campagna eliminata con successo!", Toast.LENGTH_SHORT).show()
            navigateUpTo(Intent(this, DndHome::class.java))
        }
        builder.setNegativeButton("No"){ _, _ ->

        }

        builder.setTitle("Eliminazione Campagna ${campagnaToDelete.titoloCampagna}")
        builder.setMessage("Sei sicuro di voler cancellare la campagna ${campagnaToDelete.titoloCampagna}?")
        builder.create().show()

    }

    override fun onSupportNavigateUp(): Boolean {

        return navigateUpTo(Intent(this, DndCampagnaHome::class.java).putExtra("idCampagna", idCampagna ))
    }
}