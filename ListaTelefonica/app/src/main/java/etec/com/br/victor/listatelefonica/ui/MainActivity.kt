package etec.com.br.victor.listatelefonica.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import etec.com.br.victor.listatelefonica.R
import etec.com.br.victor.listatelefonica.adapter.ContactListAdapter
import etec.com.br.victor.listatelefonica.adapter.listener.ContactOnClickListener
import etec.com.br.victor.listatelefonica.database.DBHelper
import etec.com.br.victor.listatelefonica.databinding.ActivityMainBinding
import etec.com.br.victor.listatelefonica.model.ContactModel

class MainActivity : AppCompatActivity() {

    private lateinit var contactList: List<ContactModel>
    //USING LIST VIEW
    //private lateinit var adapter: ArrayAdapter<ContactModel>
    private lateinit var adapter: ContactListAdapter
    private lateinit var result: ActivityResultLauncher<Intent>
    private lateinit var dbHelper: DBHelper
    private var ascDesc: Boolean = true

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DBHelper(this)
        val sharedPreferences = application.getSharedPreferences("login", Context.MODE_PRIVATE)

        binding.recyclerViewContacts.layoutManager = LinearLayoutManager(applicationContext)

        binding.btnLogout.setOnClickListener {
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("username", "")
            editor.apply()
            finish()
        }

        loadList()

        /*USING LIST VIEW
        binding.listViewContatc.setOnItemClickListener { _, _, position, _ ->
            /*Toast.makeText(
                applicationContext,
                "${contactList[position].name}\n${contactList[position].phone}",
                Toast.LENGTH_SHORT
            ).show()*/
            val intent = Intent(applicationContext, ContactDetailActivity::class.java)
            intent.putExtra("id",contactList[position].id)
            //startActivity(intent)
            result.launch(intent)
        }*/

        binding.btnAdd.setOnClickListener {
            result.launch(Intent(applicationContext, NewContactActivity::class.java))
        }

        binding.btnOrder.setOnClickListener {
            if(ascDesc){
                binding.btnOrder.setImageResource(R.drawable.ic_baseline_arrow_upward_24)
            }else{
                binding.btnOrder.setImageResource(R.drawable.ic_baseline_arrow_downward_24)
            }
            ascDesc = !ascDesc
            contactList = contactList.reversed()
            placeAdapter()
        }

        result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.data != null && it.resultCode == 1){
                loadList()
            }else if(it.data != null && it.resultCode == 0){
                Toast.makeText(applicationContext, "Operation Canceled", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun placeAdapter(){
        adapter = ContactListAdapter(contactList, ContactOnClickListener { contact ->
            val intent = Intent(applicationContext, ContactDetailActivity::class.java)
            intent.putExtra("id",contact.id)
            result.launch(intent)
        })
        binding.recyclerViewContacts.adapter = adapter
    }

    private fun loadList() {
        contactList = dbHelper.getAllContact().sortedWith(compareBy{it.name})
        placeAdapter()

        /* USING LIST VIEW
        contactList = dbHelper.getAllContact()

        adapter = ArrayAdapter(
            applicationContext,
            android.R.layout.simple_list_item_1,
            contactList
        )
        binding.listViewContatc.adapter = adapter*/
    }
}