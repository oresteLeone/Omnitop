package com.annoyingturtle.omnitop.fragment.schedaDndFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.constraintlayout.widget.ConstraintSet
import com.annoyingturtle.omnitop.R
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_dnd_incantesimi.*


class DndIncantesimiFragment : Fragment() {

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