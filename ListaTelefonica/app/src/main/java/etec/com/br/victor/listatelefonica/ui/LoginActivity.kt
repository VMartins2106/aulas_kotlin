package etec.com.br.victor.listatelefonica.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import etec.com.br.victor.listatelefonica.R
import etec.com.br.victor.listatelefonica.database.DBHelper
import etec.com.br.victor.listatelefonica.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = DBHelper(this)

        sharedPreferences = application.getSharedPreferences("login", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username","")
        if (username != null) {
            if(username.isNotEmpty()){
                startActivity(Intent(this, MainActivity::class.java))
            }
        }

        binding.btnLogin.setOnClickListener {
            val name = binding.edtName.text.toString()
            val pass = binding.edtPass.text.toString()
            val logged = binding.checkBoxLogged.isChecked

            if (name.isNotEmpty() && pass.isNotEmpty()) {
                if(db.login(name, pass)){
                    if(logged){
                        val editor: SharedPreferences.Editor = sharedPreferences.edit()
                        editor.putString("username", name)
                        editor.apply()
                    }
                    startActivity(Intent(this, MainActivity::class.java))
                    //finish()
                }else{
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.login_error),
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.edtName.setText("")
                    binding.edtPass.setText("")
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.fill_fields),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        binding.txtSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        binding.txtRecoverPassword.setOnClickListener {}
    }
}