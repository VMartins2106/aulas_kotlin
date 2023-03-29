package etec.com.br.victor.listatelefonica.adapter.listener

import etec.com.br.victor.listatelefonica.model.ContactModel

class ContactOnClickListener(val clickListener: (contact: ContactModel) -> Unit) {

    fun onClick(contact: ContactModel) = clickListener

}