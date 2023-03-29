package etec.com.br.victor.listatelefonica.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import etec.com.br.victor.listatelefonica.R
import etec.com.br.victor.listatelefonica.adapter.listener.ContactOnClickListener
import etec.com.br.victor.listatelefonica.adapter.viewholder.ContactViewHolder
import etec.com.br.victor.listatelefonica.model.ContactModel

class ContactListAdapter(
    private val contactList: List<ContactModel>,
    private val contactOnClickListener: ContactOnClickListener): RecyclerView.Adapter<ContactViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contactList[position]
        holder.txtName.text = contact.name
        if(contact.imageId>0){
            holder.image.setImageResource(contact.imageId)
        }else{
            holder.image.setImageResource(R.drawable.padrao)
        }
        holder.itemView.setOnClickListener {
            contactOnClickListener.clickListener(contact)
        }
    }

    override fun getItemCount(): Int {
        return contactList.size
    }
}