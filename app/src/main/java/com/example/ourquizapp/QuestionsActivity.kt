package com.example.ourquizapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat

class QuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0

    private var progressBar = findViewById<ProgressBar>(R.id.progressBar)
    private var img = findViewById<ImageView>(R.id.main_image)
    private var progress = findViewById<TextView>(R.id.tv_progress)
    private var currentQ = findViewById<TextView>(R.id.tv_question)
    private var firstPosition = findViewById<TextView>(R.id.tv_option_one)
    private var secondPosition = findViewById<TextView>(R.id.tv_option_two)
    private var thirdPosition = findViewById<TextView>(R.id.tv_option_three)
    private var fourthPosition = findViewById<TextView>(R.id.tv_option_four)

    private fun setQuestion(){
        mCurrentPosition = 1
        val question = mQuestionList!![mCurrentPosition - 1]

        defaultOptionsView()

        progressBar.progress = mCurrentPosition
        progress.text = "$mCurrentPosition" + "/" + progressBar.max

        currentQ.text = question!!.question
        img.setImageResource(question.image)

        firstPosition.text = question.firstOption
        secondPosition.text = question.secondOption
        thirdPosition.text = question.thirdOption
        fourthPosition.text = question.fourthOption
    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        options.add(0, firstPosition)
        options.add(1, secondPosition)
        options.add(2, thirdPosition)
        options.add(3, fourthPosition)

        for (option in options){
            option.setTextColor(Color.parseColor("@color/defaultText"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNumber: Int){
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNumber
        tv.setTextColor(Color.parseColor("@color/black"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        
        mQuestionList = Constants.getQuestions()
        setQuestion()

        firstPosition.setOnClickListener(this)
        secondPosition.setOnClickListener(this)
        thirdPosition.setOnClickListener(this)
        fourthPosition.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_option_one -> {selectedOptionView(firstPosition, 1)}
            R.id.tv_option_one -> {selectedOptionView(secondPosition, 2)}
            R.id.tv_option_one -> {selectedOptionView(thirdPosition, 3)}
            R.id.tv_option_one -> {selectedOptionView(fourthPosition, 4)}
        }
    }
}