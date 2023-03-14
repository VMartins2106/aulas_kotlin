package etec.com.br.victor.loginmanual

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import etec.com.br.victor.loginmanual.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEntrar.setOnClickListener {
            val userName = binding.edtUserName.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()

            if(userName.equals("user") && password.equals("pass")){
                Toast.makeText(applicationContext, "Login OK", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext, "Login Inválido", Toast.LENGTH_SHORT).show()
            }

            binding.edtUserName.setText("")
            binding.edtPassword.setText("")
        }

    }
}