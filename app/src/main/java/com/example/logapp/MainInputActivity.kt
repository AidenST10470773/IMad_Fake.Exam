package com.example.logapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainInputActivity : AppCompatActivity() {

    private lateinit var enterBtn : Button
    private lateinit var ReviewBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_input)

        enterBtn = findViewById(R.id.enterBtn)
        ReviewBtn = findViewById(R.id.ReviewBtn)

        enterBtn.setOnClickListener {
            val intent = Intent(this, Add_log::class.java)
            startActivity(intent)
        }

        ReviewBtn.setOnClickListener {
            val intent = Intent(this, Review_log::class.java)
            startActivity(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}