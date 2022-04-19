package br.digitalhouse.myboadcastreceiver.ui


import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import br.digitalhouse.myboadcastreceiver.receiver.MyReceiver
import br.digitalhouse.myboadcastreceiver.R

class MainActivity : AppCompatActivity() {

    lateinit var broadcastReceiver : BroadcastReceiver

    override fun onStart() {
        super.onStart()
        //registrando o receptor
        broadcastReceiver = MyReceiver()
        var intentFilter = IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION)
        registerReceiver(broadcastReceiver, intentFilter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        //criando o objeto gerenciador de wifi
        var wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        //pegando o botão na view
        var button = findViewById<Button>(R.id.btn1)

        //verifica se o Wifi está habilitado ou desabilitado
        if(wifiManager.isWifiEnabled){
            button.backgroundTintList = resources.getColorStateList(R.color.green)
        }else
            button.backgroundTintList = resources.getColorStateList(R.color.red)
    }

    override fun onStop() {
        super.onStop()
        //retira o registro do receptor
        unregisterReceiver(broadcastReceiver)
    }
}



