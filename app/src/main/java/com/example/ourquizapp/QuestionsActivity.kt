package com.example.ourquizapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.ourquizapp.databinding.ActivityMainBinding
import com.example.ourquizapp.databinding.ActivityQuestionBinding

class QuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0
    private var mUserName: String? = null

    private lateinit var binding. ActivityQuestionBinding
    fun setQuestion(){

        //mCurrentPosition = 1
        val question = mQuestionList!![mCurrentPosition - 1]

        defaultOptionsView()

        if(mCurrentFocus == mQuestionList!!.size){
            btn_submit.text = "Finish"
        } else{
            btn_submit.text = "Submit"
        }


        ActivityQuestionBinding.progressBar.progress = mCurrentPosition
        ActivityQuestionBinding.progress.text = "$mCurrentPosition" + "/" + ActivityQuestionBinding.progressBar.max

        ActivityQuestionBinding.question.text = question!!.question
        ActivityQuestionBinding.image.setImageResource(question.image)
        ActivityQuestionBinding.tvOptionOne.text = question.firstOption
        ActivityQuestionBinding.tvOptionTwo.text = question.secondOption
        ActivityQuestionBinding.tvOptionThree.text = question.thirdOption
        ActivityQuestionBinding.tvOptionFour.text = question.fourthOption
    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        options.add(0, ActivityQuestionBinding.tvOptionOne)
        options.add(1, ActivityQuestionBinding.tvOptionTwo)
        options.add(2, ActivityQuestionBinding.tvOptionThree)
        options.add(3, ActivityQuestionBinding.tvOptionFour)

        for (option in options){
            option.setTextColor(Color.parseColor("#A1ADC1"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNumber: Int){
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNumber
        tv.setTextColor(Color.parseColor("#FF000000"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityQuestionBinding.ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(ActivityQuestionBinding.root)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        mQuestionList = Constants.getQuestions()
        setQuestion()

        ActivityQuestionBinding.tvOptionOne.setOnClickListener(this)
        ActivityQuestionBinding.tvOptionTwo.setOnClickListener(this)
        ActivityQuestionBinding.tvOptionThree.setOnClickListener(this)
        ActivityQuestionBinding.tvOptionFour.setOnClickListener(this)
        ActivityQuestionBinding.btn_submit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_option_one -> {selectedOptionView(ActivityQuestionBinding.tvOptionOne, 1)}
            R.id.tv_option_two -> {selectedOptionView(ActivityQuestionBinding.tvOptionTwo, 2)}
            R.id.tv_option_three -> {selectedOptionView(ActivityQuestionBinding.tvOptionThree, 3)}
            R.id.tv_option_four -> {selectedOptionView(ActivityQuestionBinding.tvOptionFour, 4)}
            R.id.btn_submit ->{
                if (mSelectedOptionPosition == 0){
                    mCurrentPosition ++
                when{
                    mCurrentPosition <= mQuestionList!!.size ->{
                        setQuestion()
                    } else -> {
                        Toast.makeText(this, "You've completed a test successfully", Toast.LENGTH_SHORT).show()
                    }
                }   }
                else{
                    val question = mQuestionList?.get(mCurrentPosition - 1)
                    if (question!!.correctAnswer != mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)
                    if (mCurrentPosition == mQuestionList!!.size){
                        binding.btn_submit.text = "FINISH"
                    }
                    else{
                        binding.btn_submit.text = "SEE NEXT QUESTION"
                    }

                }
            }
        }
    }
    private fun answerView(answer:Int, drawableView: Int){
        when(answer){
            1 -> {
                ActivityQuestionBinding.tv_option_one.background = ContextCompat.getDrawable(this, drawableView)
            }

            2 -> {
                ActivityQuestionBinding.tv_option_two.background = ContextCompat.getDrawable(this, drawableView)
            }

            3 -> {
                ActivityQuestionBinding.tv_option_three.background = ContextCompat.getDrawable(this, drawableView)
            }

            4 -> {
                ActivityQuestionBinding.tv_option_four.background = ContextCompat.getDrawable(this, drawableView)
            }
        }

    }
}