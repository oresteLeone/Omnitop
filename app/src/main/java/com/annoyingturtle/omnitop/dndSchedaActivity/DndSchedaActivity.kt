package com.annoyingturtle.omnitop.dndSchedaActivity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.annoyingturtle.omnitop.R
import com.annoyingturtle.omnitop.dndCampagnaHomeActivity.DndCampagnaHome
import com.annoyingturtle.omnitop.dndSchedaActivity.dndSchedaFragments.DndDettagliFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dndData.entities.Scheda
import dndData.utilData.Dettagli
import dndData.viewModel.SchedaViewModel
import kotlinx.android.synthetic.main.activity_dnd_campagna_home.*
import kotlinx.android.synthetic.main.activity_home.addBtn1
import kotlinx.android.synthetic.main.activity_home.cardBtn1
import kotlinx.android.synthetic.main.activity_home.diceBtn1
import kotlinx.android.synthetic.main.activity_home.gridBtn1
import kotlinx.android.synthetic.main.activity_home.noteBtn1
import fabPackage.AbsFab
import fabPackage.GrigliaDIBattagliaActivity
import kotlinx.android.synthetic.main.fragment_dnd_dettagli.*
import kotlinx.android.synthetic.main.fragment_dnd_dettagli.view.*

class DndSchedaActivity : AppCompatActivity(){

    private lateinit var mSchedaViewModel: SchedaViewModel
    var idScheda = -1
    lateinit var schedaToDelete: Scheda
    lateinit var extras: Bundle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dnd_scheda)


        /**Action Bar */

        setSupportActionBar(myToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)


        /********** FAB ***********/
        val fab =
            AbsFab(addBtn1, cardBtn1, gridBtn1, noteBtn1, diceBtn1, this, supportFragmentManager, Intent(this, GrigliaDIBattagliaActivity::class.java))
        fab.startListener(this)


        /** Inizializzazione scheda */

        extras = intent.extras!!
        idScheda = extras!!.getInt("idScheda")

        mSchedaViewModel = ViewModelProvider(this).get(SchedaViewModel::class.java)
        if (idScheda > -1) {
            mSchedaViewModel.getSchedaFromID(idScheda)
            showSchedaData()

        }

        /** Schermate */


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        val navController = findNavController(R.id.schedafragment)
        bottomNavigationView.setupWithNavController(navController)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        var inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.scheda_dnd_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.idGuida -> startActivity(
                Intent(
                    this,
                    GuidaSchedaDnDActivity::class.java
                ).putExtras(extras)
            )

            R.id.idDelete -> {
                mSchedaViewModel.getSingleLiveData().observe(this, Observer {
                    schedaToDelete = Scheda(idScheda, it.Campagnaid, it.nomePG, it.tipoScheda, it.statistiche, it.incantatore, it.dettagli, it.moneteTotali)

                    eliminaScheda(schedaToDelete)
                })
            }

            else -> onBackPressed()
        }



        return super.onOptionsItemSelected(item)
    }

    fun showSchedaData() {
        mSchedaViewModel.getSingleLiveData().observe(this, Observer {
            supportActionBar?.title = Editable.Factory.getInstance().newEditable(it.nomePG)
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        return navigateUpTo(
            Intent(this, DndCampagnaHome::class.java).putExtra(
                "idCampagna",
                extras.getInt("idCampagna")
            )
        )
    }


    /** Funzione per la cancellazione di una Scheda (e per eventuali cancellazioni a cascata future)*/

    fun eliminaScheda(scheda : Scheda){

        val conferma = AlertDialog.Builder(this)
        conferma.setPositiveButton("Sì"){ _, _ ->
            mSchedaViewModel.getSingleLiveData().removeObservers(this)
            mSchedaViewModel.deleteScheda(schedaToDelete)
            Toast.makeText(this, "Scheda eliminata con successo!", Toast.LENGTH_SHORT).show()
            navigateUpTo(Intent(this, DndCampagnaHome::class.java).putExtra("idCampagna", extras.getInt("idCampagna")))

        }
        conferma.setNegativeButton("No"){ _, _ ->

        }
        conferma.setTitle("Eliminazione Scheda ${scheda.nomePG}")
        conferma.setMessage("Sei sicuro di voler cancellare la scheda ${scheda.nomePG}?")
        conferma.create().show()
    }

    fun chiudiKeyboard() {
        val view = this.currentFocus
        if (view != null)
        {
            val imm : InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}

