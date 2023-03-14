package etec.com.br.victor.conversortemperatura

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import etec.com.br.victor.conversortemperatura.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnConverter.setOnClickListener {
            if(!binding.txtC.text.toString().isEmpty()){
                val celsius = binding.txtC.text.toString().toDouble()
                val fah = String.format("%.2f",celsius * 1.8 + 32)

                binding.txtResultado.text = "Fahrenheit: ${fah} ºF"
            }else{
                binding.txtResultado.text = "Temperatura inválida "
            }
        }

    }
}