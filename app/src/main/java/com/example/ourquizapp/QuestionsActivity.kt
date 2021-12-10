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
    private var mUserName: String? = null
    private var mCorrectAnswers: Int = 0

    private lateinit var binding: ActivityQuestionBinding

    private fun setQuestion(){

        val question = mQuestionList!![mCurrentPosition - 1]

        defaultOptionsView()

        if(mCurrentPosition == mQuestionList!!.size){
            binding.btnSubmit.text = "Finish"
        } else{
            binding.btnSubmit.text = "Submit"
        }


        binding.progressBar.progress = mCurrentPosition
        binding.progress.text = "$mCurrentPosition" + "/" + binding.progressBar.max

        binding.question.text = question!!.question
        binding.image.setImageResource(question.image)
        binding.tvOptionOne.text = question.firstOption
        binding.tvOptionTwo.text = question.secondOption
        binding.tvOptionThree.text = question.thirdOption
        binding.tvOptionFour.text = question.fourthOption
    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        options.add(0, binding.tvOptionOne)
        options.add(1, binding.tvOptionTwo)
        options.add(2, binding.tvOptionThree)
        options.add(3, binding.tvOptionFour)

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
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        mQuestionList = Constants.getQuestions()
        setQuestion()

        binding.tvOptionOne.setOnClickListener(this)
        binding.tvOptionTwo.setOnClickListener(this)
        binding.tvOptionThree.setOnClickListener(this)
        binding.tvOptionFour.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_option_one -> {selectedOptionView(binding.tvOptionOne, 1)}
            R.id.tv_option_two -> {selectedOptionView(binding.tvOptionTwo, 2)}
            R.id.tv_option_three -> {selectedOptionView(binding.tvOptionThree, 3)}
            R.id.tv_option_four -> {selectedOptionView(binding.tvOptionFour, 4)}
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
                        binding.btnSubmit.text = "FINISH"
                    }
                    else{
                        binding.btnSubmit.text = "SEE NEXT QUESTION"
                    }

                }
            }
        }
    }
    private fun answerView(answer:Int, drawableView: Int){
        when(answer){
            1 -> {
                binding.tvOptionOne.background = ContextCompat.getDrawable(this, drawableView)
            }

            2 -> {
                binding.tvOptionTwo.background = ContextCompat.getDrawable(this, drawableView)
            }

            3 -> {
                binding.tvOptionThree.background = ContextCompat.getDrawable(this, drawableView)
            }

            4 -> {
                binding.tvOptionFour.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }
}