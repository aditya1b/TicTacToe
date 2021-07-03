package com.example.tictactoe

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var PLAYER = true
    var TURN_COUNT = 0
    var boardStatus = Array(3,{IntArray(3)})
    lateinit var board : Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        board = arrayOf(
            arrayOf(r1c1,r1c2,r1c3),
            arrayOf(r2c1,r2c2,r2c3),
            arrayOf(r3c1,r3c2,r3c3)
        )
        for(i in board){
            for(button in i){
                button.setOnClickListener(this)
            }
        }
        initializeBoardStatus()
        submit.setOnClickListener{
            PLAYER = true
            TURN_COUNT = 0
            updateDisplay("Player X turn")
            initializeBoardStatus()

        }

    }

    private fun initializeBoardStatus(){
        for(i in 0..2){
            for(j in 0..2){
                boardStatus[i][j] = -1
                board[i][j].isEnabled = true
                board[i][j].text=""
                if(i==0){
                    if(j==1) {
                        board[i][j].setBackgroundColor(Color.rgb(246, 243, 245))
                    }
                    else{
                        board[i][j].setBackgroundColor(Color.rgb(226, 212, 220))
                    }
                }
                if(i==1){
                    if(j==1){
                        board[i][j].setBackgroundColor(Color.rgb(226, 212, 220))
                    }
                    else{
                        board[i][j].setBackgroundColor(Color.rgb(246, 243, 245))
                    }
                }
                if(i==2){
                    if(j==1) {
                        board[i][j].setBackgroundColor(Color.rgb(246, 243, 245))
                    }
                    else{
                        board[i][j].setBackgroundColor(Color.rgb(226, 212, 220))
                    }
                }
            }
        }

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.r1c1 -> {
                updateValue(row = 0, col = 0, player = PLAYER)
            }
            R.id.r1c2 -> {
                updateValue(row = 0,col = 1, player = PLAYER)
            }
            R.id.r1c3 -> {
                updateValue(row = 0,col = 2, player = PLAYER)
            }
            R.id.r2c1 -> {
                updateValue(row = 1,col = 0, player = PLAYER)
            }
            R.id.r2c2 -> {
                updateValue(row = 1,col = 1, player = PLAYER)
            }
            R.id.r2c3 -> {
                updateValue(row = 1,col = 2, player = PLAYER)
            }
            R.id.r3c1 -> {
                updateValue(row = 2,col = 0, player = PLAYER)
            }
            R.id.r3c2 -> {
                updateValue(row = 2,col = 1, player = PLAYER)
            }
            R.id.r3c3 -> {
                updateValue(row = 2,col = 2, player = PLAYER)
            }
        }
        TURN_COUNT++
        PLAYER = !PLAYER
        if(PLAYER){
            updateDisplay("Player X turn")
        }
        else{
            updateDisplay("Player O turn")
        }
        if(TURN_COUNT==9){
            updateDisplay("DRAW")
        }
        checkWinner()
    }
    private fun checkWinner(){
        //Horizontal Rows
        for(i in 0..2){
            if(boardStatus[i][0]==boardStatus[i][1] && boardStatus[i][0]==boardStatus[i][2]){
                if(boardStatus[i][0]==1){
                    updateDisplay("X is Winner")
                    disableButton()
                    break
                }
                else if(boardStatus[i][0]==0){
                    updateDisplay("O is Winner")
                    disableButton()
                    break
                }
            }
        }

        //Vertical columns
        for(i in 0..2){
            if(boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i]){
                if(boardStatus[0][i]==1){
                    updateDisplay("X is Winner")
                    disableButton()
                    break
                }
                else if(boardStatus[0][i]==0){
                    updateDisplay("O is Winner")
                    disableButton()
                    break
                }
            }
        }
        //First diagonal
        if(boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0]==boardStatus[2][2]){
            if(boardStatus[0][0]==1){
                updateDisplay("X is Winner")
                disableButton()
            }
            else if(boardStatus[0][0] == 0){
                updateDisplay("O is Winner")
                disableButton()
            }
        }
        //Second Diagonal
        if(boardStatus[2][0] == boardStatus[1][1] && boardStatus[2][0]==boardStatus[0][2]){
            if(boardStatus[1][1]==1){
                updateDisplay("X is Winner")
            }
            else if(boardStatus[1][1] == 0){
                updateDisplay("O is Winner")
            }
        }

    }
    private fun disableButton(){
        for(i in 0..2){
            for(j in 0..2){
                boardStatus[i][j] = -1
                board[i][j].isEnabled = false
                //board[i][j].setBackgroundColor(Color.rgb(239, 197, 221))
            }
        }
    }
    private fun updateDisplay(str:String){
        TV.text=str
    }
    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val text = if(player)"X" else "O"
        val value = if(player) 1 else 0
        board[row][col].apply{
            isEnabled=false
            setText(text)

            if(value==1)
            setBackgroundColor(Color.rgb(255, 186, 225))
            else if(value==0){
                setBackgroundColor(Color.rgb(239, 197, 221))
            }
        }
        boardStatus[row][col]=value
    }
}
