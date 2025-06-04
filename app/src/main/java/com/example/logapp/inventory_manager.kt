package com.example.logapp

object inventory_manager {
     private const val MAX_ITEMS = 7
     val dates = ArrayList<String>()
     val minutesPlayed = ArrayList<Int>()
     val gameGenres = ArrayList<String>()
     val ratings = ArrayList<Int>()

     fun addLogEntry(date: String, minutes: Int, genre: String, rating: Int) {
          if (dates.size >= MAX_ITEMS) {
               dates.removeAt(0)
               minutesPlayed.removeAt(0)
               gameGenres.removeAt(0)
               ratings.removeAt(0)
          }
          dates.add(date)
          minutesPlayed.add(minutes)
          gameGenres.add(genre)
          ratings.add(rating)
     }

     data class GameLog(val date: String, val minutes: Int, val genre: String, val rating: Int)

     fun getHighestRatedGame(): GameLog? {
          if (ratings.isEmpty()) {
               return null
          }

          var highestRating = -1
          var highestRatedIndex = -1

          for (i in ratings.indices) {
               if (ratings[i] > highestRating) {
                    highestRating = ratings[i]
                    highestRatedIndex = i
               }
          }

          return if (highestRatedIndex != -1) {
               GameLog(
                    dates[highestRatedIndex],
                    minutesPlayed[highestRatedIndex],
                    gameGenres[highestRatedIndex],
                    ratings[highestRatedIndex]
               )
          } else {
               null
          }
     }

     fun getAverageMinutesPlayed(): Double {
          if (minutesPlayed.isEmpty()) {
               return 0.0
          }
          return minutesPlayed.average()
     }


     fun getAllLogs(): List<GameLog> {
          val logs = mutableListOf<GameLog>()
          for (i in dates.indices) {
               logs.add(GameLog(dates[i], minutesPlayed[i], gameGenres[i], ratings[i]))
          }
          return logs
     }


}
