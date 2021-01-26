package absPackage

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.annoyingturtle.omnitop.HomeActivity
import com.annoyingturtle.omnitop.R
import com.annoyingturtle.omnitop.dndHome
import kotlinx.android.synthetic.main.activity_main.view.*
import java.security.AccessController.getContext

class AdapterRecyclerListaGiochi (val Lista : List<ItemListaGiochi>, private val listner :OnItemClickListnerInteface ) : RecyclerView.Adapter<AdapterRecyclerListaGiochi.ListaGiochiViewHolder>() {





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaGiochiViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.lista_giochi_layout, parent, false)

        return ListaGiochiViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ListaGiochiViewHolder, position: Int) {
        val currentItem = Lista[position]

        holder.imageView.setImageResource(currentItem.imageResources)

    }

    override fun getItemCount() = Lista.size

    inner class ListaGiochiViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        var imageView : ImageView  = itemView.findViewById(R.id.IdImageView)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION)
            listner.onItemClick(position)
        }
    }

    interface OnItemClickListnerInteface{
        fun onItemClick(position: Int)
    }

}

