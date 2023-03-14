package etec.com.br.victor.intent_multipleactivitiy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import etec.com.br.victor.intent_multipleactivitiy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTela2.setOnClickListener {
            /*val i = Intent(this, ProfileActivity::class.java)
            startActivity(i)*/

            startActivity(Intent(this, ProfileActivity::class.java))
        }

    }
}