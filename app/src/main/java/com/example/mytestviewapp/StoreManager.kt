package com.example.mytestviewapp

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class StoreManager(private val context: Context) {

    companion object {
        val COOKING_LIST_KEY = stringPreferencesKey("cooking_list")
        val SHOPPING_LIST_KEY = stringPreferencesKey("shopping_list")
    }

    suspend fun saveCookingList(list: List<Ideas>) {
        val gson = Gson()
        val json = gson.toJson(list)
        context.dataStore.edit { preferences ->
            preferences[COOKING_LIST_KEY] = json
        }
    }

    val cookingListFlow: Flow<List<Ideas>> = context.dataStore.data
        .map { preferences ->
            val json = preferences[COOKING_LIST_KEY] ?: ""
            if (json.isNotEmpty()) {
                val gson = Gson()
                val type = object : TypeToken<List<Ideas>>() {}.type
                gson.fromJson(json, type)
            } else {
                emptyList()
            }
        }

    suspend fun saveShoppingList(list: List<ShoppingItems>) {
        val gson = Gson()
        val json = gson.toJson(list)
        context.dataStore.edit { preferences ->
            preferences[SHOPPING_LIST_KEY] = json
        }
    }

    val shoppingListFlow: Flow<List<ShoppingItems>> = context.dataStore.data
        .map { preferences ->
            val json = preferences[SHOPPING_LIST_KEY] ?: ""
            if (json.isNotEmpty()) {
                val gson = Gson()
                val type = object : TypeToken<List<ShoppingItems>>() {}.type
                gson.fromJson(json, type)
            } else {
                emptyList()
            }
        }
}
