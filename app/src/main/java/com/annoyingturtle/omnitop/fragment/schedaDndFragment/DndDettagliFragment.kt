package com.annoyingturtle.omnitop.fragment.schedaDndFragment

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
import kotlinx.android.synthetic.main.fragment_dnd_dettagli.*


class DndDettagliFragment : Fragment() {

    var idScheda = -1
    private lateinit var mSchedaViewModel : SchedaViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dnd_dettagli, container, false)
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
            razza.text = Editable.Factory.getInstance().newEditable(it.statistiche?.razza)
            classe.text = Editable.Factory.getInstance().newEditable(it.statistiche?.classe)
            backGround.text = Editable.Factory.getInstance().newEditable(it.dettagli?.background)
            nomeGiocatore.text = Editable.Factory.getInstance().newEditable(it.dettagli?.nomeGiocatore)
            allineamento.text = Editable.Factory.getInstance().newEditable(it.dettagli?.allineamento)
            puntiEsperienza.text = Editable.Factory.getInstance().newEditable(it.dettagli?.puntiEperienza.toString())
            puntiIspirazione.text = Editable.Factory.getInstance().newEditable(it.dettagli?.puntiIspirazione.toString())
            altreInformazioni.text = Editable.Factory.getInstance().newEditable(it.dettagli?.altreInformazioni)


        })
    }
}