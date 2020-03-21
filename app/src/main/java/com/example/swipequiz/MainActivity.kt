package com.example.swipequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
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

        createItemTouchHelper().attachToRecyclerView(rvQuestion)

        for (i in Quiz.QUIZ_QUESTIONS.indices) {
            questions.add(Quiz(Quiz.QUIZ_QUESTIONS[i], Quiz.QUIZ_ANSWER[i]))
        }
        QuizAdapter.notifyDataSetChanged()

    }

    private fun createItemTouchHelper(): ItemTouchHelper {

        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }
            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                var answer = questions.get(position).answer
                QuizAdapter.notifyDataSetChanged()
                if (direction == ItemTouchHelper.RIGHT && answer == true || direction == ItemTouchHelper.LEFT && answer == false) {
                    Toast.makeText(this@MainActivity, R.string.correct, Toast.LENGTH_SHORT).show()
                    questions.removeAt(position)
                }else{Snackbar.make(rvQuestion, getString(R.string.incorrect), Snackbar.LENGTH_SHORT)
                    .show()}
            }
        }
        return ItemTouchHelper(callback)
    }
}
