package com.example.mytestviewapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestviewapp.databinding.FragmentCookingideasBinding

class Cookingideasfragment: Fragment() {

    lateinit var binding: FragmentCookingideasBinding
    var List_Items: ArrayList<Ideas> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentCookingideasBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonBackCookingIdeas.setOnClickListener{
            findNavController().popBackStack()
        }
        val recyclerView = binding.ListCooking
        recyclerView.layoutManager =
            LinearLayoutManager(this.activity)
        recyclerView.adapter = Ideas.Adapter(List_Items)

        val showButton = binding.buttonAddCookingIdeas

        // finding the edit text
        val editText = binding.entryCookingIdeas

        showButton.setOnClickListener {

            val text = editText.text.toString()

            List_Items.add(Ideas(text))
            recyclerView.adapter?.notifyDataSetChanged()
        }
        }


}