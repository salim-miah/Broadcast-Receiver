package com.example.drawermenu2

import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var drawermenu = findViewById<DrawerLayout>(R.id.navigationdrawermenu)

        toggle = ActionBarDrawerToggle(this,drawermenu,R.string.open,R.string.close)
        drawermenu.setDrawerListener(toggle)
        toggle.syncState()

        var actions = arrayOf("Broadcast Receiver", "Image Scale", "Video", "Audio")
        var adapter = ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line, actions)
        var listview = findViewById<ListView>(R.id.listview1)
        listview.adapter = adapter

        listview.setOnItemClickListener{ parent, view, position, id->
            drawermenu.closeDrawers()
            when (position){
                0 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frame, BroadcastReceiver()).commit()
                }
                1 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frame, ImageScale()).commit()
                }
                2 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frame, Video()).commit()
                }
                3 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frame, Audio()).commit()
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }
}