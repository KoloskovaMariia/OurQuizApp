package com.example.ourquizapp

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
    }
}
