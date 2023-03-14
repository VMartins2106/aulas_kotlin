package etec.com.br.victor.exerciciolinearlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import etec.com.br.victor.exerciciolinearlayout.databinding.ActivityMainBinding
import kotlin.text.Typography.euro

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDolar.setOnClickListener {
            escrever(0.8, "$")
        }
        binding.btnReal.setOnClickListener {
            escrever(5.2, "R$")
        }
        binding.btnPeso.setOnClickListener {
            escrever(10.2, "$")
        }

    }

    private fun escrever(taxa: Double, formato: String) {
        val moeda = binding.edtMoeda.text.toString().trim()
        if(!moeda.isEmpty()){
            var resultado = String.format("%.2f",moeda.toDouble() * taxa)
            Toast.makeText(applicationContext, "$resultado $formato", Toast.LENGTH_SHORT).show()
        }
    }
}