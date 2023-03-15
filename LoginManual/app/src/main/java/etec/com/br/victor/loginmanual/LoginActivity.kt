package etec.com.br.victor.loginmanual

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import etec.com.br.victor.loginmanual.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogout.setOnClickListener {
            finish()
        }
    }
}