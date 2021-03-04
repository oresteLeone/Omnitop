package com.annoyingturtle.omnitop.dndSchedaActivity.dndSchedaFragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.annoyingturtle.omnitop.R
import com.annoyingturtle.omnitop.dndSchedaActivity.DndSchedaActivity
import dndData.entities.Scheda
import dndData.utilData.Dettagli
import dndData.viewModel.SchedaViewModel
import kotlinx.android.synthetic.main.fragment_dnd_dettagli.*
import kotlinx.android.synthetic.main.fragment_dnd_dettagli.view.*
import java.lang.Integer.parseInt
import java.lang.RuntimeException


class DndDettagliFragment : Fragment() {

    var idScheda = -1
    private lateinit var mSchedaViewModel : SchedaViewModel




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dnd_dettagli, container, false)

        /** Inizializzazione scheda*/

        idScheda = activity?.intent?.extras!!.getInt("idScheda")

        mSchedaViewModel = ViewModelProvider(this).get(SchedaViewModel::class.java)
        if (idScheda > -1){
            mSchedaViewModel.getSchedaFromID(idScheda)
        }

        showSchedaData()



        return view


    }

    override fun onStart() {
        super.onStart()

        editInformazioniBase.setOnClickListener(){
                editInformazioniBase.visibility = View.GONE
                editInformazioniBaseSALVA.visibility = View.VISIBLE

                razza_Et.isClickable = true
                razza_Et.isFocusableInTouchMode = true

                classe.isClickable = true
                classe.isFocusableInTouchMode = true

                backGround.isClickable = true
                backGround.isFocusableInTouchMode = true

                nomeGiocatore.isClickable = true
                nomeGiocatore.isFocusableInTouchMode = true

                allineamento.isClickable = true
                allineamento.isFocusableInTouchMode = true

                puntiEsperienza.isClickable = true
                puntiEsperienza.isFocusableInTouchMode = true

                puntiIspirazione.isClickable = true
                puntiIspirazione.isFocusableInTouchMode = true
            }

            editInformazioniBaseSALVA.setOnClickListener(){
                editInformazioniBase.visibility = View.VISIBLE
                editInformazioniBaseSALVA.visibility = View.GONE

                razza_Et.isClickable = false
                razza_Et.isFocusableInTouchMode = false

                classe.isClickable = false
                classe.isFocusableInTouchMode = false

                backGround.isClickable = false
                backGround.isFocusableInTouchMode = false

                nomeGiocatore.isClickable = false
                nomeGiocatore.isFocusableInTouchMode = false

                allineamento.isClickable = false
                allineamento.isFocusableInTouchMode = false

                puntiEsperienza.isClickable = false
                puntiEsperienza.isFocusableInTouchMode = false

                puntiIspirazione.isClickable = false
                puntiIspirazione.isFocusableInTouchMode = false

                updateInfBase()
            }

        editAltreInformazioni.setOnClickListener(){
            editAltreInformazioni.visibility = View.GONE
            editAltreInformazioniSALVA.visibility = View.VISIBLE

            altreInformazioni.isClickable = true
            altreInformazioni.isFocusableInTouchMode = true

        }

        editAltreInformazioniSALVA.setOnClickListener(){

            editAltreInformazioni.visibility = View.VISIBLE
            editAltreInformazioniSALVA.visibility = View.GONE

            altreInformazioni.isClickable = false
            altreInformazioni.isFocusableInTouchMode = false

            updateAltreInfo()

        }

    }

    fun showSchedaData(){
        mSchedaViewModel.getSingleLiveData().observe(viewLifecycleOwner, Observer {
            razza_Et.text = Editable.Factory.getInstance().newEditable(it.dettagli?.razza)
            classe.text = Editable.Factory.getInstance().newEditable(it.dettagli?.classe)
            backGround.text = Editable.Factory.getInstance().newEditable(it.dettagli?.background)
            nomeGiocatore.text = Editable.Factory.getInstance().newEditable(it.dettagli?.nomeGiocatore)
            allineamento.text = Editable.Factory.getInstance().newEditable(it.dettagli?.allineamento)
            puntiEsperienza.text = Editable.Factory.getInstance().newEditable(it.dettagli?.exp.toString())
            puntiIspirazione.text = Editable.Factory.getInstance().newEditable(it.dettagli?.puntiIspirazione.toString())
            altreInformazioni.text = Editable.Factory.getInstance().newEditable(it.dettagli?.altreInformazioni)

        })
    }

    fun updateInfBase(){
        mSchedaViewModel.getSingleLiveData().observe(viewLifecycleOwner, Observer {
        val dettagliNuovi = Dettagli(razza_Et.text.toString(),classe.text.toString(), backGround.text.toString(),nomeGiocatore.text.toString(),allineamento.text.toString(),
            puntiEsperienza.text.toString().toInt(),puntiIspirazione.text.toString().toInt(), mSchedaViewModel.getSingleLiveData().value!!.dettagli?.altreInformazioni)

            mSchedaViewModel.updateScheda(Scheda(
                mSchedaViewModel.getSingleLiveData().value!!.id,
                mSchedaViewModel.getSingleLiveData().value!!.Campagnaid,
                mSchedaViewModel.getSingleLiveData().value!!.nomePG,
                mSchedaViewModel.getSingleLiveData().value!!.tipoScheda,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche,
                mSchedaViewModel.getSingleLiveData().value!!.incantatore,
                dettagliNuovi,
                mSchedaViewModel.getSingleLiveData().value!!.moneteTotali))

        })
        Toast.makeText(context, "Modifica avvenuta con successo!", Toast.LENGTH_SHORT).show()

    }

    fun updateAltreInfo(){

        mSchedaViewModel.getSingleLiveData().observe(viewLifecycleOwner, Observer {
            val dettagliNuovi = Dettagli(mSchedaViewModel.getSingleLiveData().value!!.dettagli?.razza, mSchedaViewModel.getSingleLiveData().value!!.dettagli?.classe,
                mSchedaViewModel.getSingleLiveData().value!!.dettagli?.background, mSchedaViewModel.getSingleLiveData().value!!.dettagli?.nomeGiocatore,
                mSchedaViewModel.getSingleLiveData().value!!.dettagli?.allineamento, mSchedaViewModel.getSingleLiveData().value!!.dettagli?.exp,
                mSchedaViewModel.getSingleLiveData().value!!.dettagli?.puntiIspirazione, altreInformazioni.text.toString())

            mSchedaViewModel.updateScheda(Scheda(
                mSchedaViewModel.getSingleLiveData().value!!.id,
                mSchedaViewModel.getSingleLiveData().value!!.Campagnaid,
                mSchedaViewModel.getSingleLiveData().value!!.nomePG,
                mSchedaViewModel.getSingleLiveData().value!!.tipoScheda,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche,
                mSchedaViewModel.getSingleLiveData().value!!.incantatore,
                dettagliNuovi,
                mSchedaViewModel.getSingleLiveData().value!!.moneteTotali))

        })

        Toast.makeText(context, "Modifica avvenuta con successo!", Toast.LENGTH_SHORT).show()


    }


}