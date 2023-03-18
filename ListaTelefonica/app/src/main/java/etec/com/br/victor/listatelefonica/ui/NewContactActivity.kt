package etec.com.br.victor.listatelefonica.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import etec.com.br.victor.listatelefonica.R
import etec.com.br.victor.listatelefonica.database.DBHelper
import etec.com.br.victor.listatelefonica.databinding.ActivityNewContactBinding

class NewContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewContactBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = DBHelper(applicationContext)
        val i = intent

        binding.btnSave.setOnClickListener {
            val name = binding.edtName.text.toString()
            val address = binding.edtAddress.text.toString()
            val email = binding.edtEmail.text.toString()
            val phone = binding.edtPhone.text.toString()
            val imageId = 1

            if(name.isNotEmpty() && address.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty()){
                val res = db.insertContact(name, address, email, phone, imageId)

                if(res>0){
                    Toast.makeText(applicationContext, "Insert OK", Toast.LENGTH_SHORT).show()
                    setResult(1,i)
                    finish()
                }else{
                    Toast.makeText(applicationContext, "Insert ERROR", Toast.LENGTH_SHORT).show()
                }
            }

        }
        binding.btnCancel.setOnClickListener {
            setResult(0,i)
            finish()
        }
    }
}