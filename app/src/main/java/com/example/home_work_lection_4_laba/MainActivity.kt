package com.example.home_work_lection_4_laba

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlin.math.pow


class MainActivity : AppCompatActivity() {
    private val mReceiver = CustomReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        // наушники
        filter.addAction(Intent.ACTION_HEADSET_PLUG)

        registerReceiver(mReceiver, filter)

        val localFilter = IntentFilter(ACTION_CUSTOM_BROADCAST)
        localFilter.addAction(ACTION_CUSTOM_BROADCAST_RANDOM)
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(mReceiver, localFilter)
    }

    override fun onDestroy() {
        unregisterReceiver(mReceiver)
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver)
        super.onDestroy()
    }

    fun sendCustomBroadcast(view: View) {
        val customBroadcastIntent = Intent(ACTION_CUSTOM_BROADCAST)
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent)
    }

    fun generateRandomNumber(view: View) {
        val randomBroadcastIntent = Intent(ACTION_CUSTOM_BROADCAST_RANDOM)
        val randomNum = (1..20).random()
        findViewById<TextView>(R.id.randomNumber).text = randomNum.toString()
        randomBroadcastIntent.putExtra("message", "Square of the random num: ${(randomNum.toFloat().pow(2)).toInt()}")
        LocalBroadcastManager.getInstance(this).sendBroadcast(randomBroadcastIntent)
    }
}