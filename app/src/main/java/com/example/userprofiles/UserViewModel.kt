package com.example.userprofiles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    //Do this second
    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user
    // this specific user is allowing us to look at the data but not modify it

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error : StateFlow<String?> = _error

    // runs in the background
    init {
        fetchRandomUser()
    }

    fun fetchRandomUser() {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                // jumping into the api service and calling the function
                // assign the value, give us the first item of the list, or if the list
                // doesnt exist give us null
                val response = RetrofitInstance.api.getRandomUser()
                _user.value = response.results.firstOrNull()
            } catch (e: Exception) {
                _error.value = "Failed to load user: ${e.message}"
            }
            _loading.value = false
        }
    }

}
