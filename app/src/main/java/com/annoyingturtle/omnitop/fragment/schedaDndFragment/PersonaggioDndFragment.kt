package com.annoyingturtle.omnitop.fragment.schedaDndFragment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.annoyingturtle.omnitop.R
import dndData.viewModel.SchedaViewModel
import kotlinx.android.synthetic.main.fragment_personaggio.*


class PersonaggioDndFragment : Fragment() {

    var idScheda = -1
    private lateinit var mSchedaViewModel : SchedaViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personaggio, container, false)
    }

    override fun onStart() {
        super.onStart()

        /** Inizializzazione scheda*/

        idScheda = activity?.intent?.extras!!.getInt("idScheda")

        mSchedaViewModel = ViewModelProvider(this).get(SchedaViewModel::class.java)
        if (idScheda > -1){
            mSchedaViewModel.getSchedaFromID(idScheda)
            showSchedaData()
        }

    }


    fun showSchedaData(){
        mSchedaViewModel.getSingleLiveData().observe(this, Observer {
            pfAttuali.text= Editable.Factory.getInstance().newEditable(it.statistiche?.puntiFeritaAttuali.toString())
            pfTotali.text= Editable.Factory.getInstance().newEditable(it.statistiche?.puntiFeritaTotali.toString())
            classeArmatura.text= Editable.Factory.getInstance().newEditable(it.statistiche?.classeArmatura.toString())
            iniziativa.text= Editable.Factory.getInstance().newEditable(it.statistiche?.iniziativa.toString())
            velocità.text= Editable.Factory.getInstance().newEditable(it.statistiche?.velocitaMov.toString())
            percezionePassiva.text= Editable.Factory.getInstance().newEditable(it.statistiche?.percezionePassiva.toString())
            profBonus.text= Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString())
            dadiVita.text= Editable.Factory.getInstance().newEditable(it.statistiche?.dadoVita.toString())

            valoreForza.text= Editable.Factory.getInstance().newEditable(it.statistiche?.STR.toString())
            valoreDestrezza.text= Editable.Factory.getInstance().newEditable(it.statistiche?.DEX.toString())
            valoreCostituzione.text= Editable.Factory.getInstance().newEditable(it.statistiche?.CON.toString())
            valoreInt.text= Editable.Factory.getInstance().newEditable(it.statistiche?.INT.toString())
            valoreSag.text= Editable.Factory.getInstance().newEditable(it.statistiche?.WIS.toString())
            valoreCha.text= Editable.Factory.getInstance().newEditable(it.statistiche?.CHA.toString())

            tsForzaCheck.isChecked = it.statistiche?.tiroSalvezzaSTR == true
            tsDestrezzaCheck.isChecked = it.statistiche?.tiroSalvezzaDEX == true
            tsCostituzioneCheck.isChecked = it.statistiche?.tiroSalvezzaCON == true
            tsIntCheck.isChecked = it.statistiche?.tiroSalvezzaINT == true
            tsSaggCheck.isChecked = it.statistiche?.tiroSalvezzaWIS == true
            tsChaCheck.isChecked = it.statistiche?.tiroSalvezzaCHA == true

            valoreAcrobazia.text= Editable.Factory.getInstance().newEditable(it.statistiche?.abAcrobazia.toString())
            valoreAddestrareAnim.text= Editable.Factory.getInstance().newEditable(it.statistiche?.abAddestrare.toString())
            valoreArcano.text= Editable.Factory.getInstance().newEditable(it.statistiche?.abArcano.toString())
            valoreAtletica.text= Editable.Factory.getInstance().newEditable(it.statistiche?.abAtletica.toString())
            valoreFurtività.text= Editable.Factory.getInstance().newEditable(it.statistiche?.abFurtivita.toString())
            valoreIndagare.text= Editable.Factory.getInstance().newEditable(it.statistiche?.abIndagare.toString())
            valoreInganno.text= Editable.Factory.getInstance().newEditable(it.statistiche?.abInganno.toString())
            valoreIntimidire.text= Editable.Factory.getInstance().newEditable(it.statistiche?.abIntimidire.toString())
            valoreIntuizione.text= Editable.Factory.getInstance().newEditable(it.statistiche?.abIntuizione.toString())
            valoreMedicina.text= Editable.Factory.getInstance().newEditable(it.statistiche?.abMedicina.toString())
            valoreNatua.text= Editable.Factory.getInstance().newEditable(it.statistiche?.abNatura.toString())
            valorePercezione.text= Editable.Factory.getInstance().newEditable(it.statistiche?.abPercezione.toString())
            valorePersuasione.text= Editable.Factory.getInstance().newEditable(it.statistiche?.abPersuasione.toString())
            valoreRapiditaDiMano.text= Editable.Factory.getInstance().newEditable(it.statistiche?.abRapiditMano.toString())
            valoreReligione.text= Editable.Factory.getInstance().newEditable(it.statistiche?.abReligione.toString())
            valoreSopravvivenza.text= Editable.Factory.getInstance().newEditable(it.statistiche?.abSoprav.toString())
            valoreStoria.text= Editable.Factory.getInstance().newEditable(it.statistiche?.abStoria.toString())
        })

    }


}