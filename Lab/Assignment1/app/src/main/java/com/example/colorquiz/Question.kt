package com.example.colorquiz

import android.R
import android.graphics.Color
import androidx.core.content.ContextCompat
import kotlin.random.Random


class Question {
    var score: String = "0"
    var lsColor: Int = 0
    var rsColor: Int = 0
    var answer: Int = 0
    var answerString: String = ""

    var colors: HashMap<String, Int> = hashMapOf(
        "black" to com.example.colorquiz.R.color.black,
        "red" to com.example.colorquiz.R.color.red ,
        "green" to com.example.colorquiz.R.color.green,
        "yellow" to com.example.colorquiz.R.color.yellow,
        "blue" to com.example.colorquiz.R.color.blue,
        "gray" to com.example.colorquiz.R.color.gray,
        "cyan" to com.example.colorquiz.R.color.cyan,
        "pink" to com.example.colorquiz.R.color.pink,
        "purple" to com.example.colorquiz.R.color.purple,
        "orange" to com.example.colorquiz.R.color.orange)

    var arr: ArrayList<String> = arrayListOf("black", "red", "green", "yellow", "blue", "gray", "cyan", "pink", "purple", "orange")

    fun setQuestion() {
        if (arr.size > 2) {
            val rnd = Random
            val lsString = arr.removeAt(rnd.nextInt(arr.size - 1))
            val rsString = arr.removeAt(rnd.nextInt(arr.size - 1))
            lsColor = colors.remove(lsString)!!
            rsColor = colors.remove(rsString)!!
            val answerInt = rnd.nextInt(1)
            answer = if (answerInt == 1) rsColor else lsColor
            answerString =  if (answerInt == 1) rsString else lsString
            print(arr.size)
        } else {
            score = "Final score is: " + this.score
        }
    }
}
