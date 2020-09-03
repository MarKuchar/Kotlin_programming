package com.example.colorquiz

import android.R.attr.button
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.colorquiz.databinding.ActivityMainBinding
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private var question: Question = Question()
    private lateinit var binding: ActivityMainBinding

    var colors: HashMap<String, Int> = hashMapOf(
        "black" to R.color.black,
        "red" to com.example.colorquiz.R.color.red,
        "green" to com.example.colorquiz.R.color.green,
        "yellow" to com.example.colorquiz.R.color.yellow,
        "blue" to com.example.colorquiz.R.color.blue,
        "gray" to com.example.colorquiz.R.color.gray,
        "cyan" to com.example.colorquiz.R.color.cyan,
        "pink" to com.example.colorquiz.R.color.pink,
        "purple" to com.example.colorquiz.R.color.purple,
        "orange" to com.example.colorquiz.R.color.orange
    )

    var arr: ArrayList<String> = arrayListOf(
        "black",
        "red",
        "green",
        "yellow",
        "blue",
        "gray",
        "cyan",
        "pink",
        "purple",
        "orange"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.question = question

        setQuestion()
        binding.invalidateAll()
        binding.rsButton.setOnClickListener {
            questionAnswered(it)
        }
        binding.lsButton.setOnClickListener {
            questionAnswered(it)
        }
    }

    fun questionAnswered(view: View) {
        question.score = (question.score.toInt() + 1).toString()
        val toast = Toast.makeText(applicationContext, "Correct", Toast.LENGTH_LONG)
        toast.show()
//            question.score = (question.score.toInt() - 1).toString()
//            val toast = Toast.makeText(applicationContext, "Incorrect", Toast.LENGTH_LONG)
//            toast.show()
        setQuestion()
        binding.invalidateAll()

    }

    fun setQuestion() {
        if (arr.size > 2) {
            val rnd = Random
            val lsString = arr.removeAt(rnd.nextInt(arr.size - 1))
            val rsString = arr.removeAt(rnd.nextInt(arr.size - 1))
            question.lsColor = ContextCompat.getColor(this, colors.remove(lsString)!!)
            question.rsColor = ContextCompat.getColor(this, colors.remove(rsString)!!)
            val answerInt = rnd.nextInt(1)
            question.answer = if (answerInt == 1) question.rsColor else question.lsColor
            question.answerString = if (answerInt == 1) rsString else lsString
            print(arr.size)
        } else {
            question.score = "Final score is: " + question.score
        }
    }
}
