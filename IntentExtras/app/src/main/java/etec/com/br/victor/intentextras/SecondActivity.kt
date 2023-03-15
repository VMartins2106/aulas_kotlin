package etec.com.br.victor.intentextras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import etec.com.br.victor.intentextras.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val i = intent
        val nome = i.extras?.getString("nome")

        if(nome.equals("") || nome == null){
            Toast.makeText(applicationContext, "Nome não inserido", Toast.LENGTH_SHORT).show()
        }else{
            binding.txtNome.setText("Bem-Vindo $nome")
        }

        binding.btnMudar.setOnClickListener {
            if(!binding.edtNome.text.toString().equals("")){
                binding.txtNome.setText("Bem-Vindo ${binding.edtNome.text.toString()}")

                // RETORNAR INTENT
                i.putExtra("nome",binding.edtNome.text.toString())
                setResult(1,i)
                finish()
            }else{
                Toast.makeText(applicationContext, "Insira algo para alterar", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnCancelar.setOnClickListener {
            setResult(2,i)
            finish()
        }
    }
}