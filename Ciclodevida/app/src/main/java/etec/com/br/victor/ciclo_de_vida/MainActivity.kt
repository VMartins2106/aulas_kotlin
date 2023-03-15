package etec.com.br.victor.ciclo_de_vida

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import etec.com.br.victor.ciclo_de_vida.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(applicationContext,"Realizou o onCreate 1",Toast.LENGTH_SHORT).show()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPrimeira.setOnClickListener {
            startActivity(Intent(this, Tela2::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(applicationContext,"Realizou o onStart 1",Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(applicationContext,"Realizou o onResume 1",Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(applicationContext,"Realizou o onPause 1",Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(applicationContext,"Realizou o onStop 1",Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(applicationContext,"Realizou o onDestroy 1",Toast.LENGTH_SHORT).show()
    }
}