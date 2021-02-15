package com.annoyingturtle.omnitop.fragment.noteActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.annoyingturtle.omnitop.R
import dndData.entities.Notes
import kotlinx.android.synthetic.main.lista_note_layout.view.*

class NoteAdapter(private val listener : OnItemClickListner) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private var NotaList = emptyList<Notes>()

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        /** gestione click*/

        override fun onClick(v: View?) {
            val pos = adapterPosition

            if(pos != RecyclerView.NO_POSITION)
                listener.onItemClick(pos)
        }
    }

    override fun getItemCount() = NotaList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.lista_note_layout, parent, false))
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentItem = NotaList[position]
        holder.itemView.nomeNotaCardText.text = currentItem.titoloNota

        if (currentItem.ruoloNota.toString() == "DM")
            holder.itemView.ruoloGiocatoreNotaDM.visibility = View.VISIBLE
        else
            holder.itemView.ruoloGiocatoreNotaPG.visibility = View.VISIBLE

        if (currentItem.preferito) holder.itemView.notaPreferia.visibility = View.VISIBLE
    }

    fun setDataNota(nota: List<Notes>) {
        this.NotaList = nota
        notifyDataSetChanged()
    }

    /** Interfaccia per il listener */
    interface OnItemClickListner{
        fun onItemClick(position: Int)
    }
}