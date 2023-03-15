package etec.com.br.victor.bundle_extras

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import etec.com.br.victor.bundle_extras.databinding.ActivityTela2Binding
import etec.com.br.victor.bundle_extras.databinding.ActivityTela3Binding

class Tela3 : AppCompatActivity() {

    private lateinit var binding: ActivityTela3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTela3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val i = intent

        val numero1 = i.extras?.getInt("n1")
        val numero2 = i.extras?.getInt("n2")

        if(numero1!=null && numero2!=null){
            val soma = numero1 + numero2
            binding.txtResultado.setText("Soma: $soma")
        }else{
            binding.txtResultado.setText("Erro ao passar os valores")
        }
    }
}