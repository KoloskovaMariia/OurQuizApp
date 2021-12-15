package com.example.ourquizapp

data class Question (val id : Int,
                     val question: String,
                     val imageQ: Int,
                     val imageA: Int,
                     val firstOption: String,
                     val secondOption: String,
                     val thirdOption: String,
                     val fourthOption: String,
                     val correctAnswer: Int) {

}