package com.annoyingturtle.omnitop.fragment.dndRecenti

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.annoyingturtle.omnitop.R
import dndData.viewModel.CampagnaViewModel
import kotlinx.android.synthetic.main.fragment_dnd_recenti.view.*

class DndRecentiFragment : Fragment() {

    private lateinit var mCampagnaViewModel: CampagnaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dnd_recenti, container, false)

        val adapter = ListaCampagnaRecentiAdapter()
        val recyclerView = view.recyclerViewDndCampagnaRecenti
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mCampagnaViewModel = ViewModelProvider(this).get(CampagnaViewModel::class.java)
        mCampagnaViewModel.readAllData.observe(viewLifecycleOwner, Observer { campagna ->
            adapter.setDataCampagna(campagna)
        })

        return view
    }
}

