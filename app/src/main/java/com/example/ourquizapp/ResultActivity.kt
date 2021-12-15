package com.example.ourquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ourquizapp.databinding.ActivityQuestionBinding
import com.example.ourquizapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userName = intent.getStringExtra(Constants.USER_NAME)
        binding.name.text = userName
        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)
        binding.score.text = "Your score is $correctAnswers out of $totalQuestions"

        binding.exit.setOnClickListener{
            startActivity(Intent(this, MainActivity:: class.java))
        }
        binding.anotherButton.setOnClickListener {
            intent = Intent(this, ChooseActivity:: class.java)
            intent.putExtra(Constants.USER_NAME, userName)
            startActivity(intent)
        }
    }
}
