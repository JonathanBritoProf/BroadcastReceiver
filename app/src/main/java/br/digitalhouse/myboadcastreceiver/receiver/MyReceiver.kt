package br.digitalhouse.myboadcastreceiver.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.net.wifi.WifiManager
import android.widget.Toast
import br.digitalhouse.myboadcastreceiver.ui.MainActivity

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        // cria uma intent para realizar a troca de tela
        var trocarTela =  Intent(context, MainActivity :: class.java)

        //cria um objeto shared preferences para verificar se foi alterado o estado do wifi
        var sharedPreferences = context.getSharedPreferences("mysharedprefs", MODE_PRIVATE)
        var currentState =  sharedPreferences.getInt(WifiManager.EXTRA_WIFI_STATE,WifiManager.WIFI_STATE_UNKNOWN)

        //verifica se o wifi está habilitado ou desabilitado e armazena em uma var INT
        var stateWifi = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN)

        //verifica se foi alterado o ultimo estado do wifi
       if (stateWifi != currentState) {
           //valida se o wifi etá habilitado ou desabilitado e realiza a abertura da tela de acordo
           when (stateWifi) {
               WifiManager.WIFI_STATE_ENABLED -> {
                   Toast.makeText(context, "WIFI ON", Toast.LENGTH_LONG).show()
                   context.startActivity(trocarTela)
               }
               WifiManager.WIFI_STATE_DISABLED -> {
                   Toast.makeText(context, "WIFI OFF", Toast.LENGTH_LONG).show()
                   context.startActivity(trocarTela)
               }
           }
       }

        //Salva a ultima alteração do Wifi no Shared Preferences
        var editor = sharedPreferences.edit()
        editor.putInt(WifiManager.EXTRA_WIFI_STATE,stateWifi)
        editor.commit()
    }
}