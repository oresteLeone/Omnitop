package com.annoyingturtle.omnitop.fragment.dndHomeFragments.dndRecenti

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.annoyingturtle.omnitop.DndCampagnaHome
import com.annoyingturtle.omnitop.R
import com.annoyingturtle.omnitop.fragment.dndHomeFragments.support.ListaCampagnaAdapter
import dndData.viewModel.CampagnaViewModel
import kotlinx.android.synthetic.main.fragment_dnd_recenti.view.*

class DndRecentiFragment : Fragment(), ListaCampagnaAdapter.onItemClickListner  {

    private lateinit var mCampagnaViewModel: CampagnaViewModel
    lateinit var adapter: ListaCampagnaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dnd_recenti, container, false)

        adapter = ListaCampagnaAdapter(this)
        val recyclerView = view.recyclerViewDndCampagnaRecenti
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mCampagnaViewModel = ViewModelProvider(this).get(CampagnaViewModel::class.java)
        mCampagnaViewModel.readAllData.observe(viewLifecycleOwner, Observer { campagna ->
            adapter.setDataCampagna(campagna)
        })

        return view
    }

    override fun onItemClick(position: Int) {

        /* var bundle = Bundle()
         bundle.putString("goto", requireContext()::class.java.simpleName.toString())    prima di put extra putExtras(bundle)*/
        startActivity(
            Intent(context, DndCampagnaHome::class.java).putExtra("idItem", adapter.getItemID(position))
        )

    }

}

