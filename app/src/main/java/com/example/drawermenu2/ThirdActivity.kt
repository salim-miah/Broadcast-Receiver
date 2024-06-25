package com.example.drawermenu2

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter

class ThirdActivity : AppCompatActivity() {
    lateinit var receiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_third)

        var text = findViewById<TextView>(R.id.received_input)

        receiver = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                val broadcast_sent_text = intent?.getStringExtra("broadcast_sent")
                text.text = broadcast_sent_text
            }
        }

        // To register the receiver
        val filter = IntentFilter("com.example.drawermenu2.CUSTOM_INTENT")
        registerReceiver(receiver,filter)

        // Get the userinput from the previous activity and then send it as broadcast
        val userinput = intent.getStringExtra("userinput")
        val broadcastIntent = Intent("com.example.drawermenu2.CUSTOM_INTENT")
        broadcastIntent.putExtra("broadcast_sent",userinput)
        sendBroadcast(broadcastIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}