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
import kotlinx.android.synthetic.main.fragment_equipaggiamento_dnd.*


class EquipaggiamentoDnDFragment : Fragment() {

    var idScheda = -1
    private lateinit var mSchedaViewModel : SchedaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_equipaggiamento_dnd, container, false)
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
            moneteRame.text= Editable.Factory.getInstance().newEditable(it.moneteTotali?.moneteR.toString())
            moneteArgento.text= Editable.Factory.getInstance().newEditable(it.moneteTotali?.moneteA.toString())
            moneteOro.text= Editable.Factory.getInstance().newEditable(it.moneteTotali?.moneteO.toString())
            moneteElectrum.text= Editable.Factory.getInstance().newEditable(it.moneteTotali?.moneteE.toString())
            monetePlatino.text= Editable.Factory.getInstance().newEditable(it.moneteTotali?.moneteP.toString())
        })

    }

}