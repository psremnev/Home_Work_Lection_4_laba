package com.example.home_work_lection_4_laba

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class CustomReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val intentAction = intent.action
        if (intentAction != null) {
            var toastMessage = "unknown intent action"
            when (intentAction) {
                Intent.ACTION_POWER_CONNECTED -> toastMessage = "Power connected!"
                Intent.ACTION_POWER_DISCONNECTED -> toastMessage = "Power disconnected!"
                ACTION_CUSTOM_BROADCAST -> toastMessage = "Custom Broadcast Received"
                Intent.ACTION_HEADSET_PLUG -> toastMessage = "HeadPhones Enable"
                ACTION_CUSTOM_BROADCAST_RANDOM -> toastMessage = (intent.extras?.get("message") ?: "") as String
            }

            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
        }
    }
}