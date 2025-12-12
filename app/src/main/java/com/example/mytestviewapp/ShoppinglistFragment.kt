package com.example.mytestviewapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytestviewapp.databinding.FragmentShoppinglistBinding
import kotlinx.coroutines.launch

class ShoppinglistFragment: Fragment() {
    lateinit var binding: FragmentShoppinglistBinding

    var listItemsSL: ArrayList<ShoppingItems> = ArrayList()
    private lateinit var storeManager: StoreManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentShoppinglistBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        storeManager = StoreManager(requireContext())

        binding.buttonBackShoppingList.setOnClickListener{
            findNavController().popBackStack()
        }

        val recyclerView = binding.ListShopping
        recyclerView.layoutManager =
            LinearLayoutManager(this.activity)
        recyclerView.adapter = AdapterShopping(listItemsSL)

        // Load saved data
        viewLifecycleOwner.lifecycleScope.launch {
            storeManager.shoppingListFlow.collect { savedList ->
                listItemsSL.clear()
                listItemsSL.addAll(savedList)
                recyclerView.adapter?.notifyDataSetChanged()
            }
        }

        val showButton = binding.buttonAddShoppingList

        // finding the edit text
        val editText = binding.entryShoppingList
        val entryPrice = binding.entryPrice

        showButton.setOnClickListener {
            val text = editText.text.toString()
            val priceStr = entryPrice.text.toString()

            if (text.isNotBlank() && priceStr.isNotBlank()) {
                val price = priceStr.toIntOrNull() ?: 0
                listItemsSL.add(ShoppingItems(text, price))
                recyclerView.adapter?.notifyDataSetChanged()

                // Save data
                viewLifecycleOwner.lifecycleScope.launch {
                    storeManager.saveShoppingList(listItemsSL)
                }

                editText.text?.clear()
                entryPrice.text?.clear()
            }
        }
    }
}
