package etec.com.br.victor.toast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import etec.com.br.victor.toast.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMostrar.setOnClickListener {
            val nome = binding.txtNome.text.toString().trim()
            val sobrenome = binding.txtSobre.text.toString().trim()

            if(nome.isEmpty() || sobrenome.isEmpty()){
                binding.textResultado.text = "Nome não inserido"
                Toast.makeText(applicationContext, "Nome não inserido", Toast.LENGTH_SHORT).show()
            }else{
                binding.textResultado.text = "Olá $nome $sobrenome"
                Toast.makeText(applicationContext, "Olá $nome $sobrenome", Toast.LENGTH_LONG).show()
            }
        }
    }
}