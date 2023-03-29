package etec.com.br.victor.listatelefonica.ui

import android.Manifest.permission.CALL_PHONE
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import etec.com.br.victor.listatelefonica.R
import etec.com.br.victor.listatelefonica.database.DBHelper
import etec.com.br.victor.listatelefonica.databinding.ActivityContactDetailBinding
import etec.com.br.victor.listatelefonica.model.ContactModel

class ContactDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactDetailBinding

    private lateinit var launcher: ActivityResultLauncher<Intent>
    private var imageId: Int? = -1
    private lateinit var db: DBHelper
    private val REQUEST_PHONE_CALL = 1

    private var contactModel = ContactModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val i = intent
        val id = i.extras?.getInt("id")

        db = DBHelper(applicationContext)

        if (id != null) {
            contactModel = db.getContact(id)
            populate()
        } else {
            finish()
        }

        binding.imgEmail.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.type = "text/plain"
            //emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(contactModel.email))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Contact")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Sent by PhoneList app")

            try {
                startActivity(Intent.createChooser(emailIntent, "Choose email client..."))
            }catch (e: Exception){
                Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
            }
        }
        binding.imgPhone.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(CALL_PHONE),
                    REQUEST_PHONE_CALL)
            }else{
                val dialIntent = Intent(Intent.ACTION_CALL, Uri.parse("tel:"+contactModel.phone.toInt()))
                startActivity(dialIntent)
            }
        }

        binding.btnBack.setOnClickListener {
            setResult(0, i)
            finish()
        }

        binding.btnEdit.setOnClickListener {
            binding.layoutEditDelete.visibility = View.VISIBLE
            binding.layoutEdit.visibility = View.GONE
            changeEditText(true)
        }

        binding.btnCancel.setOnClickListener {
            populate()
            changeEditText(false)
            binding.layoutEditDelete.visibility = View.GONE
            binding.layoutEdit.visibility = View.VISIBLE
        }
        binding.btnSave.setOnClickListener {
            val res = db.updateContact(
                id = contactModel.id,
                name = binding.edtName.text.toString(),
                address = binding.edtAddress.text.toString(),
                email = binding.edtEmail.text.toString(),
                phone = binding.edtPhone.text.toString(),
                imageId = contactModel.imageId
            )

            if (res > 0) {
                Toast.makeText(applicationContext, "UPDATE OK", Toast.LENGTH_SHORT).show()
                setResult(1, i)
                finish()
            } else {
                Toast.makeText(applicationContext, "UPDATE ERROR", Toast.LENGTH_SHORT).show()
                setResult(0, i)
                finish()
            }
        }
        binding.btnDelete.setOnClickListener {
            val res = db.deleteContact(contactModel.id)

            if (res > 0) {
                Toast.makeText(applicationContext, "DELETE OK", Toast.LENGTH_SHORT).show()
                setResult(1, i)
                finish()
            } else {
                Toast.makeText(applicationContext, "DELETE ERROR", Toast.LENGTH_SHORT).show()
                setResult(0, i)
                finish()
            }
        }

        binding.imageContact.setOnClickListener {
            launcher.launch(Intent(applicationContext, ContactImageSelectionActivity::class.java))
        }
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.data != null && it.resultCode == 1) {
                imageId = it.data?.extras?.getInt("id")
                binding.imageContact.setImageDrawable(resources.getDrawable(imageId!!))
            } else if (it.data != null && it.resultCode == 0) {
                imageId = -1
                binding.imageContact.setImageResource(R.drawable.padrao)
            }
        }

    }

    private fun changeEditText(status: Boolean) {
        binding.edtName.isEnabled = status
        binding.edtAddress.isEnabled = status
        binding.edtEmail.isEnabled = status
        binding.edtPhone.isEnabled = status
    }

    private fun populate() {
        binding.edtName.setText(contactModel.name)
        binding.edtAddress.setText(contactModel.address)
        binding.edtEmail.setText(contactModel.email)
        binding.edtPhone.setText(contactModel.phone.toString())
        if (contactModel.imageId > 0) {
            binding.imageContact.setImageDrawable(resources.getDrawable(contactModel.imageId))
        } else {
            binding.imageContact.setImageResource(R.drawable.padrao)
        }
    }
}
