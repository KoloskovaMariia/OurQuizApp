package com.example.ourquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ChooseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)

        val memeButton = findViewById<Button>(R.id.meme_button)
        val geoButton = findViewById<Button>(R.id.geo_button)
        val userName = intent.getStringExtra(Constants.USER_NAME)

        memeButton.setOnClickListener{
            val intent = Intent(this, QuestionsActivity :: class.java)
            intent.putExtra(Constants.USER_NAME, userName)
            intent.putExtra(Constants.QUESTIONS, Constants.getMemeQuestionSet())
            startActivity(intent)
            finish()
        }

        geoButton.setOnClickListener {
            val intent = Intent(this, QuestionsActivity :: class.java)
            intent.putExtra(Constants.USER_NAME, userName)
            intent.putExtra(Constants.QUESTIONS, Constants.getGeographicalQuestionSet())
            startActivity(intent)
            finish()
        }

    }
}