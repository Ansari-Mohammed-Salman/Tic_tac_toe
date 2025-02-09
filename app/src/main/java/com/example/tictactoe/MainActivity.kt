package com.example.tictactoe

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.graphics.Color
import android.widget.Toast.makeText

class MainActivity : AppCompatActivity() {

    var playerTurn = true
    var player1Count = 0
    var player2Count = 0
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var emptyCells = ArrayList<Int>()
    var activeUser = 1
    var singleUser = false // Set to true for single-player mode
    lateinit var textView: TextView
    lateinit var textView2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        textView = findViewById(R.id.textView)
        textView2 = findViewById(R.id.textView2)

        // Initialize empty cells
        emptyCells.addAll(1..9)

        val button10: Button = findViewById(R.id.button10)
        button10.setOnClickListener {
            reset()
        }
    }

    fun clickfun(view: View) {
        if (playerTurn) {
            val but = view as Button
            var cellID = 0
            when (but.id) {
                R.id.button -> cellID = 1
                R.id.button2 -> cellID = 2
                R.id.button3 -> cellID = 3
                R.id.button4 -> cellID = 4
                R.id.button5 -> cellID = 5
                R.id.button6 -> cellID = 6
                R.id.button7 -> cellID = 7
                R.id.button8 -> cellID = 8
                R.id.button9 -> cellID = 9
            }
            playerTurn = false
            Handler().postDelayed({ playerTurn = true }, 600)
            playnow(but, cellID)
        }
    }

    fun playnow(buttonSelected: Button, currCell: Int) {
        if (activeUser == 1) {
            buttonSelected.text = "X"
            buttonSelected.setTextColor(Color.parseColor("#EC0C0C"))
            player1.add(currCell)
            emptyCells.remove(currCell)
            buttonSelected.isEnabled = false
            Handler().postDelayed({}, 200)
            val checkWinner = checkwinner()
            if (checkWinner == 1) {
                Handler().postDelayed({ reset() }, 2000)
            } else if (singleUser) {
                Handler().postDelayed({ robot() }, 500)
            } else {
                activeUser = 2
            }
        } else {
            buttonSelected.text = "O"
            buttonSelected.setTextColor(Color.parseColor("#D22BB804"))
            activeUser = 1
            player2.add(currCell)
            emptyCells.remove(currCell)
            Handler().postDelayed({}, 200)
            buttonSelected.isEnabled = false
            val checkWinner = checkwinner()
            if (checkWinner == 1) {
                Handler().postDelayed({ reset() }, 4000)
            }
        }
    }

    fun reset() {
        player1.clear()
        player2.clear()
        emptyCells.clear()
        emptyCells.addAll(1..9)
        activeUser = 1
        for (i in 1..9) {
            var buttonselected: Button? = when (i) {
                1 -> findViewById(R.id.button)
                2 -> findViewById(R.id.button2)
                3 -> findViewById(R.id.button3)
                4 -> findViewById(R.id.button4)
                5 -> findViewById(R.id.button5)
                6 -> findViewById(R.id.button6)
                7 -> findViewById(R.id.button7)
                8 -> findViewById(R.id.button8)
                9 -> findViewById(R.id.button9)
                else -> findViewById(R.id.button)
            }
            buttonselected?.isEnabled = true
            buttonselected?.text = ""
        }
        textView.text = "Player1 : $player1Count"
        textView2.text = "Player2 : $player2Count"
    }

    fun disableReset() {
        val button10: Button = findViewById(R.id.button10)
        button10.isEnabled = false
        Handler().postDelayed({ button10.isEnabled = true }, 2200)
    }

    fun robot() {
        val rnd = (1..9).random()
        if (emptyCells.contains(rnd)) robot()
        else {
            val buttonselected: Button? = when (rnd) {
                1 -> findViewById(R.id.button)
                2 -> findViewById(R.id.button2)
                3 -> findViewById(R.id.button3)
                4 -> findViewById(R.id.button4)
                5 -> findViewById(R.id.button5)
                6 -> findViewById(R.id.button6)
                7 -> findViewById(R.id.button7)
                8 -> findViewById(R.id.button8)
                9 -> findViewById(R.id.button9)
                else -> findViewById(R.id.button)
            }
            emptyCells.remove(rnd)
            buttonselected?.text = "O"
            buttonselected?.setTextColor(Color.parseColor("#D22BB804"))
            player2.add(rnd)
            buttonselected?.isEnabled = false
            val checkWinner = checkwinner()
            if (checkWinner == 1) {
                Handler().postDelayed({ reset() }, 2000)
            }
        }
    }

    fun checkwinner(): Int {
        val winPatterns = arrayOf(
            arrayOf(1, 2, 3),
            arrayOf(4, 5, 6),
            arrayOf(7, 8, 9),
            arrayOf(1, 4, 7),
            arrayOf(2, 5, 8),
            arrayOf(3, 6, 9),
            arrayOf(1, 5, 9),
            arrayOf(3, 5, 7)
        )

        for (pattern in winPatterns) {
            if (player1.containsAll(pattern.toList())) {
                player1Count++
                Toast.makeText(this, "Player 1 Wins!", Toast.LENGTH_SHORT).show()
                return 1
            } else if (player2.containsAll(pattern.toList())) {
                player2Count++
                Toast.makeText(this, "Player 2 Wins!", Toast.LENGTH_SHORT).show()
                return 1
            }
        }

        if (emptyCells.size == 0) {
            Toast.makeText(this, "It's a draw!", Toast.LENGTH_SHORT).show()
        }
        return 0

    }

}
