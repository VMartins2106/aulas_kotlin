package etec.com.br.victor.list_listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import etec.com.br.victor.list_listview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listaNumeros = ArrayList<Int>()

        listaNumeros.add(1)
        listaNumeros.add(10)
        listaNumeros.add(5)
        listaNumeros.add(4)
        listaNumeros.add(8)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,listaNumeros)
        binding.listView.adapter = adapter

        binding.listView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, "Clicado em ${listaNumeros.get(position)}",Toast.LENGTH_SHORT).show()
        }

        /*
        //listaNumeros = [1,10,5,4,8]

        val primeiro = listaNumeros.get(0) // valor na posição 0

        listaNumeros.removeAt(0) // remover o valor da posição 0
        //listaNumeros = [10,5,4,8]

        listaNumeros.remove(5) // remover o valor passado
        //listaNumeros = [10,4,8]

        val tamanho = listaNumeros.size // tamanho da lista

        listaNumeros.clear() // limpar toda a lista
        // listaNumeros = []
        */
    }
}