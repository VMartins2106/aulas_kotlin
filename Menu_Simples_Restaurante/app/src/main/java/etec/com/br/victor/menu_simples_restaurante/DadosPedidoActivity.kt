package etec.com.br.victor.menu_simples_restaurante

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import etec.com.br.victor.menu_simples_restaurante.databinding.ActivityDadosPedidoBinding

class DadosPedidoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDadosPedidoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDadosPedidoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val i = intent
        val qtdCafe = i.getStringExtra("qtdCafe").toString().toInt()
        val qtdPao = i.getStringExtra("qtdPao").toString().toInt()
        val qtdChocolate = i.getStringExtra("qtdChocolate").toString().toInt()
        val precoCafe = i.getDoubleExtra("precoCafe",1.0)
        val precoPao = i.getDoubleExtra("precoPao",5.0)
        val precoChocolate = i.getDoubleExtra("precoChocolate",10.0)

        val total = (qtdCafe*precoCafe) + (qtdPao*precoPao) + (qtdChocolate*precoChocolate)
        val texto = "Resumo do pedido: \n\n" +
                "Café: $qtdCafe --> Preço: ${qtdCafe*precoCafe}$\n" +
                "Pão: $qtdPao --> Preço: ${qtdPao*precoPao}$\n" +
                "Chocolate: $qtdChocolate --> Preço: ${qtdChocolate*precoChocolate}$\n" +
                "\n\n Total: $total"

        binding.txtResumo.setText(texto)

    }
}