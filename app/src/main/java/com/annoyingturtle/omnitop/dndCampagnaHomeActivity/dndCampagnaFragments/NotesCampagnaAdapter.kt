package com.annoyingturtle.omnitop.dndCampagnaHomeActivity.dndCampagnaFragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.annoyingturtle.omnitop.R
import dndData.entities.Notes
import kotlinx.android.synthetic.main.lista_note_campagna.view.*

class NotesCampagnaAdapter(private val listener : NotesCampagnaAdapter.onItemClickListner): RecyclerView.Adapter<NotesCampagnaAdapter.NotesViewHolder>() {

    private var notesList  = emptyList<Notes>()

    inner class NotesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{

            init {
                itemView.setOnClickListener(this)
            }

            override fun onClick(v: View?) {
                val pos = adapterPosition

                if(pos!= RecyclerView.NO_POSITION)
                    listener.onItemClick(pos)
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder{
        return NotesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.lista_note_campagna, parent, false))
    }

    override fun onBindViewHolder(holder: NotesCampagnaAdapter.NotesViewHolder, position: Int){
        val currentItem = notesList[position]
        holder.itemView.titoloNota.text = currentItem.titoloNota
    }

    fun setDataNote(note: List<Notes>){
        this.notesList = note
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    interface onItemClickListner{
        fun onItemClick(position: Int)
    }

    fun getItemID(position: Int): Int{
        return notesList[position].id
    }
}