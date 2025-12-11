package com.example.mytestviewapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mytestviewapp.databinding.FragmentMainBinding

class MainFregment: Fragment() {

    lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonCookingIdeas   .setOnClickListener{
            findNavController().navigate(R.id.action_to_cookingIdeas)
        }
        binding.buttonShoppinglist   .setOnClickListener{
            findNavController().navigate(R.id.action_to_shoppingList)
            }

    }
    }


















