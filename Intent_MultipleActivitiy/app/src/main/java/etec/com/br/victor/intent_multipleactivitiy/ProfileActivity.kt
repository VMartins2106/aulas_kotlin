package etec.com.br.victor.intent_multipleactivitiy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import etec.com.br.victor.intent_multipleactivitiy.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVoltar.setOnClickListener {
            //startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }
}