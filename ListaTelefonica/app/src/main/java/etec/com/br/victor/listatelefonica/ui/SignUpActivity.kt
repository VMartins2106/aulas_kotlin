package etec.com.br.victor.listatelefonica.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import etec.com.br.victor.listatelefonica.R
import etec.com.br.victor.listatelefonica.database.DBHelper
import etec.com.br.victor.listatelefonica.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = DBHelper(this)

        binding.btnSingUp.setOnClickListener {
            // validate sign up

            val name = binding.edtName.text.toString()
            val pass = binding.edtPass.text.toString()
            val confirmPass = binding.edtConfirmPass.text.toString()

            if (name.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {
                    val res = db.insertUser(name, pass)
                    if (res > 0) {
                        Toast.makeText(
                            applicationContext,
                            getString(R.string.signup_ok),
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    } else {
                        Toast.makeText(
                            applicationContext,
                            getString(R.string.signup_error),
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.edtName.setText("")
                        binding.edtPass.setText("")
                        binding.edtConfirmPass.setText("")
                    }
                } else {
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.password_dont_match),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.fill_fields),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}