package com.example.ourquizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.ourquizapp.databinding.ActivityQuestionBinding

class QuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mUserName: String? = null
    private var mCorrectAnswers: Int = 0

    private lateinit var binding: ActivityQuestionBinding

    private fun setQuestion(){

        val question = (mQuestionList ?: throw NullPointerException
            ("Expression 'mQuestionList' must not be null"))[mCurrentPosition - 1]

        defaultOptionsView()

        if(mCurrentPosition == (mQuestionList?.size ?:
        throw NullPointerException("Expression 'mQuestionList' must not be null"))){
            binding.btnSubmit.text = "Finish"
        } else{
            binding.btnSubmit.text = "Submit"
        }

        binding.progressBar.progress = mCurrentPosition
        binding.progress.text = "$mCurrentPosition" + "/" + binding.progressBar.max

        binding.question.text = question.question
        binding.image.setImageResource(question.imageQ)
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

        mQuestionList = intent.getParcelableArrayListExtra(Constants.QUESTIONS)
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
                        mCurrentPosition <= (mQuestionList?.size ?:
                        throw NullPointerException("Expression 'mQuestionList' must not be null"))
                        -> {setQuestion()}

                        else -> {
                            val intent = Intent(this, ResultActivity:: class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS,
                                (mQuestionList?.size ?: throw NullPointerException
                                    ("Expression 'mQuestionList' must not be null"))
                            )
                            startActivity(intent)
                            finish()
                        }
                    }
                }
                else{
                    val question = mQuestionList?.get(mCurrentPosition - 1)

                    if ((question?.correctAnswer ?: throw NullPointerException
                            ("Expression 'question' must not be null"))
                        != mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }
                    else{
                        mCorrectAnswers++
                    }
                    binding.image.setImageResource(question.imageA)
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)
                    if (mCurrentPosition == (mQuestionList?.size ?:
                    throw NullPointerException("Expression 'mQuestionList' must not be null"))){
                        binding.btnSubmit.text = "FINISH"
                    }
                    else{
                        binding.btnSubmit.text = "SEE NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }
    private fun answerView(answer:Int, drawableView: Int){
        when(answer){
            1 -> {
                binding.tvOptionOne.background = ContextCompat.getDrawable(this, drawableView)
                binding.tvOptionOne.setTextColor(Color.parseColor("#FF000000"))
                binding.tvOptionOne.setTypeface(binding.tvOptionOne.typeface, Typeface.BOLD)
            }

            2 -> {
                binding.tvOptionTwo.background = ContextCompat.getDrawable(this, drawableView)
                binding.tvOptionTwo.setTextColor(Color.parseColor("#FF000000"))
                binding.tvOptionTwo.setTypeface(binding.tvOptionTwo.typeface, Typeface.BOLD)
            }

            3 -> {
                binding.tvOptionThree.background = ContextCompat.getDrawable(this, drawableView)
                binding.tvOptionThree.setTextColor(Color.parseColor("#FF000000"))
                binding.tvOptionThree.setTypeface(binding.tvOptionThree.typeface, Typeface.BOLD)
            }

            4 -> {
                binding.tvOptionFour.background = ContextCompat.getDrawable(this, drawableView)
                binding.tvOptionFour.setTextColor(Color.parseColor("#FF000000"))
                binding.tvOptionFour.setTypeface(binding.tvOptionFour.typeface, Typeface.BOLD)
            }
        }
    }
}