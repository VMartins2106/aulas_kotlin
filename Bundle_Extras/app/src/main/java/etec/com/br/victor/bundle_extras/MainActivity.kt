package etec.com.br.victor.bundle_extras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import etec.com.br.victor.bundle_extras.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOk.setOnClickListener {
            val numero = binding.edtN1.text.toString().trim().toInt()

            val i = Intent(this, Tela2::class.java)
            i.putExtra("n1", numero)
            startActivity(i)
        }
    }
}