package com.annoyingturtle.omnitop.dndSchedaActivity.dndSchedaFragments

import android.os.Bundle
import android.text.Editable
import android.util.Log
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
import dndData.utilData.Incantatore
import dndData.viewModel.SchedaViewModel
import kotlinx.android.synthetic.main.fragment_dnd_incantesimi.*
import kotlinx.android.synthetic.main.fragment_personaggio.*
import java.lang.Exception
import kotlin.math.log


class DndIncantesimiFragment : Fragment() {

    var idScheda = -1
    private lateinit var mSchedaViewModel : SchedaViewModel
    var listaEspansa = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dnd_incantesimi, container, false)

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

        setListner()
    }

    fun showSchedaData(){
        mSchedaViewModel.getSingleLiveData().observe(viewLifecycleOwner, Observer {
            classeIncantatore.text= Editable.Factory.getInstance().newEditable(it.incantatore?.incantatoreClasse)
            caratteristicaIncantatore.text= Editable.Factory.getInstance().newEditable(it.incantatore?.carIncantatore)
            CDIncantesimi.text= Editable.Factory.getInstance().newEditable(it.incantatore?.cd.toString())
            TPCIncantesimi.text= Editable.Factory.getInstance().newEditable(it.incantatore?.bonus.toString())

            slotLv1Attuali.text= Editable.Factory.getInstance().newEditable(it.incantatore?.slotLVL1.toString())
            slotLv1Totali.text= Editable.Factory.getInstance().newEditable(it.incantatore?.slotLVL1MAX.toString())

            slotLv2Attuali.text= Editable.Factory.getInstance().newEditable(it.incantatore?.slotLVL2.toString())
            slotLv2Totali.text= Editable.Factory.getInstance().newEditable(it.incantatore?.slotLVL2MAX.toString())

            slotLv3Attuali.text= Editable.Factory.getInstance().newEditable(it.incantatore?.slotLVL3.toString())
            slotLv3Totali.text= Editable.Factory.getInstance().newEditable(it.incantatore?.slotLVL3MAX.toString())

            slotLv4Attuali.text= Editable.Factory.getInstance().newEditable(it.incantatore?.slotLVL4.toString())
            slotLv4Totali.text= Editable.Factory.getInstance().newEditable(it.incantatore?.slotLVL4MAX.toString())

            slotLv5Attuali.text= Editable.Factory.getInstance().newEditable(it.incantatore?.slotLVL5.toString())
            slotLv5Totali.text= Editable.Factory.getInstance().newEditable(it.incantatore?.slotLVL5MAX.toString())

            slotLv6Attuali.text= Editable.Factory.getInstance().newEditable(it.incantatore?.slotLVL6.toString())
            slotLv6Totali.text= Editable.Factory.getInstance().newEditable(it.incantatore?.slotLVL6MAX.toString())

            slotLv7Attuali.text= Editable.Factory.getInstance().newEditable(it.incantatore?.slotLVL7.toString())
            slotLv7Totali.text= Editable.Factory.getInstance().newEditable(it.incantatore?.slotLVL7MAX.toString())

            slotLv8Attuali.text= Editable.Factory.getInstance().newEditable(it.incantatore?.slotLVL8.toString())
            slotLv8Totali.text= Editable.Factory.getInstance().newEditable(it.incantatore?.slotLVL8MAX.toString())

            slotLv9Attuali.text= Editable.Factory.getInstance().newEditable(it.incantatore?.slotLVL9.toString())
            slotLv9Totali.text= Editable.Factory.getInstance().newEditable(it.incantatore?.slotLVL9MAX.toString())
        })
    }

    fun setListner(){
        btnShow.setOnClickListener(){
            if (!listaEspansa){
                constraintLayoutLV2.visibility = View.VISIBLE
                constraintLayoutLV3.visibility = View.VISIBLE
                constraintLayoutLV4.visibility = View.VISIBLE
                constraintLayoutLV5.visibility = View.VISIBLE
                constraintLayoutLV6.visibility = View.VISIBLE
                constraintLayoutLV7.visibility = View.VISIBLE
                constraintLayoutLV8.visibility = View.VISIBLE
                constraintLayoutLV9.visibility = View.VISIBLE

                btnShow.text = getString(R.string.nascondiInc)

                listaEspansa = true
            }
            else
            {
                constraintLayoutLV2.visibility = View.GONE
                constraintLayoutLV3.visibility = View.GONE
                constraintLayoutLV4.visibility = View.GONE
                constraintLayoutLV5.visibility = View.GONE
                constraintLayoutLV6.visibility = View.GONE
                constraintLayoutLV7.visibility = View.GONE
                constraintLayoutLV8.visibility = View.GONE
                constraintLayoutLV9.visibility = View.GONE

                btnShow.text = getString(R.string.mostraInc)

                listaEspansa = false
            }
        }

        editClasseIncantatore.setOnClickListener(){
            editClasseIncantatore.visibility = View.GONE
            editClasseIncantatoreSALVA.visibility = View.VISIBLE

            classeIncantatore.isFocusableInTouchMode = true
            classeIncantatore.isClickable = true

            caratteristicaIncantatore.isFocusableInTouchMode = true
            caratteristicaIncantatore.isClickable = true

            CDIncantesimi.isFocusableInTouchMode= true
            CDIncantesimi.isClickable = true

            TPCIncantesimi.isFocusableInTouchMode = true
            TPCIncantesimi.isClickable = true
        }

        editClasseIncantatoreSALVA.setOnClickListener(){

            editClasseIncantatore.visibility = View.VISIBLE
            editClasseIncantatoreSALVA.visibility = View.GONE

            classeIncantatore.isFocusableInTouchMode = false
            classeIncantatore.isClickable = false

            caratteristicaIncantatore.isFocusableInTouchMode = false
            caratteristicaIncantatore.isClickable = false

            CDIncantesimi.isFocusableInTouchMode= false
            CDIncantesimi.isClickable = false

            TPCIncantesimi.isFocusableInTouchMode = false
            TPCIncantesimi.isClickable = false

            updateDatiIncantatore()
            (activity as DndSchedaActivity).chiudiKeyboard()

        }

        editLv1.setOnClickListener(){
            editLv1.visibility = View.GONE
            editLv1SALVA.visibility = View.VISIBLE

            slotLv1Attuali.isClickable = true
            slotLv1Attuali.isFocusableInTouchMode = true

            slotLv1Totali.isClickable = true
            slotLv1Totali.isFocusableInTouchMode = true

        }

        editLv2.setOnClickListener(){
            editLv2.visibility = View.GONE
            editLv2SALVA.visibility = View.VISIBLE

            slotLv2Attuali.isClickable = true
            slotLv2Attuali.isFocusableInTouchMode = true

            slotLv2Totali.isClickable = true
            slotLv2Totali.isFocusableInTouchMode = true

        }

        editLv3.setOnClickListener(){
            editLv3.visibility = View.GONE
            editLv3SALVA.visibility = View.VISIBLE

            slotLv3Attuali.isClickable = true
            slotLv3Attuali.isFocusableInTouchMode = true

            slotLv3Totali.isClickable = true
            slotLv3Totali.isFocusableInTouchMode = true

        }

        editLv4.setOnClickListener(){
            editLv4.visibility = View.GONE
            editLv4SALVA.visibility = View.VISIBLE

            slotLv4Attuali.isClickable = true
            slotLv4Attuali.isFocusableInTouchMode = true

            slotLv4Totali.isClickable = true
            slotLv4Totali.isFocusableInTouchMode = true

        }

        editLv5.setOnClickListener(){
            editLv5.visibility = View.GONE
            editLv5SALVA.visibility = View.VISIBLE

            slotLv5Attuali.isClickable = true
            slotLv5Attuali.isFocusableInTouchMode = true

            slotLv5Totali.isClickable = true
            slotLv5Totali.isFocusableInTouchMode = true

        }

        editLv6.setOnClickListener(){
            editLv6.visibility = View.GONE
            editLv6SALVA.visibility = View.VISIBLE

            slotLv6Attuali.isClickable = true
            slotLv6Attuali.isFocusableInTouchMode = true

            slotLv6Totali.isClickable = true
            slotLv6Totali.isFocusableInTouchMode = true

        }

        editLv7.setOnClickListener(){
            editLv7.visibility = View.GONE
            editLv7SALVA.visibility = View.VISIBLE

            slotLv7Attuali.isClickable = true
            slotLv7Attuali.isFocusableInTouchMode = true

            slotLv7Totali.isClickable = true
            slotLv7Totali.isFocusableInTouchMode = true

        }

        editLv8.setOnClickListener(){
            editLv8.visibility = View.GONE
            editLv8SALVA.visibility = View.VISIBLE

            slotLv8Attuali.isClickable = true
            slotLv8Attuali.isFocusableInTouchMode = true

            slotLv8Totali.isClickable = true
            slotLv8Totali.isFocusableInTouchMode = true

        }

        editLv9.setOnClickListener(){
            editLv9.visibility = View.GONE
            editLv9SALVA.visibility = View.VISIBLE

            slotLv9Attuali.isClickable = true
            slotLv9Attuali.isFocusableInTouchMode = true

            slotLv9Totali.isClickable = true
            slotLv9Totali.isFocusableInTouchMode = true

        }

        editLv1SALVA.setOnClickListener(){
            editLv1.visibility = View.VISIBLE
            editLv1SALVA.visibility = View.GONE

            slotLv1Attuali.isClickable = false
            slotLv1Attuali.isFocusableInTouchMode = false

            slotLv1Totali.isClickable = false
            slotLv1Totali.isFocusableInTouchMode = false

            updateLivello1()
            (activity as DndSchedaActivity).chiudiKeyboard()
        }

        editLv2SALVA.setOnClickListener(){
            editLv2.visibility = View.VISIBLE
            editLv2SALVA.visibility = View.GONE

            slotLv2Attuali.isClickable = false
            slotLv2Attuali.isFocusableInTouchMode = false

            slotLv2Totali.isClickable = false
            slotLv2Totali.isFocusableInTouchMode = false

            updateLivello2()
            (activity as DndSchedaActivity).chiudiKeyboard()
        }

        editLv3SALVA.setOnClickListener(){
            editLv3.visibility = View.VISIBLE
            editLv3SALVA.visibility = View.GONE

            slotLv3Attuali.isClickable = false
            slotLv3Attuali.isFocusableInTouchMode = false

            slotLv3Totali.isClickable = false
            slotLv3Totali.isFocusableInTouchMode = false

            updateLivello3()
            (activity as DndSchedaActivity).chiudiKeyboard()
        }

        editLv4SALVA.setOnClickListener(){
            editLv4.visibility = View.VISIBLE
            editLv4SALVA.visibility = View.GONE

            slotLv4Attuali.isClickable = false
            slotLv4Attuali.isFocusableInTouchMode = false

            slotLv4Totali.isClickable = false
            slotLv4Totali.isFocusableInTouchMode = false

            updateLivello4()
            (activity as DndSchedaActivity).chiudiKeyboard()
        }

        editLv5SALVA.setOnClickListener(){
            editLv5.visibility = View.VISIBLE
            editLv5SALVA.visibility = View.GONE

            slotLv5Attuali.isClickable = false
            slotLv5Attuali.isFocusableInTouchMode = false

            slotLv5Totali.isClickable = false
            slotLv5Totali.isFocusableInTouchMode = false

            updateLivello5()
            (activity as DndSchedaActivity).chiudiKeyboard()
        }

        editLv6SALVA.setOnClickListener(){
            editLv6.visibility = View.VISIBLE
            editLv6SALVA.visibility = View.GONE

            slotLv6Attuali.isClickable = false
            slotLv6Attuali.isFocusableInTouchMode = false

            slotLv6Totali.isClickable = false
            slotLv6Totali.isFocusableInTouchMode = false

            updateLivello6()
            (activity as DndSchedaActivity).chiudiKeyboard()
        }

        editLv7SALVA.setOnClickListener(){
            editLv7.visibility = View.VISIBLE
            editLv7SALVA.visibility = View.GONE

            slotLv7Attuali.isClickable = false
            slotLv7Attuali.isFocusableInTouchMode = false

            slotLv7Totali.isClickable = false
            slotLv7Totali.isFocusableInTouchMode = false

            updateLivello7()
            (activity as DndSchedaActivity).chiudiKeyboard()
        }

        editLv8SALVA.setOnClickListener(){
            editLv8.visibility = View.VISIBLE
            editLv8SALVA.visibility = View.GONE

            slotLv8Attuali.isClickable = false
            slotLv8Attuali.isFocusableInTouchMode = false

            slotLv8Totali.isClickable = false
            slotLv8Totali.isFocusableInTouchMode = false

            updateLivello8()
            (activity as DndSchedaActivity).chiudiKeyboard()
        }

        editLv9SALVA.setOnClickListener(){
            editLv9.visibility = View.VISIBLE
            editLv9SALVA.visibility = View.GONE

            slotLv9Attuali.isClickable = false
            slotLv9Attuali.isFocusableInTouchMode = false

            slotLv9Totali.isClickable = false
            slotLv9Totali.isFocusableInTouchMode = false

            updateLivello9()
            (activity as DndSchedaActivity).chiudiKeyboard()
        }

    }

    fun updateDatiIncantatore(){



            mSchedaViewModel.getSingleLiveData().observe(this, Observer {
                val cd : Int = if (CDIncantesimi.text.isEmpty()) 0 else CDIncantesimi.text.toString().toInt()
                val tpc : Int = if (TPCIncantesimi.text.isEmpty()) 0 else TPCIncantesimi.text.toString().toInt()

                val nuovoIncantatore = Incantatore(
                    classeIncantatore.text.toString(), caratteristicaIncantatore.text.toString(), cd, tpc,
                    mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL1MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL1,
                    mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL2MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL2,
                    mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL3MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL3,
                    mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL4MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL4,
                    mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL5MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL5,
                    mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL6MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL6,
                    mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL7MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL7,
                    mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL8MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL8,
                    mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL9MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL9
                )


                mSchedaViewModel.updateScheda(Scheda(
                    mSchedaViewModel.getSingleLiveData().value!!.id,
                    mSchedaViewModel.getSingleLiveData().value!!.Campagnaid,
                    mSchedaViewModel.getSingleLiveData().value!!.nomePG,
                    mSchedaViewModel.getSingleLiveData().value!!.tipoScheda,
                    mSchedaViewModel.getSingleLiveData().value!!.statistiche,
                    nuovoIncantatore,
                    mSchedaViewModel.getSingleLiveData().value!!.dettagli,
                    mSchedaViewModel.getSingleLiveData().value!!.moneteTotali,
                    mSchedaViewModel.getSingleLiveData().value!!.imgSchedaBitmap
                    ))
            })

        Toast.makeText(context, "Modifica avvenuta con successo!", Toast.LENGTH_SHORT).show()

    }

    fun updateLivello1(){

        val totali : Int = if (slotLv1Totali.text.isNotEmpty()) slotLv1Totali.text.toString().toInt() else 0
        val attuali : Int = if (slotLv1Attuali.text.isNotEmpty()) slotLv1Attuali.text.toString().toInt() else 0

        mSchedaViewModel.getSingleLiveData().observe(viewLifecycleOwner, Observer {
            val nuovoIncantatore = Incantatore(
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.incantatoreClasse, mSchedaViewModel.getSingleLiveData().value?.incantatore?.carIncantatore,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.cd, mSchedaViewModel.getSingleLiveData().value?.incantatore?.bonus,
                totali, attuali,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL2MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL2,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL3MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL3,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL4MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL4,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL5MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL5,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL6MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL6,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL7MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL7,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL8MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL8,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL9MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL9
            )

            mSchedaViewModel.updateScheda(Scheda(
                mSchedaViewModel.getSingleLiveData().value!!.id,
                mSchedaViewModel.getSingleLiveData().value!!.Campagnaid,
                mSchedaViewModel.getSingleLiveData().value!!.nomePG,
                mSchedaViewModel.getSingleLiveData().value!!.tipoScheda,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche,
                nuovoIncantatore,
                mSchedaViewModel.getSingleLiveData().value!!.dettagli,
                mSchedaViewModel.getSingleLiveData().value!!.moneteTotali,
                mSchedaViewModel.getSingleLiveData().value!!.imgSchedaBitmap
            ))
        })

        Toast.makeText(context, "Modifica avvenuta con successo!", Toast.LENGTH_SHORT).show()


    }

    fun updateLivello2(){

        val totali : Int = if (slotLv2Totali.text.isNotEmpty() ) slotLv2Totali.text.toString().toInt() else 0
        val attuali : Int = if (slotLv2Attuali.text.isNotEmpty()) slotLv2Attuali.text.toString().toInt() else 0

        mSchedaViewModel.getSingleLiveData().observe(viewLifecycleOwner, Observer {
            val nuovoIncantatore = Incantatore(
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.incantatoreClasse, mSchedaViewModel.getSingleLiveData().value?.incantatore?.carIncantatore,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.cd, mSchedaViewModel.getSingleLiveData().value?.incantatore?.bonus,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL1MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL1,
                totali, attuali,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL3MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL3,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL4MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL4,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL5MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL5,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL6MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL6,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL7MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL7,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL8MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL8,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL9MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL9
            )

            mSchedaViewModel.updateScheda(Scheda(
                mSchedaViewModel.getSingleLiveData().value!!.id,
                mSchedaViewModel.getSingleLiveData().value!!.Campagnaid,
                mSchedaViewModel.getSingleLiveData().value!!.nomePG,
                mSchedaViewModel.getSingleLiveData().value!!.tipoScheda,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche,
                nuovoIncantatore,
                mSchedaViewModel.getSingleLiveData().value!!.dettagli,
                mSchedaViewModel.getSingleLiveData().value!!.moneteTotali,
                mSchedaViewModel.getSingleLiveData().value!!.imgSchedaBitmap
            ))
        })

        Toast.makeText(context, "Modifica avvenuta con successo!", Toast.LENGTH_SHORT).show()


    }

    fun updateLivello3(){

        val totali : Int = if (slotLv3Totali.text.isNotEmpty()) slotLv3Totali.text.toString().toInt() else 0
        val attuali : Int = if (slotLv3Attuali.text.isNotEmpty()) slotLv3Attuali.text.toString().toInt() else 0

        mSchedaViewModel.getSingleLiveData().observe(viewLifecycleOwner, Observer {
            val nuovoIncantatore = Incantatore(
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.incantatoreClasse, mSchedaViewModel.getSingleLiveData().value?.incantatore?.carIncantatore,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.cd, mSchedaViewModel.getSingleLiveData().value?.incantatore?.bonus,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL1MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL1,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL2MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL2,
                totali, attuali,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL4MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL4,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL5MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL5,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL6MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL6,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL7MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL7,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL8MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL8,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL9MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL9
            )

            mSchedaViewModel.updateScheda(Scheda(
                mSchedaViewModel.getSingleLiveData().value!!.id,
                mSchedaViewModel.getSingleLiveData().value!!.Campagnaid,
                mSchedaViewModel.getSingleLiveData().value!!.nomePG,
                mSchedaViewModel.getSingleLiveData().value!!.tipoScheda,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche,
                nuovoIncantatore,
                mSchedaViewModel.getSingleLiveData().value!!.dettagli,
                mSchedaViewModel.getSingleLiveData().value!!.moneteTotali,
                mSchedaViewModel.getSingleLiveData().value!!.imgSchedaBitmap
            ))
        })

        Toast.makeText(context, "Modifica avvenuta con successo!", Toast.LENGTH_SHORT).show()


    }

    fun updateLivello4(){

        val totali : Int = if (slotLv4Totali.text.isNotEmpty()) slotLv4Totali.text.toString().toInt() else 0
        val attuali : Int = if (slotLv4Attuali.text.isNotEmpty()) slotLv4Attuali.text.toString().toInt() else 0

        mSchedaViewModel.getSingleLiveData().observe(viewLifecycleOwner, Observer {
            val nuovoIncantatore = Incantatore(
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.incantatoreClasse, mSchedaViewModel.getSingleLiveData().value?.incantatore?.carIncantatore,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.cd, mSchedaViewModel.getSingleLiveData().value?.incantatore?.bonus,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL1MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL1,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL2MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL2,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL3MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL3,
                totali, attuali,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL5MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL5,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL6MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL6,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL7MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL7,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL8MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL8,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL9MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL9
            )

            mSchedaViewModel.updateScheda(Scheda(
                mSchedaViewModel.getSingleLiveData().value!!.id,
                mSchedaViewModel.getSingleLiveData().value!!.Campagnaid,
                mSchedaViewModel.getSingleLiveData().value!!.nomePG,
                mSchedaViewModel.getSingleLiveData().value!!.tipoScheda,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche,
                nuovoIncantatore,
                mSchedaViewModel.getSingleLiveData().value!!.dettagli,
                mSchedaViewModel.getSingleLiveData().value!!.moneteTotali,
                mSchedaViewModel.getSingleLiveData().value!!.imgSchedaBitmap
            ))
        })

        Toast.makeText(context, "Modifica avvenuta con successo!", Toast.LENGTH_SHORT).show()


    }

    fun updateLivello5(){

        val totali : Int = if (slotLv5Totali.text.isNotEmpty()) slotLv5Totali.text.toString().toInt() else 0
        val attuali : Int = if (slotLv5Attuali.text.isNotEmpty()) slotLv5Attuali.text.toString().toInt() else 0

        mSchedaViewModel.getSingleLiveData().observe(viewLifecycleOwner, Observer {
            val nuovoIncantatore = Incantatore(
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.incantatoreClasse, mSchedaViewModel.getSingleLiveData().value?.incantatore?.carIncantatore,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.cd, mSchedaViewModel.getSingleLiveData().value?.incantatore?.bonus,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL1MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL1,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL2MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL2,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL3MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL3,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL4MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL4,
                totali, attuali,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL6MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL6,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL7MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL7,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL8MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL8,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL9MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL9
            )

            mSchedaViewModel.updateScheda(Scheda(
                mSchedaViewModel.getSingleLiveData().value!!.id,
                mSchedaViewModel.getSingleLiveData().value!!.Campagnaid,
                mSchedaViewModel.getSingleLiveData().value!!.nomePG,
                mSchedaViewModel.getSingleLiveData().value!!.tipoScheda,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche,
                nuovoIncantatore,
                mSchedaViewModel.getSingleLiveData().value!!.dettagli,
                mSchedaViewModel.getSingleLiveData().value!!.moneteTotali,
                mSchedaViewModel.getSingleLiveData().value!!.imgSchedaBitmap
            ))
        })

        Toast.makeText(context, "Modifica avvenuta con successo!", Toast.LENGTH_SHORT).show()


    }

    fun updateLivello6(){

        val totali : Int = if (slotLv6Totali.text.isNotEmpty()) slotLv6Totali.text.toString().toInt() else 0
        val attuali : Int = if (slotLv6Attuali.text.isNotEmpty()) slotLv6Attuali.text.toString().toInt() else 0

        mSchedaViewModel.getSingleLiveData().observe(viewLifecycleOwner, Observer {
            val nuovoIncantatore = Incantatore(
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.incantatoreClasse, mSchedaViewModel.getSingleLiveData().value?.incantatore?.carIncantatore,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.cd, mSchedaViewModel.getSingleLiveData().value?.incantatore?.bonus,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL1MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL1,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL2MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL2,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL3MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL3,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL4MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL4,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL5MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL5,
                totali, attuali,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL7MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL7,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL8MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL8,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL9MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL9
            )

            mSchedaViewModel.updateScheda(Scheda(
                mSchedaViewModel.getSingleLiveData().value!!.id,
                mSchedaViewModel.getSingleLiveData().value!!.Campagnaid,
                mSchedaViewModel.getSingleLiveData().value!!.nomePG,
                mSchedaViewModel.getSingleLiveData().value!!.tipoScheda,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche,
                nuovoIncantatore,
                mSchedaViewModel.getSingleLiveData().value!!.dettagli,
                mSchedaViewModel.getSingleLiveData().value!!.moneteTotali,
                mSchedaViewModel.getSingleLiveData().value!!.imgSchedaBitmap
            ))
        })

        Toast.makeText(context, "Modifica avvenuta con successo!", Toast.LENGTH_SHORT).show()


    }

    fun updateLivello7(){

        val totali : Int = if (slotLv7Totali.text.isNotEmpty()) slotLv7Totali.text.toString().toInt() else 0
        val attuali : Int = if (slotLv7Attuali.text.isNotEmpty()) slotLv7Attuali.text.toString().toInt() else 0

        mSchedaViewModel.getSingleLiveData().observe(viewLifecycleOwner, Observer {
            val nuovoIncantatore = Incantatore(
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.incantatoreClasse, mSchedaViewModel.getSingleLiveData().value?.incantatore?.carIncantatore,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.cd, mSchedaViewModel.getSingleLiveData().value?.incantatore?.bonus,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL1MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL1,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL2MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL2,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL3MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL3,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL4MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL4,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL5MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL5,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL6MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL6,
                totali, attuali,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL8MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL8,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL9MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL9
            )

            mSchedaViewModel.updateScheda(Scheda(
                mSchedaViewModel.getSingleLiveData().value!!.id,
                mSchedaViewModel.getSingleLiveData().value!!.Campagnaid,
                mSchedaViewModel.getSingleLiveData().value!!.nomePG,
                mSchedaViewModel.getSingleLiveData().value!!.tipoScheda,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche,
                nuovoIncantatore,
                mSchedaViewModel.getSingleLiveData().value!!.dettagli,
                mSchedaViewModel.getSingleLiveData().value!!.moneteTotali,
                mSchedaViewModel.getSingleLiveData().value!!.imgSchedaBitmap
            ))
        })

        Toast.makeText(context, "Modifica avvenuta con successo!", Toast.LENGTH_SHORT).show()


    }

    fun updateLivello8(){

        val totali : Int = if (slotLv8Totali.text.isNotEmpty()) slotLv8Totali.text.toString().toInt() else 0
        val attuali : Int = if (slotLv8Attuali.text.isNotEmpty()) slotLv8Attuali.text.toString().toInt() else 0

        mSchedaViewModel.getSingleLiveData().observe(viewLifecycleOwner, Observer {
            val nuovoIncantatore = Incantatore(
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.incantatoreClasse, mSchedaViewModel.getSingleLiveData().value?.incantatore?.carIncantatore,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.cd, mSchedaViewModel.getSingleLiveData().value?.incantatore?.bonus,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL1MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL1,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL2MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL2,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL3MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL3,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL4MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL4,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL5MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL5,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL6MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL6,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL7MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL7,
                totali, attuali,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL9MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL9
            )

            mSchedaViewModel.updateScheda(Scheda(
                mSchedaViewModel.getSingleLiveData().value!!.id,
                mSchedaViewModel.getSingleLiveData().value!!.Campagnaid,
                mSchedaViewModel.getSingleLiveData().value!!.nomePG,
                mSchedaViewModel.getSingleLiveData().value!!.tipoScheda,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche,
                nuovoIncantatore,
                mSchedaViewModel.getSingleLiveData().value!!.dettagli,
                mSchedaViewModel.getSingleLiveData().value!!.moneteTotali,
                mSchedaViewModel.getSingleLiveData().value!!.imgSchedaBitmap
            ))
        })

        Toast.makeText(context, "Modifica avvenuta con successo!", Toast.LENGTH_SHORT).show()


    }

    fun updateLivello9(){

        val totali : Int = if (slotLv9Totali.text.isNotEmpty()) slotLv9Totali.text.toString().toInt() else 0
        val attuali : Int = if (slotLv9Attuali.text.isNotEmpty()) slotLv9Attuali.text.toString().toInt() else 0

        mSchedaViewModel.getSingleLiveData().observe(viewLifecycleOwner, Observer {
            val nuovoIncantatore = Incantatore(
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.incantatoreClasse, mSchedaViewModel.getSingleLiveData().value?.incantatore?.carIncantatore,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.cd, mSchedaViewModel.getSingleLiveData().value?.incantatore?.bonus,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL1MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL1,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL2MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL2,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL3MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL3,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL4MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL4,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL5MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL5,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL6MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL6,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL7MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL7,
                mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL8MAX, mSchedaViewModel.getSingleLiveData().value?.incantatore?.slotLVL8,
                totali, attuali
            )

            mSchedaViewModel.updateScheda(Scheda(
                mSchedaViewModel.getSingleLiveData().value!!.id,
                mSchedaViewModel.getSingleLiveData().value!!.Campagnaid,
                mSchedaViewModel.getSingleLiveData().value!!.nomePG,
                mSchedaViewModel.getSingleLiveData().value!!.tipoScheda,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche,
                nuovoIncantatore,
                mSchedaViewModel.getSingleLiveData().value!!.dettagli,
                mSchedaViewModel.getSingleLiveData().value!!.moneteTotali,
                mSchedaViewModel.getSingleLiveData().value!!.imgSchedaBitmap
            ))
        })

        Toast.makeText(context, "Modifica avvenuta con successo!", Toast.LENGTH_SHORT).show()


    }

}