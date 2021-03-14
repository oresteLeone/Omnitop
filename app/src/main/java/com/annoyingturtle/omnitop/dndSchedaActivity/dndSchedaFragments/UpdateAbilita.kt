package com.annoyingturtle.omnitop.dndSchedaActivity.dndSchedaFragments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.annoyingturtle.omnitop.R
import com.annoyingturtle.omnitop.dndSchedaActivity.DndSchedaActivity
import dndData.entities.Scheda
import dndData.utilData.Statistiche
import dndData.viewModel.SchedaViewModel
import kotlinx.android.synthetic.main.activity_update_abilita.*
import kotlinx.android.synthetic.main.activity_update_caratteristiche_scheda.*

class UpdateAbilita : AppCompatActivity() {
    var idScheda = -1
    private lateinit var mSchedaViewModel : SchedaViewModel
    var campagnaid = -1

    var acrobazia = 0
    var addestrare = 0
    var arcano = 0
    var atletica = 0
    var furtivita = 0
    var indagare = 0
    var inganno = 0
    var intimidire = 0
    var intrattenere = 0
    var intuizione = 0
    var medicina = 0
    var natura = 0
    var percezione = 0
    var persuasione = 0
    var rapidita = 0
    var religione = 0
    var sopravvivenza = 0
    var storia = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_abilita)

        /**Action Bar */

        setSupportActionBar(myToolbarModificaAbilità)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        /** Inizializzazione scheda*/

        idScheda = intent?.extras!!.getInt("idScheda")
        campagnaid = intent?.extras!!.getInt("idCampagna")

        mSchedaViewModel = ViewModelProvider(this).get(SchedaViewModel::class.java)
        if (idScheda > -1) {
            mSchedaViewModel.getSchedaFromID(idScheda)
            showDati()

        }

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

    override fun onSupportNavigateUp(): Boolean {

        return navigateUpTo(Intent(this, DndSchedaActivity::class.java).putExtra("idScheda", idScheda).putExtra("idCampagna", campagnaid))
    }





    fun showDati(){

        mSchedaViewModel.getSingleLiveData().observe(this, Observer {


            acrobazia = Editable.Factory.getInstance().newEditable(it.statistiche?.abAcrobazia.toString()).toString().toInt()
            addestrare = Editable.Factory.getInstance().newEditable(it.statistiche?.abAddestrare.toString()).toString().toInt()
            arcano = Editable.Factory.getInstance().newEditable(it.statistiche?.abArcano.toString()).toString().toInt()
            atletica = Editable.Factory.getInstance().newEditable(it.statistiche?.abAtletica.toString()).toString().toInt()
            furtivita = Editable.Factory.getInstance().newEditable(it.statistiche?.abFurtivita.toString()).toString().toInt()
            indagare = Editable.Factory.getInstance().newEditable(it.statistiche?.abIndagare.toString()).toString().toInt()
            inganno = Editable.Factory.getInstance().newEditable(it.statistiche?.abInganno.toString()).toString().toInt()
            intimidire = Editable.Factory.getInstance().newEditable(it.statistiche?.abIntimidire.toString()).toString().toInt()
            intrattenere = Editable.Factory.getInstance().newEditable(it.statistiche?.abIntrattenere.toString()).toString().toInt()
            intuizione = Editable.Factory.getInstance().newEditable(it.statistiche?.abIntuizione.toString()).toString().toInt()
            medicina = Editable.Factory.getInstance().newEditable(it.statistiche?.abMedicina.toString()).toString().toInt()
            natura = Editable.Factory.getInstance().newEditable(it.statistiche?.abNatura.toString()).toString().toInt()
            percezione = Editable.Factory.getInstance().newEditable(it.statistiche?.abPercezione.toString()).toString().toInt()
            persuasione = Editable.Factory.getInstance().newEditable(it.statistiche?.abPersuasione.toString()).toString().toInt()
            rapidita = Editable.Factory.getInstance().newEditable(it.statistiche?.abRapiditMano.toString()).toString().toInt()
            religione = Editable.Factory.getInstance().newEditable(it.statistiche?.abReligione.toString()).toString().toInt()
            sopravvivenza = Editable.Factory.getInstance().newEditable(it.statistiche?.abSoprav.toString()).toString().toInt()
            storia = Editable.Factory.getInstance().newEditable(it.statistiche?.abStoria.toString()).toString().toInt()

            when (acrobazia){
                0-> acrobazia0.isChecked = true
                1-> acrobazia1.isChecked = true
                2-> acrobazia2.isChecked = true
            }

            when (addestrare){
                0-> addestrare0.isChecked = true
                1-> addestrare1.isChecked = true
                2-> addestrare2.isChecked = true
            }

            when (arcano){
                0-> Arcano0.isChecked = true
                1-> Arcano1.isChecked = true
                2-> Arcano2.isChecked = true
            }

            when (atletica){
                0-> Atletica0.isChecked = true
                1-> Atletica1.isChecked = true
                2-> Atletica2.isChecked = true
            }

            when (furtivita){
                0-> Furtività0.isChecked = true
                1-> Furtività1.isChecked = true
                2-> Furtività2.isChecked = true
            }

            when (indagare){
                0-> Indagare0.isChecked = true
                1-> Indagare1.isChecked = true
                2-> Indagare2.isChecked = true
            }

            when (inganno){
                0-> Inganno0.isChecked = true
                1-> Inganno1.isChecked = true
                2-> Inganno2.isChecked = true
            }

            when (intimidire){
                0-> Intimidire0.isChecked = true
                1-> Intimidire1.isChecked = true
                2-> Intimidire2.isChecked = true
            }

            when (intrattenere){
                0-> Intrattenere0.isChecked = true
                1-> Intrattenere1.isChecked = true
                2-> Intrattenere2.isChecked = true
            }

            when (intuizione){
                0-> Intuizione0.isChecked = true
                1-> Intuizione1.isChecked = true
                2-> Intuizione2.isChecked = true
            }

            when (medicina){
                0-> Medicina0.isChecked = true
                1-> Medicina1.isChecked = true
                2-> Medicina2.isChecked = true
            }

            when (natura){
                0-> Natura0.isChecked = true
                1-> Natura1.isChecked = true
                2-> Natura2.isChecked = true
            }

            when (percezione){
                0-> Percezione0.isChecked = true
                1-> Percezione1.isChecked = true
                2-> Percezione2.isChecked = true
            }

            when (persuasione){
                0-> Persuasione0.isChecked = true
                1-> Persuasione1.isChecked = true
                2-> Persuasione2.isChecked = true
            }

            when (rapidita){
                0-> Rapidità0.isChecked = true
                1-> Rapidità1.isChecked = true
                2-> Rapidità2.isChecked = true
            }

            when (religione){
                0-> Religione0.isChecked = true
                1-> Religione1.isChecked = true
                2-> Religione2.isChecked = true
            }

            when (sopravvivenza){
                0-> Sopravvivenza0.isChecked = true
                1-> Sopravvivenza1.isChecked = true
                2-> Sopravvivenza2.isChecked = true
            }

            when (storia){
                0-> Storia0.isChecked = true
                1-> Storia1.isChecked = true
                2-> Storia2.isChecked = true
            }

        })

    }


    fun salvaDati(){

        mSchedaViewModel.getSingleLiveData().observe(this, Observer {

            if(acrobazia0.isChecked) acrobazia=0 else if (acrobazia1.isChecked) acrobazia = 1 else if (acrobazia2.isChecked) acrobazia = 2

            if(addestrare0.isChecked) addestrare=0 else if (addestrare1.isChecked) addestrare = 1 else if (addestrare2.isChecked) addestrare = 2

            if(Arcano0.isChecked) arcano=0 else if (Arcano1.isChecked) arcano = 1 else if (Arcano2.isChecked) arcano = 2

            if(Atletica0.isChecked) atletica=0 else if (Atletica1.isChecked) atletica = 1 else if (Atletica2.isChecked) atletica = 2

            if(Furtività0.isChecked) furtivita=0 else if (Furtività1.isChecked) furtivita = 1 else if (Furtività2.isChecked) furtivita = 2

            if(Indagare0.isChecked) indagare=0 else if (Indagare1.isChecked) indagare = 1 else if (Indagare2.isChecked) indagare = 2

            if(Inganno0.isChecked) inganno=0 else if (Inganno1.isChecked) inganno = 1 else if (Inganno2.isChecked) inganno = 2

            if(Intimidire0.isChecked) intimidire=0 else if (Intimidire1.isChecked) intimidire = 1 else if (Intimidire2.isChecked) intimidire = 2

            if(Intrattenere0.isChecked) intrattenere=0 else if (Intrattenere1.isChecked) intrattenere = 1 else if (Intrattenere2.isChecked) intrattenere = 2

            if(Intuizione0.isChecked) intuizione=0 else if (Intuizione1.isChecked) intuizione = 1 else if (Intuizione2.isChecked) intuizione = 2

            if(Medicina0.isChecked) medicina=0 else if (Medicina1.isChecked) medicina = 1 else if (Medicina2.isChecked) medicina = 2


            if(Natura0.isChecked) natura=0 else if (Natura1.isChecked) natura = 1 else if (Natura2.isChecked) natura = 2


            if(Percezione0.isChecked) percezione=0 else if (Percezione1.isChecked) percezione = 1 else if (Percezione2.isChecked) percezione = 2


            if(Persuasione0.isChecked) persuasione=0 else if (Persuasione1.isChecked) persuasione = 1 else if (Persuasione2.isChecked) persuasione = 2


            if(Rapidità0.isChecked) rapidita=0 else if (Rapidità1.isChecked) rapidita = 1 else if (Rapidità2.isChecked) rapidita = 2


            if(Religione0.isChecked) religione=0 else if (Religione1.isChecked) religione = 1 else if (Religione2.isChecked) religione = 2


            if(Sopravvivenza0.isChecked) sopravvivenza=0 else if (Sopravvivenza1.isChecked) sopravvivenza = 1 else if (Sopravvivenza2.isChecked) sopravvivenza = 2


            if(Storia0.isChecked) storia=0 else if (Storia1.isChecked) storia = 1 else if (Storia2.isChecked) storia = 2

            val statisticheNuove = Statistiche(
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.puntiFeritaAttuali,  mSchedaViewModel.getSingleLiveData().value!!.statistiche?.puntiFeritaTotali,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.classeArmatura,  mSchedaViewModel.getSingleLiveData().value!!.statistiche?.iniziativa,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.velocitaMov,  mSchedaViewModel.getSingleLiveData().value!!.statistiche?.bonusCompetenza,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.dadoVita, mSchedaViewModel.getSingleLiveData().value!!.statistiche?.STR,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.DEX, mSchedaViewModel.getSingleLiveData().value!!.statistiche?.CON,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.INT, mSchedaViewModel.getSingleLiveData().value!!.statistiche?.WIS,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.CHA, mSchedaViewModel.getSingleLiveData().value!!.statistiche?.tiroSalvezzaSTR,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.tiroSalvezzaDEX, mSchedaViewModel.getSingleLiveData().value!!.statistiche?.tiroSalvezzaCON,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.tiroSalvezzaINT, mSchedaViewModel.getSingleLiveData().value!!.statistiche?.tiroSalvezzaWIS,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.tiroSalvezzaCHA, acrobazia, addestrare, arcano, atletica, furtivita, indagare, inganno,
                intimidire, intrattenere, intuizione, medicina, natura, percezione, persuasione, rapidita, religione, sopravvivenza, storia
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
                    mSchedaViewModel.getSingleLiveData().value!!.moneteTotali,
                    mSchedaViewModel.getSingleLiveData().value!!.imgSchedaBitmap
                )
            )

        })

        Toast.makeText(applicationContext, "Modifica avvenuta con successo!", Toast.LENGTH_SHORT).show()
        onSupportNavigateUp()

    }
}
