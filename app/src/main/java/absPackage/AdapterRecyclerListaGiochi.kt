package absPackage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.annoyingturtle.omnitop.R
import kotlinx.android.synthetic.main.activity_main.view.*

class ListaGiochiViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    val imageView : ImageView // = itemView.imageView.findViewById(R.id.IdImageView)////////

    init {
        imageView = itemView.findViewById(R.id.IdImageView)
    }
}

class AdapterRecyclerListaGiochi (val Lista : List<ItemListaGiochi>) : RecyclerView.Adapter<ListaGiochiViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaGiochiViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.lista_giochi_layout, parent, false)

        return ListaGiochiViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ListaGiochiViewHolder, position: Int) {
        val currentItem = Lista[position]

        holder.imageView.setImageResource(currentItem.imageResources)
    }

    override fun getItemCount() = Lista.size


}

