package etec.com.br.victor.recyclerview.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import etec.com.br.victor.recyclerview.adapter.CarroListAdapter
import etec.com.br.victor.recyclerview.data.CarroMock
import etec.com.br.victor.recyclerview.databinding.ActivityMainBinding
import etec.com.br.victor.recyclerview.model.Carro

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: CarroListAdapter
    private lateinit var mock: CarroMock
    private var pos = -1

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        mock = CarroMock()
        adapter = CarroListAdapter(mock.listaCarros, CarroListAdapter.OnClickListener{
            pos = pesquisaPosicao(it.id)
            binding.edtModelo.setText(mock.listaCarros[pos].modelo)
            //Toast.makeText(this, it.modelo, Toast.LENGTH_SHORT).show()
        })
        binding.recyclerView.adapter = adapter

        binding.btnInserir.setOnClickListener {
            if(binding.edtModelo.text.toString().trim().isNotEmpty()){
                val modelo = binding.edtModelo.text.toString().toInt()
                mock.listaCarros.add(Carro(modelo, modelo.toString()))
                binding.edtModelo.setText("")
                adapter.notifyDataSetChanged()
            }else{
                Toast.makeText(this, "Insira algo", Toast.LENGTH_SHORT).show()
            }

        }
        binding.btnEdit.setOnClickListener {
            if(pos>=0){
                val modelo = binding.edtModelo.text.toString()
                mock.listaCarros[pos].modelo = modelo
                mock.listaCarros[pos].id = modelo.toInt()

                pos = -1
                binding.edtModelo.setText("")
                adapter.notifyDataSetChanged()
            }
        }
        binding.btnDeletar.setOnClickListener {
            if(pos>=0){
                mock.listaCarros.removeAt(pos)
                pos = -1
                binding.edtModelo.setText("")
                adapter.notifyDataSetChanged()
            }
        }

    }

    private fun pesquisaPosicao(id: Int): Int {
        for (i in 0..mock.listaCarros.size){
            if(mock.listaCarros[i].id == id){
                return i
            }
        }
        return -1
    }
}