package etec.com.br.victor.menu_simples_restaurante

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import etec.com.br.victor.menu_simples_restaurante.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val precoCafe = "1"
    private val precoPao = "5"
    private val precoChocolate = "10"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPedir.setOnClickListener {
            val i = Intent(this,SplashScreen::class.java)
            i.putExtra("qtdCafe", binding.edtQtdCafe.text.toString())
            i.putExtra("qtdPao", binding.edtQtdPao.text.toString())
            i.putExtra("qtdChocolate", binding.edtQtdChocolate.text.toString())
            i.putExtra("precoCafe", precoCafe)
            i.putExtra("precoPao", precoPao)
            i.putExtra("precoChocolate", precoChocolate)
            startActivity(i)
        }
        binding.checkCafe.setOnClickListener {
            if(binding.checkCafe.isChecked){
                binding.edtQtdCafe.setText("1")
                binding.precoCafe.visibility = View.VISIBLE
            }else{
                binding.edtQtdCafe.setText("0")
                binding.precoCafe.visibility = View.GONE
            }
        }
        binding.checkPao.setOnClickListener {
            if(binding.checkPao.isChecked){
                binding.edtQtdPao.setText("1")
                binding.precoPao.visibility = View.VISIBLE
            }else{
                binding.edtQtdPao.setText("0")
                binding.precoPao.visibility = View.GONE
            }
        }
        binding.checkChocolate.setOnClickListener {
            if(binding.checkChocolate.isChecked){
                binding.edtQtdChocolate.setText("1")
                binding.precoChocolate.visibility = View.VISIBLE
            }else{
                binding.edtQtdChocolate.setText("0")
                binding.precoChocolate.visibility = View.GONE
            }
        }
    }
}