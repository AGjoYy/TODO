package com.example.todo.Actions

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.RecyclerViewAdapters.TaskRecyclerViewAdapter
import javax.inject.Inject

class SwipeToDelete (var adapter: TaskRecyclerViewAdapter) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT ) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        adapter.deleteOnSwipe(viewHolder.adapterPosition)
    }
}