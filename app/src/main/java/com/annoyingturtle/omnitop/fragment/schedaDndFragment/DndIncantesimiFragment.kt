package com.annoyingturtle.omnitop.fragment.schedaDndFragment

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.annoyingturtle.omnitop.R
import dndData.viewModel.SchedaViewModel
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_dnd_incantesimi.*


class DndIncantesimiFragment : Fragment() {

    var idScheda = -1
    private lateinit var mSchedaViewModel : SchedaViewModel
    var listaEspansa = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dnd_incantesimi, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onStart() {
        super.onStart()

        setListner()

        idScheda = activity?.intent?.extras!!.getInt("idScheda")

        mSchedaViewModel = ViewModelProvider(this).get(SchedaViewModel::class.java)
        if (idScheda > -1){
            mSchedaViewModel.getSchedaFromID(idScheda)
            showSchedaData()



        }



    }

    fun showSchedaData(){
        mSchedaViewModel.getSingleLiveData().observe(this, Observer {
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

    }


}