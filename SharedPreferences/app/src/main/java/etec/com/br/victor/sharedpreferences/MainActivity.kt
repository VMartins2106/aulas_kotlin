package etec.com.br.victor.sharedpreferences

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import etec.com.br.victor.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreference = this.getSharedPreferences("valores", Context.MODE_PRIVATE)
        val valor = sharedPreference.getString("valor", "")

        binding.txtValor.setText(valor)

        binding.btnGravar.setOnClickListener {
            val novoValor = binding.edtValor.text.toString()
            binding.txtValor.setText(novoValor)

            val editor = sharedPreference.edit()
            editor.putString("valor",novoValor)
            editor.apply()
        }
    }
}