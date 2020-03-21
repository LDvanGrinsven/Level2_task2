package com.example.swipequiz

data class Quiz(
    var question: String, var answer: Boolean
)
{
    companion object {
        val QUIZ_QUESTIONS = arrayOf(
            "Question 1",
			"Question 2",
			"Question 3",
			"Question 4"
        )

        val QUIZ_ANSWER = arrayOf(
            true,
            true,
            false,
            true
        )
    }
}