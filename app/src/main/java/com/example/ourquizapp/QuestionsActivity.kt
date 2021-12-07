package com.example.ourquizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class QuestionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        
        val questionList = Constants.getQuestions()
        Log.i("Questions size", "${questionList.size}")

        val currentPosition = 1
        val question: Question ? = questionList[currentPosition - 1]

        progressBar.progress = currentPosition
        tv_progress.text = "$currentPosition" + "/" + progressBar.max

        tv_question.text = question!!.question
        iv_image.setImageResource(question.image)
        tv_option_one.text = question.firstOption
        tv_option_two.text = question.secondOption
        tv_option_three.text = question.thirdOption
        tv_option_four.text = question.fourthOption

    }
}