package com.example.mytestviewapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mytestviewapp.databinding.FragmentShoppinglistBinding

    class ShoppinglistFragment: Fragment() {
        lateinit var binding: FragmentShoppinglistBinding

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View {
            binding = FragmentShoppinglistBinding.inflate(layoutInflater)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)


            //binding.button.setOnClickListener{.navigate(R.id.acitvity_main_to_fragment_shoppinglist)}
        }
    }