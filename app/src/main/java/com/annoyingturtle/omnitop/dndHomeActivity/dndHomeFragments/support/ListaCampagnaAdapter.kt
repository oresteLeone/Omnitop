package com.annoyingturtle.omnitop.dndHomeActivity.dndHomeFragments.support

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.annoyingturtle.omnitop.R
import dndData.RuoloGiocatore
import dndData.entities.Campagna
import kotlinx.android.synthetic.main.lista_campagne.view.*

class ListaCampagnaAdapter(private val listener : onItemClickListner): RecyclerView.Adapter<ListaCampagnaAdapter.RecentiViewHolder>() {

    private var CampagnaList = emptyList<Campagna>()

    inner class RecentiViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val pos = adapterPosition

            if(pos!= RecyclerView.NO_POSITION)
                listener.onItemClick(pos)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentiViewHolder {
        return RecentiViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.lista_campagne,parent,false))
    }

    override fun onBindViewHolder(holder: RecentiViewHolder, position: Int) {
        val currentItem = CampagnaList[position]
        holder.itemView.nomeCampagnaCardText.text = currentItem.titoloCampagna
        if(currentItem.ruoloCampagna == RuoloGiocatore.DM){
            holder.itemView.ruoloGiocatoreCampagnaDM.visibility = View.VISIBLE
            holder.itemView.ruoloGiocatoreCampagnaPG.visibility = View.GONE
        }else if(currentItem.ruoloCampagna == RuoloGiocatore.PG){
            holder.itemView.ruoloGiocatoreCampagnaPG.visibility = View.VISIBLE
            holder.itemView.ruoloGiocatoreCampagnaDM.visibility = View.GONE
        }
    }

    fun setDataCampagna(campagna: List<Campagna>){
        this.CampagnaList = campagna
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return CampagnaList.size
    }

    interface onItemClickListner {
        fun onItemClick(position: Int)
    }

    fun getItemID(position: Int): Int{
        return CampagnaList[position].id
    }
}