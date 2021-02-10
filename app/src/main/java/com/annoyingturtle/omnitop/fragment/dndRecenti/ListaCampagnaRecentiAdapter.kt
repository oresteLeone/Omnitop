package com.annoyingturtle.omnitop.fragment.dndRecenti

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.annoyingturtle.omnitop.R
import dndData.entities.Campagna
import kotlinx.android.synthetic.main.lista_campagne.view.*

class ListaCampagnaRecentiAdapter: RecyclerView.Adapter<ListaCampagnaRecentiAdapter.RecentiViewHolder>() {

    private var CampagnaList = emptyList<Campagna>()

    class RecentiViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaCampagnaRecentiAdapter.RecentiViewHolder {
        return RecentiViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.lista_campagne,parent,false))
    }

    override fun onBindViewHolder(holder: ListaCampagnaRecentiAdapter.RecentiViewHolder, position: Int) {
        val currentItem = CampagnaList[position]
        holder.itemView.nomeCampagnaCardText.text = currentItem.titoloCampagna
        if(currentItem.ruoloCampagna.toString() == "DM"){
            holder.itemView.ruoloGiocatoreCampagnaDM.visibility = View.VISIBLE
        }else{
            holder.itemView.ruoloGiocatoreCampagnaPG.visibility = View.VISIBLE
        }
    }

    fun setDataCampagna(campagna: List<Campagna>){
        this.CampagnaList = campagna
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return CampagnaList.size
    }
}