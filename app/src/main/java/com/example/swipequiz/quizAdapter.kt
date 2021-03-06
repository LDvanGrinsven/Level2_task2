package com.example.swipequiz

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_quiz.view.*

class quizAdapter(private val reminders: List<Quiz>) : RecyclerView.Adapter<quizAdapter.ViewHolder>() {

    /**
     * Creates and returns a ViewHolder object, inflating the layout called item_reminder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_quiz, parent, false)
        )
    }

    /**
     * Returns the size of the list
     */
    override fun getItemCount(): Int {
        return reminders.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(reminders[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(quiz: Quiz) {
            itemView.tvItem.text = quiz.question
            itemView.setOnClickListener {
                Snackbar.make(itemView.rootView.findViewById(R.id.tvItem),
                    "The correct answer is: " + quiz.answer,
                    Snackbar.LENGTH_LONG
                )
                    .show()
            }
        }
    }

}
