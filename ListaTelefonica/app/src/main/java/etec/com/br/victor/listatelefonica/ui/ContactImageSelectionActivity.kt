package etec.com.br.victor.listatelefonica.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import etec.com.br.victor.listatelefonica.R
import etec.com.br.victor.listatelefonica.databinding.ActivityContactImageSelectionBinding

class ContactImageSelectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactImageSelectionBinding
    private lateinit var i: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactImageSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        i = intent

        binding.imageProfile1.setOnClickListener { sendId(R.drawable.profile1) }
        binding.imageProfile2.setOnClickListener { sendId(R.drawable.profile2) }
        binding.imageProfile3.setOnClickListener { sendId(R.drawable.profile3) }
        binding.imageProfile4.setOnClickListener { sendId(R.drawable.profile4) }
        binding.imageProfile5.setOnClickListener { sendId(R.drawable.profile5) }
        binding.imageProfile6.setOnClickListener { sendId(R.drawable.profile6) }
        binding.btnRemoveImage.setOnClickListener { sendId(R.drawable.padrao) }
    }

    private fun sendId(id: Int) {
        i.putExtra("id", id)
        setResult(1,i)
        finish()
    }
}