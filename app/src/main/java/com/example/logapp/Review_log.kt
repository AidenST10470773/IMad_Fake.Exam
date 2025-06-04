package com.example.logapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale
import kotlin.text.forEachIndexed
import kotlin.text.format
import kotlin.text.isNotEmpty
import kotlin.time.minutes

class Review_log : AppCompatActivity() {
    private lateinit var tvHighestRatedGameDetails: TextView
    private lateinit var tvAverageMinutesValue: TextView
    private lateinit var tvAllLogsList: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_review_log)

        tvHighestRatedGameDetails = findViewById(R.id.tvHighestRatedGameDetails)
        tvAverageMinutesValue = findViewById(R.id.tvAverageMinutesValue)
        tvAllLogsList = findViewById(R.id.tvAllLogsList) // Initialize this TextView

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Load and display data
        loadAndDisplayReviewData()
    }

    private fun loadAndDisplayReviewData() {

        val highestRatedGame = inventory_manager.getHighestRatedGame()
        if (highestRatedGame != null) {
            tvHighestRatedGameDetails.text =
                "Date: ${highestRatedGame.date}, Genre: ${highestRatedGame.genre}, Rating: ${highestRatedGame.rating}, Mins: ${highestRatedGame.minutes}"
        } else {
            tvHighestRatedGameDetails.text = "No games logged yet."
        }


        val averageMinutes = inventory_manager.getAverageMinutesPlayed()
        tvAverageMinutesValue.text = String.format(Locale.getDefault(), "%.1f minutes", averageMinutes)



        val allLogs = inventory_manager.getAllLogs()
        if (allLogs.isNotEmpty()) {
            val logsText = StringBuilder()
            allLogs.forEachIndexed { index, log ->
                logsText.append("Game ${index + 1}:\n")
                logsText.append("  Date: ${log.date}\n")
                logsText.append("  Genre: ${log.genre}\n")
                logsText.append("  Rating: ${log.rating}\n")
                logsText.append("  Minutes: ${log.minutes}\n\n")
            }
            tvAllLogsList.text = logsText.toString()
        } else {
            tvAllLogsList.text = "No games logged yet."
        }
    }

    override fun onResume() {
        super.onResume()
        loadAndDisplayReviewData()
    }
}
