package etec.com.br.victor.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import etec.com.br.victor.recyclerview.R
import etec.com.br.victor.recyclerview.model.Carro

class CarroListAdapter(val listaCarros: ArrayList<Carro>, val onClickListener: OnClickListener):
    RecyclerView.Adapter<CarroListAdapter.CarroViewHolder>() {

    var contadorOnCreate = 0
    var contadorOnBind = 0

    class CarroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView : TextView = itemView.findViewById(R.id.txtModelo)
    }

    class OnClickListener(val clickListener: (carro: Carro) -> Unit){
        fun onClick(carro: Carro) = clickListener(carro)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarroViewHolder {
        contadorOnCreate++
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_carro_list, parent, false)

        return CarroViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarroViewHolder, position: Int) {
        contadorOnBind++
        val carro = listaCarros[position]
        holder.textView.setText(carro.modelo)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(carro)
        }
    }

    override fun getItemCount(): Int {
        return listaCarros.size
    }

}
