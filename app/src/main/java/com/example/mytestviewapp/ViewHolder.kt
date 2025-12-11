package com.example.mytestviewapp

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val input: TextView = view.findViewById(R.id.checkBox_item)
    }
