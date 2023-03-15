package etec.com.br.victor.intentextras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import etec.com.br.victor.intentextras.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // RETORNAR INTENT
    private lateinit var result : ActivityResultLauncher<Intent>

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOk.setOnClickListener {
            val nome = binding.edtNome.text.toString()

            val i = Intent(this, SecondActivity::class.java)
            i.putExtra("nome",nome)
            result.launch(i) // PARA PEDIR UM RESULTADO
            //startActivity(i)
        }

        // retornar intent
        result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.data != null && it.resultCode == 1){
                binding.edtNome.setText(it.data?.getStringExtra("nome").toString())
                binding.txtNome.setText(it.data?.getStringExtra("nome").toString())
            }else if(it.data != null && it.resultCode == 2){
                Toast.makeText(applicationContext, "Operação cancelada", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext, "Erro ao atualizar o nome", Toast.LENGTH_SHORT).show()
            }
        }
    }
}