package etec.com.br.victor.listatelefonica.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import etec.com.br.victor.listatelefonica.R

class ContactViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val image: ImageView = view.findViewById(R.id.imgContact)
    val txtName: TextView = view.findViewById(R.id.txtContactNome)

}