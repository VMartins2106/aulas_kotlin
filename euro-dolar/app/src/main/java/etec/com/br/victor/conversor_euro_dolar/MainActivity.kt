package etec.com.br.victor.conversor_euro_dolar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import etec.com.br.victor.conversor_euro_dolar.databinding.ActivityMainBinding
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnConverter.setOnClickListener {
            val euro: Double = binding.txtEuro.text.toString().toDouble()
            // don't need ": Double", because it's already defined with "toDouble()"

            val dolar = (euro *0.8 * 100).roundToInt().toDouble() / 100
            // OR
            // val dolar = String.format("%0.2f", euro * 0.8)

            binding.txtDolar.text = "Valor em Dolar: $dolar $"
        }
    }
}