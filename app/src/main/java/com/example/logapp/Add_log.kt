package com.example.logapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Add_log : AppCompatActivity() {
    private lateinit var etDate: EditText
    private lateinit var etMinutesPlyd: EditText
    private lateinit var etGenre: EditText
    private lateinit var etRating: EditText
    private lateinit var addBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_log)

        etDate = findViewById(R.id.etDete)
        etMinutesPlyd = findViewById(R.id.etMinutesPlyd)
        etGenre = findViewById(R.id.etGenre)
        etRating = findViewById(R.id.etRating)
        addBtn = findViewById(R.id.addBtn)

        addBtn.setOnClickListener {
            val date = etDate.text.toString()
            val minutesPlayed = etMinutesPlyd.text.toString()
            val genre = etGenre.text.toString()
            val rating = etRating.text.toString()


            if (date.isNotEmpty() && minutesPlayed.isNotEmpty() && genre.isNotEmpty() && rating.isNotEmpty()) {
                try {
                    val minutes = minutesPlayed.toInt()
                    val rating = rating.toInt()

                    inventory_manager.addLogEntry(date, minutes, genre, rating)

                    Toast.makeText(this, "Log added successfully!", Toast.LENGTH_SHORT).show()
                    finish()


                } catch (e: NumberFormatException) {
                    Toast.makeText(
                        this,
                        "Please enter valid numbers for Minutes Played and Rating",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }


    }
}



//if (Date.isNotEmpty() && minutesPlayed.isNotEmpty() && GameGenre.isNotEmpty() && rating.isNotEmpty()) {
  //  inventory_manager.Date.add(date)
    //inventory_manager.MinutesPlayed.add(minutesPlayed.toInt())
 //   inventory_manager.GameGenre.add(genre)
 //   inventory_manager.Rating.add(rating.toInt())
   // Toast.makeText(this, "Log added successfully!", Toast.LENGTH_SHORT).show()
 //   finish()

//}else{
 //   Toast.makeText(this, "Please fill in the correct details", Toast.LENGTH_SHORT).show()
//}