package com.annoyingturtle.omnitop.dndHomeActivity.dndHomeFragments.support

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.annoyingturtle.omnitop.R
import dndData.RuoloGiocatore
import dndData.entities.Campagna
import dndData.viewModel.CampagnaViewModel
import kotlinx.android.synthetic.main.fragment_dnd_add_campagna.*
import kotlinx.android.synthetic.main.fragment_dnd_add_campagna.view.*

class DndAddCampagnaFragment : Fragment() {

    private lateinit var mCampagnaViewModel: CampagnaViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dnd_add_campagna, container, false)

        mCampagnaViewModel = ViewModelProvider(this).get(CampagnaViewModel::class.java)
        view.ButtonInsertDataAddCampagna.setOnClickListener {
            insertCampagnaDataToDatabase()
        }

        return view

    }

    private fun insertCampagnaDataToDatabase() {

        val titoloCampagna = titoloCam_et.text.toString()
        val ruoloCampagna = if (checkBoxDM.isChecked) RuoloGiocatore.DM
                            else RuoloGiocatore.PG
        val descrizioneCampagna = descrizioneCam_et.text?.toString()

        if(!inputcheck(titoloCampagna)){
                val Campagna = Campagna(0, titoloCampagna, ruoloCampagna, descrizioneCampagna)
                mCampagnaViewModel.addCampagna(Campagna)
                Toast.makeText(requireContext(), "Campagna aggiunta con successo!", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
        }
        else{
            Toast.makeText(requireContext(), "Titolo Campagna non può essere vuoto!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputcheck(titoloCampagna: String?): Boolean{

        return (TextUtils.isEmpty(titoloCampagna))

    }
}