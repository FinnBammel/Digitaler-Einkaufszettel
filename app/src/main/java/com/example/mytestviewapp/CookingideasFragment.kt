package com.example.mytestviewapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytestviewapp.databinding.FragmentCookingideasBinding
import kotlinx.coroutines.launch

class CookingideasFragment: Fragment() {

    lateinit var binding: FragmentCookingideasBinding
    var listItems: ArrayList<Ideas> = ArrayList()
    private lateinit var storeManager: StoreManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentCookingideasBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        storeManager = StoreManager(requireContext())

        binding.buttonBackCookingIdeas.setOnClickListener{
            findNavController().popBackStack()
        }
        val recyclerView = binding.ListCooking
        recyclerView.layoutManager =
            LinearLayoutManager(this.activity)
        recyclerView.adapter = Adapter(listItems)

        // Load saved data
        viewLifecycleOwner.lifecycleScope.launch {
            storeManager.cookingListFlow.collect { savedList ->
                listItems.clear()
                listItems.addAll(savedList)
                recyclerView.adapter?.notifyDataSetChanged()
            }
        }

        val showButton = binding.buttonAddCookingIdeas

        // finding the edit text
        val editText = binding.entryCookingIdeas

        showButton.setOnClickListener {
            val text = editText.text.toString()
            if (text.isNotBlank()) {
                listItems.add(Ideas(text))
                recyclerView.adapter?.notifyDataSetChanged()
                
                // Save data
                viewLifecycleOwner.lifecycleScope.launch {
                    storeManager.saveCookingList(listItems)
                }
                
                editText.text?.clear()
            }
        }
    }
}
