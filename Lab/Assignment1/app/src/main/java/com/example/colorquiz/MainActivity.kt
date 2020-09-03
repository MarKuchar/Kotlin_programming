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
        "black" to R.color.black, "red" to R.color.red, "green" to R.color.green,
        "yellow" to R.color.yellow, "blue" to R.color.blue, "gray" to R.color.gray,
        "cyan" to R.color.cyan, "pink" to R.color.pink, "purple" to R.color.purple,
        "orange" to R.color.orange
    )

    var arr: ArrayList<String> = arrayListOf(
        "black", "red", "green", "yellow", "blue",
        "gray", "cyan", "pink", "purple", "orange"
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
        if (arr.isNotEmpty()) {
            if (view.tag == question.answerString) {
                question.score = (question.score.toInt() + 1).toString()
                val toast = Toast.makeText(applicationContext, "Correct", Toast.LENGTH_LONG)
                toast.show()
            } else {
                question.score = (question.score.toInt() - 1).toString()
                val toast = Toast.makeText(applicationContext, "Incorrect", Toast.LENGTH_LONG)
                toast.show()
            }
            setQuestion()
            binding.invalidateAll()
        }
    }

    fun setQuestion() {
        if (arr.size > 2) {
            val rnd = Random
            val lsString = arr.removeAt(rnd.nextInt(arr.size - 1))
            val rsString = arr.removeAt(rnd.nextInt(arr.size - 1))
            question.lsColor = ContextCompat.getColor(this, colors.remove(lsString)!!)
            question.rsColor = ContextCompat.getColor(this, colors.remove(rsString)!!)
            val answerInt = rnd.nextInt(2)
            question.answerString = if (answerInt == 1) rsString else lsString
            question.lsString = lsString
            question.rsString = rsString
        } else {
            question.score = "Final score is: " + question.score
            binding.lsButton.isEnabled = false
            binding.rsButton.isEnabled = false
        }
    }
}
