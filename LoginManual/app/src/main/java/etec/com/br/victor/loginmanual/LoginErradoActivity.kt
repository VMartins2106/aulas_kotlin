package etec.com.br.victor.loginmanual

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import etec.com.br.victor.loginmanual.databinding.ActivityLoginBinding
import etec.com.br.victor.loginmanual.databinding.ActivityLoginErradoBinding

class LoginErradoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginErradoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginErradoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVoltar.setOnClickListener {
            finish()
        }
    }
}