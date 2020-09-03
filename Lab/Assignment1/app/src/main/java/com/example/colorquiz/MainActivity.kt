package com.example.colorquiz

import android.R.attr.button
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.colorquiz.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var question: Question = Question()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.question = question
        question.setQuestion()
        binding.invalidateAll()
        binding.rsButton.setOnClickListener {
            questionAnswered(it)
        }
        binding.lsButton.setOnClickListener {
            questionAnswered(it)
        }
    }

    fun questionAnswered(view: View) {
        view.
            question.score = (question.score.toInt() + 1).toString()
            val toast = Toast.makeText(applicationContext, "Correct", Toast.LENGTH_LONG)
            toast.show()
//            question.score = (question.score.toInt() - 1).toString()
//            val toast = Toast.makeText(applicationContext, "Incorrect", Toast.LENGTH_LONG)
//            toast.show()
        question.setQuestion()
        binding.invalidateAll()

    }
}

