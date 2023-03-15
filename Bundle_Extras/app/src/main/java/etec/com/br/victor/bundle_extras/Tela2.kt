package etec.com.br.victor.bundle_extras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import etec.com.br.victor.bundle_extras.databinding.ActivityMainBinding
import etec.com.br.victor.bundle_extras.databinding.ActivityTela2Binding

class Tela2 : AppCompatActivity() {

    private lateinit var binding: ActivityTela2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTela2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val i = intent
        val bundle = i.extras

        binding.btnOk.setOnClickListener {
            val numero = binding.edtN2.text.toString().trim().toInt()

            val i = Intent(this, Tela3::class.java)
            if (bundle != null) {
                i.putExtras(bundle)
            }
            i.putExtra("n2", numero)
            startActivity(i)
        }
    }
}