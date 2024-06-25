package com.example.drawermenu2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CustomBroadcastReceiver : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_custom_broadcast_receiver)

        val input = findViewById<EditText>(R.id.Text)
        val button = findViewById<Button>(R.id.submit)

        button.setOnClickListener{
            val userinput = input.text.toString()
            val intent = Intent(this,ThirdActivity::class.java)
            intent.putExtra("userinput",userinput)
            startActivity(intent)
        }
    }
}