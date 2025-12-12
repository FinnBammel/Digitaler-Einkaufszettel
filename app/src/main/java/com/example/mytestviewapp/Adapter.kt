package com.example.mytestviewapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView

data class Ideas(
    val viewHolder: String,
    ) {}

data class ShoppingItems(
    val name: String,
    val price: Int
) {}

class Adapter(private val list: List<Ideas>): RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.adding_item, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val cookingidea = list[position]

            val checkboxIdea = holder.itemView.findViewById<CheckBox>(R.id.checkBox_item)

            checkboxIdea.text = cookingidea.viewHolder
        }
    }


class AdapterShopping(private val list: List<ShoppingItems>): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adding_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shoppingItem = list[position]

        val checkboxShoppingItem = holder.itemView.findViewById<CheckBox>(R.id.checkBox_item)

        val shoppingitemString = "${shoppingItem.name} (${shoppingItem.price.toString()} EUR)"
        checkboxShoppingItem.text = shoppingitemString
    }
    }
