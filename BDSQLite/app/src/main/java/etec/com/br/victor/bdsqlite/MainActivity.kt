package etec.com.br.victor.bdsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import etec.com.br.victor.bdsqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ArrayAdapter<User>
    private var pos: Int = -1

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = DBHelper(this)

        val listaUsuarios = db.usuarioListSelectAll()

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaUsuarios)
        binding.listView.adapter = adapter
        
        binding.listView.setOnItemClickListener { _, _, position, _ ->
            binding.txtId.setText("ID: ${listaUsuarios[position].id}")
            binding.edtName.setText(listaUsuarios[position].username)
            binding.edtPass.setText(listaUsuarios[position].password)
            pos = position
        }

        binding.btnInserir.setOnClickListener {
            if(binding.edtName.text.isNotEmpty() && binding.edtPass.text.isNotEmpty()){
                val nome = binding.edtName.text.toString()
                val senha = binding.edtPass.text.toString()

                val res = db.usuarioInsert(nome, senha)

                if (res>0){
                    Toast.makeText(applicationContext, "Insert OK: $res", Toast.LENGTH_SHORT).show()
                    listaUsuarios.add(User(res.toInt(), nome, senha))
                    adapter.notifyDataSetChanged()

                    binding.edtName.setText("")
                    binding.edtPass.setText("")
                }else{
                    Toast.makeText(applicationContext, "ERRO NO INSERT", Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.btnEditar.setOnClickListener {
            if(pos>=0 && binding.edtName.text.isNotEmpty() && binding.edtPass.text.isNotEmpty()){
                val id = listaUsuarios[pos].id
                val nome = binding.edtName.text.toString()
                val senha = binding.edtPass.text.toString()

                val res = db.usuarioUpdate(id, nome, senha)

                if (res>0){
                    Toast.makeText(applicationContext, "Update OK: $res", Toast.LENGTH_SHORT).show()
                    listaUsuarios[pos].username = nome
                    listaUsuarios[pos].password = senha
                    adapter.notifyDataSetChanged()

                    binding.txtId.setText("ID: ")
                    binding.edtName.setText("")
                    binding.edtPass.setText("")
                    pos = -1
                }else{
                    Toast.makeText(applicationContext, "ERRO NO UPDATE", Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.btnDeletar.setOnClickListener {
            if(pos>=0){
                val id = listaUsuarios[pos].id
                val res = db.usuarioDelete(id)

                if (res>0){
                    Toast.makeText(applicationContext, "DELETE OK: $res", Toast.LENGTH_SHORT).show()
                    listaUsuarios.removeAt(pos)
                    adapter.notifyDataSetChanged()

                    binding.txtId.setText("ID: ")
                    binding.edtName.setText("")
                    binding.edtPass.setText("")
                    pos = -1
                }else{
                    Toast.makeText(applicationContext, "ERRO NO DELETE", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}