package etec.com.br.victor.apprelogio

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import etec.com.br.victor.apprelogio.databinding.ActivityFullscreenBinding

class FullscreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFullscreenBinding
    private var isChecked = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFullscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // RETIRA A BARRA SUPERIOR DO CELULAR
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        }else{
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        // MANTER A TELA SEMPRE LIGADA
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        // CHECAR NIVEL DE BATERIA
        val baterialReceiver: BroadcastReceiver = object: BroadcastReceiver(){
            override fun onReceive(p0: Context?, p1: Intent?) {
                if(p1!=null){
                    val nivel: Int = p1.getIntExtra(BatteryManager.EXTRA_LEVEL,0)
                    //Toast.makeText(applicationContext, nivel.toString(), Toast.LENGTH_SHORT).show()
                    binding.txtBateria.text = "Bateria: ${nivel.toString()}%"
                }
            }
        }
        // MOSTRAR NIVEL DE BATERIA
        registerReceiver(baterialReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))

        binding.checkNivelBateria.setOnClickListener {
            if(isChecked){
                isChecked = false
                binding.txtBateria.visibility = View.GONE
            }else{
                isChecked = true
                binding.txtBateria.visibility = View.VISIBLE
            }
            binding.checkNivelBateria.isChecked = isChecked
        }

        // MOSTRANDO E TIRANDO MENU DE BATERIA
        binding.layoutMenu.animate().translationY(500F)
        binding.imageAbrir.setOnClickListener {
            binding.layoutMenu.visibility = View.VISIBLE
            binding.layoutMenu.animate().translationY(0F).setDuration(
                resources.getInteger(android.R.integer.config_mediumAnimTime).toLong()
            )
        }
        binding.imageFechar.setOnClickListener {
            binding.layoutMenu.animate().translationY(binding.layoutMenu.measuredHeight.toFloat())
                .setDuration(
                    resources.getInteger(android.R.integer.config_mediumAnimTime).toLong()
                )
        }
    }
}