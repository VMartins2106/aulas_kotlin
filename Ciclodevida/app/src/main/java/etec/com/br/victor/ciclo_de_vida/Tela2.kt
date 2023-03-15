package etec.com.br.victor.ciclo_de_vida

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import etec.com.br.victor.ciclo_de_vida.databinding.ActivityTela2Binding

class Tela2 : AppCompatActivity() {

    private lateinit var binding: ActivityTela2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(applicationContext,"Realizou o onCreate 2",Toast.LENGTH_SHORT).show()
        binding = ActivityTela2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSegunda.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(applicationContext,"Realizou o onStart 2", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(applicationContext,"Realizou o onResume 2", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(applicationContext,"Realizou o onPause 2", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(applicationContext,"Realizou o onStop 2", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(applicationContext,"Realizou o onDestroy 2", Toast.LENGTH_SHORT).show()
    }
}