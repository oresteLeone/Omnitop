package com.annoyingturtle.omnitop.dndCampagnaHomeActivity.dndCampagnaFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.annoyingturtle.omnitop.R
import com.annoyingturtle.omnitop.dndCampagnaHomeActivity.DndCampagnaHome
import com.annoyingturtle.omnitop.dndCampagnaHomeActivity.DndCampagnaModificaNota
import com.annoyingturtle.omnitop.dndSchedaActivity.DndSchedaActivity
import com.annoyingturtle.omnitop.noteActivity.ModificaNota
import kotlinx.android.synthetic.main.fragment_dnd_camapagna_note.view.*
import kotlinx.android.synthetic.main.fragment_dnd_campagna_schede.view.*

class DndCamapagnaNoteFragment : Fragment(), NotesCampagnaAdapter.onItemClickListner {


    lateinit var adapter: NotesCampagnaAdapter
    lateinit var campagnaHome: DndCampagnaHome

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_dnd_camapagna_note, container, false)
        campagnaHome = requireActivity() as DndCampagnaHome

        adapter = NotesCampagnaAdapter(this)
        val recyclerView = view.NoteCampagnaRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(),2, LinearLayoutManager.VERTICAL , false)
       campagnaHome.mCampagnaViewModel.getListaLiveDataNote().observe(viewLifecycleOwner, Observer { note ->
            adapter?.setDataNote(note)

        })

        return view
    }

    override fun onItemClick(position: Int) {

        campagnaHome.extras.putInt("idNota", adapter.getItemID(position))
        campagnaHome.extras.putInt("idCampagna", campagnaHome.idCampagna)
        startActivity(Intent(context, DndCampagnaModificaNota::class.java).putExtras(campagnaHome.extras))

    }
}