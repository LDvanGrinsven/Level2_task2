package com.example.swipequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val questions = arrayListOf<Quiz>()
    private val QuizAdapter = quizAdapter(questions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {

        // Initialize the recycler view with a linear layout manager, adapter
        rvQuestion.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rvQuestion.adapter = QuizAdapter
        rvQuestion.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))

        for (i in Quiz.QUIZ_QUESTIONS.indices) {
            questions.add(Quiz(Quiz.QUIZ_QUESTIONS[i], Quiz.QUIZ_ANSWER[i]))
        }
        QuizAdapter.notifyDataSetChanged()

    }
}
