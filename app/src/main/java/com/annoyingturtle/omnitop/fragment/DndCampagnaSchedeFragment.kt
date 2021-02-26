package com.annoyingturtle.omnitop.fragment

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.annoyingturtle.omnitop.DndCampagnaHome
import com.annoyingturtle.omnitop.R
import kotlinx.android.synthetic.main.fragment_dnd_campagna_schede.view.*


class DndCampagnaSchedeFragment : Fragment(), SchedeCampagnaAdapter.onItemClickListner {

    lateinit var adapter: SchedeCampagnaAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_dnd_campagna_schede, container, false)
        var campagnaHome: DndCampagnaHome = requireActivity() as DndCampagnaHome

        adapter = SchedeCampagnaAdapter(this)
        val recyclerView = view.SchedeCampagnaRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(),2, LinearLayoutManager.VERTICAL , false)
        campagnaHome.mCampagnaViewModel.readAllSchedeFromCampagnaID.observe(viewLifecycleOwner, Observer { scheda ->
            adapter.setDataSchede(scheda)

        })

        return view
    }

    override fun onItemClick(position: Int) {

       var idItem = adapter.getItemID(position)
        /* var bundle = Bundle()
         bundle.putString("goto", requireContext()::class.java.simpleName.toString())    prima di put extra putExtras(bundle)*//*
        startActivity(
            Intent(context, DndCampagnaHome::class.java).putExtra("idItem", idItem )
        )*/
        Toast.makeText(requireContext() , "scheda $idItem", Toast.LENGTH_SHORT).show()
    }


}