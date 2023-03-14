package etec.com.br.victor.app_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import etec.com.br.victor.app_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn.setOnClickListener {
            val nome = binding.txtNome.text.toString()
            //binding.txtView.text = "Olá " + nome
            binding.txtView.text = "Olá $nome"
            //binding.txtView.setText("Olá " + nome)
        }

    }
}