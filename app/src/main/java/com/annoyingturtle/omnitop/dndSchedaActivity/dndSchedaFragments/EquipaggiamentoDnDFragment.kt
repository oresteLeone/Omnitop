package com.annoyingturtle.omnitop.dndSchedaActivity.dndSchedaFragments

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.annoyingturtle.omnitop.R
import com.annoyingturtle.omnitop.dndSchedaActivity.DndSchedaActivity
import dndData.entities.Scheda
import dndData.utilData.Money
import dndData.viewModel.SchedaViewModel
import kotlinx.android.synthetic.main.fragment_equipaggiamento_dnd.*


class EquipaggiamentoDnDFragment : Fragment() {

    var idScheda = -1
    private lateinit var mSchedaViewModel : SchedaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_equipaggiamento_dnd, container, false)

        /** Inizializzazione scheda*/

        idScheda = activity?.intent?.extras!!.getInt("idScheda")

        mSchedaViewModel = ViewModelProvider(this).get(SchedaViewModel::class.java)
        if (idScheda > -1){
            mSchedaViewModel.getSchedaFromID(idScheda)
            showSchedaData()

        }

        return view
    }

    override fun onStart() {
        super.onStart()


        editMonete.setOnClickListener(){

            editMonete.visibility = View.GONE
            editMoneteSALVA.visibility = View.VISIBLE

            moneteRame.isFocusableInTouchMode = true
            moneteRame.isClickable = true

            moneteArgento.isFocusableInTouchMode = true
            moneteArgento.isClickable = true

            moneteOro.isFocusableInTouchMode = true
            moneteOro.isClickable = true

            moneteElectrum.isFocusableInTouchMode = true
            moneteElectrum.isClickable = true

            monetePlatino.isFocusableInTouchMode = true
            monetePlatino.isClickable = true
        }

        editMoneteSALVA.setOnClickListener(){
            editMonete.visibility = View.VISIBLE
            editMoneteSALVA.visibility = View.GONE

            moneteRame.isFocusableInTouchMode = false
            moneteRame.isClickable = false

            moneteArgento.isFocusableInTouchMode = false
            moneteArgento.isClickable = false

            moneteOro.isFocusableInTouchMode = false
            moneteOro.isClickable = false

            moneteElectrum.isFocusableInTouchMode = false
            moneteElectrum.isClickable = false

            monetePlatino.isFocusableInTouchMode = false
            monetePlatino.isClickable = false

            updateMonete()
            (activity as DndSchedaActivity).chiudiKeyboard()
        }

    }

    fun showSchedaData(){

        mSchedaViewModel.getSingleLiveData().observe(viewLifecycleOwner, Observer {
            moneteRame.text= Editable.Factory.getInstance().newEditable(it.moneteTotali?.moneteR.toString())
            moneteArgento.text= Editable.Factory.getInstance().newEditable(it.moneteTotali?.moneteA.toString())
            moneteOro.text= Editable.Factory.getInstance().newEditable(it.moneteTotali?.moneteO.toString())
            moneteElectrum.text= Editable.Factory.getInstance().newEditable(it.moneteTotali?.moneteE.toString())
            monetePlatino.text= Editable.Factory.getInstance().newEditable(it.moneteTotali?.moneteP.toString())
        })

    }

    fun updateMonete(){
        mSchedaViewModel.getSingleLiveData().observe(viewLifecycleOwner, Observer {
            val mr : Int = if (moneteRame.text.isNotEmpty()) moneteRame.text.toString().toInt() else 0
            val ma : Int = if (moneteArgento.text.isNotEmpty()) moneteArgento.text.toString().toInt() else 0
            val me : Int = if (moneteElectrum.text.isNotEmpty()) moneteElectrum.text.toString().toInt() else 0
            val mo : Int = if (moneteOro.text.isNotEmpty()) moneteOro.text.toString().toInt() else 0
            val mp : Int = if (monetePlatino.text.isNotEmpty()) monetePlatino.text.toString().toInt() else 0

            val moneteNuove = Money(mr, ma , me, mo, mp)

            mSchedaViewModel.updateScheda(
                Scheda(
                mSchedaViewModel.getSingleLiveData().value!!.id,
                mSchedaViewModel.getSingleLiveData().value!!.Campagnaid,
                mSchedaViewModel.getSingleLiveData().value!!.nomePG,
                mSchedaViewModel.getSingleLiveData().value!!.tipoScheda,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche,
                mSchedaViewModel.getSingleLiveData().value!!.incantatore,
                mSchedaViewModel.getSingleLiveData().value!!.dettagli,
                moneteNuove,
                    mSchedaViewModel.getSingleLiveData().value!!.imgSchedaBitmap)
            )
        })

        Toast.makeText(context, "Modifica avvenuta con successo!", Toast.LENGTH_SHORT).show()


    }

}