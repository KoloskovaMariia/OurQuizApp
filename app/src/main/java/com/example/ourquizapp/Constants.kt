package com.example.ourquizapp

object Constants{
    fun getQuestions(): ArrayList<Question>{
        val questionsList = ArrayList<Question>()
        val questionOne = Question(
            1,
            "What's name of this meme?",
            R.drawable.q1,
            "Big Floppa",
            "Pretty kitty",
            "Sharp-eared cat",
            "Cat, mr.Cat",
            1)

        val questionTwo = Question(
            2,
            "What's happening on the second part of this meme?",
            R.drawable.q2,
            "A dog is seeing",
            "There are two women, one is screaming",
            "Am empty plate",
            "More cats",
            2)

        val questionThree = Question(
            3,
            "Which word was hidden?",
            R.drawable.q3,
            "Progress bar",
            "Frog bar",
            "Frogress bar",
            "Scroll bar",
            3)

        val questionFour = Question(
            4,
            "Have you seen him? Now you have. Who are talking about?",
            R.drawable.q4,
            "Doggo",
            "Alf",
            "Big Floppa",
            "smol kitten",
            1)

        val questionFive = Question(
            5,
            "What was encrypted here?",
            R.drawable.q5,
            "I cat you",
            "I hug you",
            "I meow you",
            "I love you",
            4)

        val questionSix = Question(
            6,
            "What's in the hands?",
            R.drawable.q6,
            "a ring",
            "smol catto",
            "gyros",
            "mirror",
            2)

        val questionSeven = Question(
            7,
            "What name is associated with this meme?",
            R.drawable.q7,
            "Samuel",
            "Ibragim",
            "Stanislav",
            "Mikel",
            2)

        val questionEight = Question(
            8,
            "Which word was hidden?",
            R.drawable.q8,
            "Fog",
            "Froggo",
            "Drog",
            "Frog-dog",
            3)

        val questionNine = Question(
            9,
            "Why doggo is sad?",
            R.drawable.q9,
            "He's hungry",
            "His ball is lost",
            "sad just like that",
            "He was sent to horny jail",
            1)

        val questionTen = Question(
            10,
            "What says Keanu Reeves",
            R.drawable.q10,
            "You are incredible!",
            "You are amazing!",
            "You are fantastic!",
            "You're breathtaking!",
            4)

        questionsList.add(questionOne)
        questionsList.add(questionTwo)
        questionsList.add(questionThree)
        questionsList.add(questionFour)
        questionsList.add(questionFive)
        questionsList.add(questionSix)
        questionsList.add(questionSeven)
        questionsList.add(questionEight)
        questionsList.add(questionNine)
        questionsList.add(questionTen)

        return questionsList
    }
}