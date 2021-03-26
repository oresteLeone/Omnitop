package com.annoyingturtle.omnitop.dndCampagnaHomeActivity.dndCampagnaFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.annoyingturtle.omnitop.R
import kotlinx.android.synthetic.main.fragment_dnd_campagna_compendium.*


class DndCampagnaCompendiumFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dnd_campagna_compendium, container, false)
    }

    override fun onStart() {
        super.onStart()

        imageViewEquipaggiamento.setOnClickListener(){
            Toast.makeText(requireContext(), "Pulsante Equipaggiamento", Toast.LENGTH_SHORT).show()
        }

        imageViewBestiario.setOnClickListener(){
            Toast.makeText(requireContext(), "Pulsante Bestiario", Toast.LENGTH_SHORT).show()
        }

        imageViewIncantesimi.setOnClickListener(){
            Toast.makeText(requireContext(), "Pulsante Incantesimi", Toast.LENGTH_SHORT).show()
        }
    }


}