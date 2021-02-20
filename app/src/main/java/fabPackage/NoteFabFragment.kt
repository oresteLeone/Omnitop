package fabPackage

import android.content.Context
import android.content.Intent
import android.os.BaseBundle
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.annoyingturtle.omnitop.HomeActivity
import com.annoyingturtle.omnitop.R
import com.annoyingturtle.omnitop.fragment.noteActivity.ModificaNota
import com.annoyingturtle.omnitop.fragment.noteActivity.NoteAdapter
import com.annoyingturtle.omnitop.fragment.noteActivity.NuovaNota
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dndData.entities.Notes
import dndData.viewModel.NotesViewModel
import kotlinx.android.synthetic.main.note_fab_layout.*
import kotlinx.android.synthetic.main.note_fab_layout.view.*


class NoteFabFragment() : BottomSheetDialogFragment(), NoteAdapter.OnItemClickListner {

    private var preferite = true
    private lateinit var mNotaViewModel : NotesViewModel

    val favAdapter = NoteAdapter(this)
    val allAdapter = NoteAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.note_fab_layout, container, false)


        val recyclerView = view.favNoteRecyclerView
        recyclerView.adapter = favAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mNotaViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
        mNotaViewModel.readFavoriteData.observe(viewLifecycleOwner, Observer { nota -> favAdapter.setDataNota(nota)})
        return view

    }

    override fun onStart() {
        super.onStart()

        var bundle = Bundle()
        bundle.putString("goto", requireContext()::class.java.simpleName.toString())

        nuovaNotaBtn.setOnClickListener(){
            startActivity(Intent(context, NuovaNota::class.java).putExtras(bundle))
        }

        allNotesBtn.setOnClickListener(){

            if(preferite){
                allNotesBtn.text = getString(R.string.favorite)
                textView8.text = getString(R.string.allNoteString)
                preferite = false


                val recyclerView = requireView().favNoteRecyclerView

                recyclerView?.adapter = allAdapter
                recyclerView?.layoutManager = LinearLayoutManager(requireContext())

                mNotaViewModel.readAllData.observe(viewLifecycleOwner, Observer { nota -> allAdapter.setDataNota(nota)})
            }
            else
            {
                preferite = true
                allNotesBtn.text = getText(R.string.allNote)
                textView8.text = getString(R.string.favoriteString)

                val recyclerView = requireView().favNoteRecyclerView

                recyclerView?.adapter = favAdapter
                recyclerView?.layoutManager = LinearLayoutManager(requireContext())

                mNotaViewModel.readFavoriteData.observe(viewLifecycleOwner, Observer { nota -> favAdapter.setDataNota(nota)})

            }

        }

    }

    override fun onItemClick(position: Int) {

        var bundle = Bundle()
        bundle.putString("goto", requireContext()::class.java.simpleName.toString())
        startActivity(Intent(context, ModificaNota()::class.java).putExtras(bundle).putExtra("idItem",
                if(preferite)
                    favAdapter.getItemID(position)
                else
                    allAdapter.getItemID(position)
                )
        )

    }
}