package etec.com.br.victor.crud_objetos_listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import etec.com.br.victor.crud_objetos_listview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var pos = -1

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listaUsuarios = ArrayList<Usuario>()
        listaUsuarios.add(Usuario("user","pass"))
        listaUsuarios.add(Usuario("admin","1234"))
        listaUsuarios.add(Usuario("victor","senha"))

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaUsuarios)
        binding.listViewUser.adapter = adapter

        binding.listViewUser.setOnItemClickListener { _, _, i, _ ->
            // AMBOS OS JEITOS DE PEGAR O INDEX FUNCIONAM
            binding.userName.setText(listaUsuarios[i].userName)
            binding.userPass.setText(listaUsuarios.get(i).password)
            pos = i
        }

        binding.btnCad.setOnClickListener {
            val nome = binding.userName.text.toString().trim()
            val senha = binding.userPass.text.toString().trim()

            // AMBOS OS JEITOS FUNCIONAM
            if(nome.isNotEmpty() && !senha.isEmpty()){
                listaUsuarios.add(Usuario(nome,senha))
                adapter.notifyDataSetChanged()
                binding.userName.setText("")
                binding.userPass.setText("")
                pos = -1
            }
        }

        binding.btnUpd.setOnClickListener {
            if(pos>=0){
                val nome = binding.userName.text.toString().trim()
                val senha = binding.userPass.text.toString().trim()
                if(nome.isNotEmpty() && !senha.isEmpty()){
                    listaUsuarios.get(pos).userName = nome
                    listaUsuarios.get(pos).password = senha
                    adapter.notifyDataSetChanged()
                    binding.userName.setText("")
                    binding.userPass.setText("")
                    pos = -1
                }

            }
        }

        binding.btnDel.setOnClickListener {
            if(pos>=0){
                listaUsuarios.removeAt(pos)
                adapter.notifyDataSetChanged()
                binding.userName.setText("")
                binding.userPass.setText("")
                pos = -1
            }
        }

        binding.btnLimpar.setOnClickListener {
            listaUsuarios.clear()
            adapter.notifyDataSetChanged()
            binding.userName.setText("")
            binding.userPass.setText("")
            pos = -1
        }
    }
}