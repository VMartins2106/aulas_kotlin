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
import etec.com.br.victor.listatelefonica.database.DBHelper
import etec.com.br.victor.listatelefonica.databinding.ActivityMainBinding
import etec.com.br.victor.listatelefonica.model.ContactModel

class MainActivity : AppCompatActivity() {

    private lateinit var contactList: ArrayList<ContactModel>
    private lateinit var adapter: ArrayAdapter<ContactModel>
    private lateinit var result: ActivityResultLauncher<Intent>
    private lateinit var dbHelper: DBHelper

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DBHelper(this)
        val sharedPreferences = application.getSharedPreferences("login", Context.MODE_PRIVATE)

        binding.btnLogout.setOnClickListener {
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("username", "")
            editor.apply()
            finish()
        }

        loadList()

        binding.listViewContatc.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(
                applicationContext,
                "${contactList[position].name}\n${contactList[position].phone}",
                Toast.LENGTH_SHORT
            ).show()
            val intent = Intent(applicationContext, ContactDetailActivity::class.java)
            intent.putExtra("id",contactList[position].id)
            startActivity(intent)
        }

        binding.btnAdd.setOnClickListener {
            result.launch(Intent(applicationContext, NewContactActivity::class.java))
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

    private fun loadList() {
        contactList = dbHelper.getAllContact()

        adapter = ArrayAdapter(
            applicationContext,
            android.R.layout.simple_list_item_1,
            contactList
        )
        binding.listViewContatc.adapter = adapter
    }
}