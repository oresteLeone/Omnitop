package com.annoyingturtle.omnitop.dndSchedaActivity.dndSchedaFragments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.annoyingturtle.omnitop.R
import com.annoyingturtle.omnitop.dndSchedaActivity.DndSchedaActivity
import dndData.entities.Scheda
import dndData.utilData.Statistiche
import dndData.viewModel.SchedaViewModel
import kotlinx.android.synthetic.main.activity_update_caratteristiche_scheda.*
import java.lang.Exception

class UpdateCaratteristicheScheda : AppCompatActivity() {

    var idScheda = -1
    private lateinit var mSchedaViewModel : SchedaViewModel
    var campagnaid =-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_caratteristiche_scheda)

        /**Action Bar */

        setSupportActionBar(myToolbarCaratteristiche)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        /** Inizializzazione scheda*/

        idScheda = intent?.extras!!.getInt("idScheda")
        campagnaid = intent?.extras!!.getInt("idCampagna")
        mSchedaViewModel = ViewModelProvider(this).get(SchedaViewModel::class.java)
        if (idScheda > -1) {
            mSchedaViewModel.getSchedaFromID(idScheda)
        }
        showDati()
        attivaListener()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_salvataggio_dati_scheda, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId)
        {
            R.id.idSalva -> salvaDati()
            else -> onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    fun salvaDati(){
        mSchedaViewModel.getSingleLiveData().observe(this, Observer {
            val str: Int = if (forza.text.isEmpty()) 0 else forza.text.toString().toInt()
            val dex: Int = if (destrezza.text.isEmpty()) 0 else destrezza.text.toString().toInt()
            val con: Int = if (costituzione.text.isEmpty()) 0 else costituzione.text.toString().toInt()
            val int: Int = if (intelligenza.text.isEmpty()) 0 else intelligenza.text.toString().toInt()
            val wis: Int = if (saggezza.text.isEmpty()) 0 else saggezza.text.toString().toInt()
            val cha: Int = if (carisma.text.isEmpty()) 0 else carisma.text.toString().toInt()

            val strTS: Boolean = tsForza.isChecked
            val dexTS: Boolean = tsDestrezza.isChecked
            val conTS: Boolean = tsCostituzione.isChecked
            val intTS: Boolean = tsIntelligenza.isChecked
            val sagTS: Boolean = tsSaggezza.isChecked
            val carTs: Boolean = tsCarisma.isChecked


            val statisticheNuove = Statistiche(
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.puntiFeritaAttuali,  mSchedaViewModel.getSingleLiveData().value!!.statistiche?.puntiFeritaTotali,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.classeArmatura,  mSchedaViewModel.getSingleLiveData().value!!.statistiche?.iniziativa,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.velocitaMov,  mSchedaViewModel.getSingleLiveData().value!!.statistiche?.bonusCompetenza,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.dadoVita,  str, dex, con, int, wis, cha, strTS, dexTS, conTS, intTS, sagTS, carTs,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.abAcrobazia, mSchedaViewModel.getSingleLiveData().value!!.statistiche?.abAddestrare,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.abArcano, mSchedaViewModel.getSingleLiveData().value!!.statistiche?.abAtletica,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.abFurtivita, mSchedaViewModel.getSingleLiveData().value!!.statistiche?.abIndagare,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.abInganno, mSchedaViewModel.getSingleLiveData().value!!.statistiche?.abIntimidire,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.abIntrattenere, mSchedaViewModel.getSingleLiveData().value!!.statistiche?.abIntuizione,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.abMedicina, mSchedaViewModel.getSingleLiveData().value!!.statistiche?.abNatura,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.abPercezione, mSchedaViewModel.getSingleLiveData().value!!.statistiche?.abPersuasione,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.abRapiditMano, mSchedaViewModel.getSingleLiveData().value!!.statistiche?.abReligione,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.abSoprav, mSchedaViewModel.getSingleLiveData().value!!.statistiche?.abStoria
            )

            mSchedaViewModel.updateScheda(
                Scheda(
                mSchedaViewModel.getSingleLiveData().value!!.id,
                mSchedaViewModel.getSingleLiveData().value!!.Campagnaid,
                mSchedaViewModel.getSingleLiveData().value!!.nomePG,
                mSchedaViewModel.getSingleLiveData().value!!.tipoScheda,
                statisticheNuove,
                mSchedaViewModel.getSingleLiveData().value!!.incantatore,
                mSchedaViewModel.getSingleLiveData().value!!.dettagli,
                mSchedaViewModel.getSingleLiveData().value!!.moneteTotali
            )
            )


        })


        Toast.makeText(applicationContext, "Modifica avvenuta con successo!", Toast.LENGTH_SHORT).show()
        onSupportNavigateUp()

    }
    override fun onSupportNavigateUp(): Boolean {

        return navigateUpTo(Intent(this, DndSchedaActivity::class.java).putExtra("idScheda", idScheda).putExtra("idCampagna", campagnaid))
    }

    fun attivaListener(){
        forza.addTextChangedListener(){
            try {
                if (forza.text.toString().toInt() > 30) forza.setText(
                    "30",
                    TextView.BufferType.EDITABLE
                )
            }catch (e: Exception) {
            }
        }

        destrezza.addTextChangedListener(){
            try {
                if (destrezza.text.toString().toInt() > 30) destrezza.setText(
                    "30",
                    TextView.BufferType.EDITABLE
                )
            }catch (e: Exception) {
            }
        }

        costituzione.addTextChangedListener(){
            try {
                if (costituzione.text.toString().toInt() > 30) costituzione.setText(
                    "30",
                    TextView.BufferType.EDITABLE
                )
            }catch (e: Exception) {
            }
        }

        intelligenza.addTextChangedListener(){
            try {
                if (intelligenza.text.toString().toInt() > 30) intelligenza.setText(
                    "30",
                    TextView.BufferType.EDITABLE
                )
            }catch (e: Exception) {
            }
        }

        saggezza.addTextChangedListener(){
            try {
                if (saggezza.text.toString().toInt() > 30) saggezza.setText(
                    "30",
                    TextView.BufferType.EDITABLE
                )
            }catch (e: Exception) {
            }
        }

        carisma.addTextChangedListener(){
            try {
                if (carisma.text.toString().toInt() > 30) carisma.setText(
                    "30",
                    TextView.BufferType.EDITABLE
                )
            }catch (e: Exception) {
            }
        }
    }


    fun showDati(){
        mSchedaViewModel.getSingleLiveData().observe(this, Observer {
            forza.text = Editable.Factory.getInstance().newEditable(it.statistiche?.STR.toString())
            destrezza.text = Editable.Factory.getInstance().newEditable(it.statistiche?.DEX.toString())
            costituzione.text = Editable.Factory.getInstance().newEditable(it.statistiche?.CON.toString())
            intelligenza.text = Editable.Factory.getInstance().newEditable(it.statistiche?.INT.toString())
            saggezza.text = Editable.Factory.getInstance().newEditable(it.statistiche?.WIS.toString())
            carisma.text = Editable.Factory.getInstance().newEditable(it.statistiche?.CHA.toString())

            tsForza.isChecked = it.statistiche?.tiroSalvezzaSTR == true
            tsDestrezza.isChecked = it.statistiche?.tiroSalvezzaDEX == true
            tsCostituzione.isChecked = it.statistiche?.tiroSalvezzaCON == true
            tsIntelligenza.isChecked = it.statistiche?.tiroSalvezzaINT == true
            tsSaggezza.isChecked = it.statistiche?.tiroSalvezzaWIS == true
            tsCarisma.isChecked = it.statistiche?.tiroSalvezzaCHA == true


        })
    }
}